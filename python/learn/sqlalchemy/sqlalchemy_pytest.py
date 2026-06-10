from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext import IntegrityError

import pytest

from learn.sqlalchemy.sqlalchemy_orm import User


#from your_app.models import Base

def get_test_engine():
    return create_engine('sqlite:///:memory:')

def get_test_session():
    engine = get_test_engine()
    Base.metadata.create_all(engine)
    Session = sessionmaker(bind=engine)
    return Session()

# In your test setup
def setup_module(module):
    global test_session()
    test_session = get_test_session()

def teardown_session(module):
    test_session.close()

'''Unit Tests'''

def test_create_user(test_session):
    user = User(name='Testname', email='testmail@test.com')
    test_session.add(user)
    test_session.commit()

    assert user.id is not None
    assert user.name == 'Testname'
    assert user.email == 'testmail@test.com'

def test_unique_email_constraint(test_session):
    user1 = User(name='Testname', email='testmail@test.')
    user2 = User(name='Testname2', email='testmail2@test.')

    test_session.add(user1)
    test_session.commit()

    test_session.add(user2)
    with pytest.raises(IntegrityError):
        test_session.commit()

def test_query_user(test_session):
    user = User(name='Testname', email='testmail@test.')
    test_session.add(user)
    test_session.commit()

    queried_user = test_session.query(User).filter(User.id == user.id).one()
    assert queried_user is not None
    assert queried_user.name == user.name
    assert queried_user.email == user.email

def test_update_user(test_session):
    user = User(name='Testname', email='testmail@test.')
    test_session.add(user)
    test_session.commit()

    user.name = ('Updatedname2')
    test_session.commit()

    updated_user = test_session.query(User).filter(User.id == user.id).one()
    assert updated_user.name == 'Updatedname2'

def test_delete_user(test_session):
    user = User(name='Testname', email='testmail@test.')
    test_session.add(user)
    test_session.commit()

    test_session.delete(user)
    test_session.commit()

    deleted_user = test_session.query(User).filter(User.id == user.id).one()
    assert deleted_user is None


'''Testing with Transactions'''
@pytest.fixture(scope='function')
def db_session(test_engine):
    connection = test_engine.connect()
    transaction = connection.begin()
    Session = sessionmaker(bind=connection)
    session = Session()

    yield session
    transaction.rollback()
    connection.close()

def test_user_creation(db_session):
    user = User(name='Testname', email='testmail@test.')
    db_session.add(user)
    db_session.commit()

    assert db_session.query(User).filter(User.id == user.id).one() is not None

#After this test, the transaction is rolled back, and the database is clean

'''Mocking Database Calls'''
from unittest.mock import MagicMock, patch
#from your_app.models import User
#from your_app.services import get_user_by_email

def test_get_user_by_email():
    mock_session = MagicMock()
    mock_user = User(id=1, name='Alice', email='alice@example.com')
    mock_session.query.return_value.filter_by.return_value.first.return_value = mock_user

    with patch('your_app.services.Session', return_value=mock_session):
        user = get_user_by_email('alice@example.com')

    assert user.id == 1
    assert user.name == 'Alice'
    assert user.email == 'alice@example.com'

'''
Testing Asynchronous SQLAlchemy Code
'''


@pytest.fixture(scope='function')
async def async_db_session():
    engine = create_async_engine('sqlite+aiosqlite:///:memory:')
    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.create_all)

    async_session = sessionmaker(engine, class_=AsyncSession, expire_on_commit=False)

    async with async_session() as session:
        yield session

    await engine.dispose()


@pytest.mark.asyncio
async def test_async_user_creation(async_db_session):
    user = User(name='Alice', email='alice@example.com')
    async_db_session.add(user)
    await async_db_session.commit()

    result = await async_db_session.execute(select(User).filter_by(email='alice@example.com'))
    fetched_user = result.scalar_one_or_none()
    assert fetched_user is not None
    assert fetched_user.name == 'Alice'





'''
Best Practices for Testing SQLAlchemy Applications:
. Use a separate test database: Always use a separate database for testing to avoid interfering with development or production data.
. Leverage fixtures: Use pytest fixtures to set up and tear down your test database and sessions.
. Test all CRUD operations: Ensure you have tests for Create, Read, Update, and Delete operations for each model.
. Test constraints and validations: Include tests for database constraints and any custom validations you’ve implemented.
. Use transactions for test isolation: Wrap each test in a transaction that’s rolled back after the test to ensure a clean state.
. Test complex queries: If you have complex queries or joins, write specific tests for them to ensure they’re working correctly.
. Mock external dependencies: When testing services that use your SQLAlchemy models, consider mocking the database calls to isolate your tests.
. Test error conditions: Include tests for expected error conditions, such as integrity errors or validation failures.
. Performance testing: Consider writing performance tests for critical database operations to catch any unintended performance regressions.
. Continuous Integration: Integrate your SQLAlchemy tests into your CI/CD pipeline to catch issues early.
'''
