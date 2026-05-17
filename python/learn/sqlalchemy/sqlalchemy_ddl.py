from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, text
from sqlalchemy import inspect
from sqlalchemy.exc import SQLAlchemyError

# Initialize the engine for a local SQLite database
engine = create_engine('sqlite:///alchemy_tutorial.db', echo=True)
metadata = MetaData()

# Define the 'users_table' schema
users = Table(
    'users_table', metadata,
    Column('id', Integer, primary_key=True),
    Column('name', String(50))
)

# Create all tables defined in metadata (if they don't exist yet)
try:
    metadata.create_all(engine)
    print("Database and tables created successfully.")
except SQLAlchemyError as e:
    print(f"Error creating tables: {e}")


def inspect_database():
    """Print all table names found in the database."""
    try:
        inspector = inspect(engine)
        print("Tables in the database:")
        print(inspector.get_table_names())
    except SQLAlchemyError as e:
        print(f"Error inspecting database: {e}")


def inspect_table(table_name):
    """Print all column names and types for a given table."""
    try:
        inspector = inspect(engine)
        print(f"Columns in the '{table_name}' table:")
        for column in inspector.get_columns(table_name):
            print(f"  - {column['name']} ({column['type']})")
    except SQLAlchemyError as e:
        print(f"Error inspecting table '{table_name}': {e}")


def upgrade(engine):
    """Add the 'email' column to 'users_table' (schema migration up)."""
    try:
        with engine.connect() as connection:
            connection.execute(
                text("ALTER TABLE users_table ADD COLUMN email VARCHAR(100)")
            )
            connection.commit()
        print("Database schema upgraded: 'email' column added to 'users_table'.")
    except SQLAlchemyError as e:
        print(f"Error during upgrade: {e}")


def downgrade(engine):
    """Remove the 'email' column from 'users_table' (schema migration down).
    
    Note: DROP COLUMN requires SQLite >= 3.35.0.
    """
    try:
        with engine.connect() as connection:
            connection.execute(
                text("ALTER TABLE users_table DROP COLUMN email")
            )
            connection.commit()
        print("Database schema downgraded: 'email' column removed from 'users_table'.")
    except SQLAlchemyError as e:
        print(f"Error during downgrade: {e}")


if __name__ == "__main__":
    inspect_database()
    inspect_table('users_table')

    upgrade(engine)
    inspect_table('users_table')

    downgrade(engine)
    inspect_table('users_table')