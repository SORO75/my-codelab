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

    





