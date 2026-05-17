from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, text
from sqlalchemy import inspect

# Engine für lokale SQLite-Datenbank
engine = create_engine('sqlite:///alchemy_tutorial.db', echo=True)
metadata = MetaData()  # klein geschrieben, kein Konflikt mit der Klasse

users = Table(
    'users_table', metadata,
    Column('id', Integer, primary_key=True),
    Column('name', String(50))
)
metadata.create_all(engine)


def inspect_database():
    inspector = inspect(engine)
    print("Tables in the database:")
    print(inspector.get_table_names())


def inspect_table(table_name):
    inspector = inspect(engine)  # ein Inspector reicht
    print(f"Columns in the '{table_name}' table:")
    for column in inspector.get_columns(table_name):
        print(f"  - {column['name']} ({column['type']})")


def upgrade(engine):
    with engine.connect() as connection:
        connection.execute(
            text("ALTER TABLE users_table ADD COLUMN email VARCHAR(100)")
        )
        connection.commit()
    print("Database schema upgraded: 'email' column added to 'users_table'.")


def downgrade(engine):
    # DROP COLUMN erfordert SQLite >= 3.35.0
    with engine.connect() as connection:
        connection.execute(
            text("ALTER TABLE users_table DROP COLUMN email")
        )
        connection.commit()
    print("Database schema downgraded: 'email' column removed from 'users_table'.")


if __name__ == "__main__":
    inspect_database()
    inspect_table('users_table')
    upgrade(engine)
    inspect_table('users_table')
    downgrade(engine)
    inspect_table('users_table')
   