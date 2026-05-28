from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String
from sqlalchemy import inspect, insert, update, delete, select
from sqlalchemy.exc import SQLAlchemyError

# Initialize the engine for a local SQLite database
engine = create_engine('sqlite:///alchemy_tutorial.db', echo=True)
metadata = MetaData()

# Define the 'users_table' schema
users = Table(
    'users_table', metadata,
    Column('id', Integer, primary_key=True),
    Column('name', String(50)),
    Column('email', String(100))
)


# ──────────────────────────────────────────────
# DDL — Table Setup
# ──────────────────────────────────────────────

def create_tables():
    """Create all tables defined in metadata (if they don't exist yet)."""
    try:
        metadata.create_all(engine)
        print("Database and tables created successfully.")
    except SQLAlchemyError as e:
        print(f"Error creating tables: {e}")
        raise


def drop_tables():
    """Drop all tables defined in metadata — only if they exist."""
    try:
        metadata.drop_all(engine, checkfirst=True)
        print("All tables dropped successfully.")
    except SQLAlchemyError as e:
        print(f"Error dropping tables: {e}")
        raise


# ──────────────────────────────────────────────
# DML — Insert
# ──────────────────────────────────────────────

def insert_user(name: str, email: str):
    """Insert a single user record into 'users_table'."""
    try:
        with engine.connect() as connection:
            connection.execute(
                insert(users).values(name=name, email=email)
            )
            connection.commit()
        print(f"User inserted: name='{name}', email='{email}'")
    except SQLAlchemyError as e:
        print(f"Error inserting user '{name}': {e}")
        raise


def insert_many_users(users_data: list[dict]):
    """Insert multiple user records into 'users_table' in a single transaction.

    Args:
        users_data: List of dicts with keys 'name' and 'email'.
                    Example: [{"name": "Alice", "email": "alice@example.com"}, ...]
    """
    try:
        with engine.connect() as connection:
            connection.execute(insert(users), users_data)
            connection.commit()
        print(f"{len(users_data)} users inserted successfully.")
    except SQLAlchemyError as e:
        print(f"Error inserting multiple users: {e}")
        raise


# ──────────────────────────────────────────────
# DML — Select / Read
# ──────────────────────────────────────────────

def select_all_users():
    """Fetch and print all records from 'users_table'."""
    try:
        with engine.connect() as connection:
            result = connection.execute(select(users))
            rows = result.fetchall()

        if not rows:
            print("No users found.")
        else:
            print("All users:")
            for row in rows:
                print(f"id={row.id}, name={row.name}, email={row.email}")
    except SQLAlchemyError as e:
        print(f"Error fetching users: {e}")
        raise


def select_user_by_id(user_id: int):
    """Fetch and print a single user record by ID."""
    try:
        with engine.connect() as connection:
            result = connection.execute(
                select(users).where(users.c.id == user_id)
            )
            row = result.fetchone()

        if row is None:
            print(f"No user found with id={user_id}.")
        else:
            print(f"User found: id={row.id}, name={row.name}, email={row.email}")
    except SQLAlchemyError as e:
        print(f"Error fetching user with id={user_id}: {e}")
        raise


# ──────────────────────────────────────────────
# DML — Update
# ──────────────────────────────────────────────

def update_user_email(user_id: int, new_email: str):
    """Update the email address of a user identified by ID."""
    try:
        with engine.connect() as connection:
            result = connection.execute(
                update(users)
                .where(users.c.id == user_id)
                .values(email=new_email)
            )
            connection.commit()

        if result.rowcount == 0:
            print(f"No user found with id={user_id}. Nothing updated.")
        else:
            print(f"User id={user_id} updated: email='{new_email}'")
    except SQLAlchemyError as e:
        print(f"Error updating user id={user_id}: {e}")
        raise


def update_user_name(user_id: int, new_name: str):
    """Update the name of a user identified by ID."""
    try:
        with engine.connect() as connection:
            result = connection.execute(
                update(users)
                .where(users.c.id == user_id)
                .values(name=new_name)
            )
            connection.commit()

        if result.rowcount == 0:
            print(f"No user found with id={user_id}. Nothing updated.")
        else:
            print(f"User id={user_id} updated: name='{new_name}'")
    except SQLAlchemyError as e:
        print(f"Error updating user id={user_id}: {e}")
        raise


# ──────────────────────────────────────────────
# DML — Delete
# ──────────────────────────────────────────────

def delete_user(user_id: int):
    """Delete a single user record by ID."""
    try:
        with engine.connect() as connection:
            result = connection.execute(
                delete(users).where(users.c.id == user_id)
            )
            connection.commit()

        if result.rowcount == 0:
            print(f"No user found with id={user_id}. Nothing deleted.")
        else:
            print(f"User id={user_id} deleted successfully.")
    except SQLAlchemyError as e:
        print(f"Error deleting user id={user_id}: {e}")
        raise


def delete_all_users():
    """Delete all records from 'users_table' without dropping the table."""
    try:
        with engine.connect() as connection:
            result = connection.execute(delete(users))
            connection.commit()
        print(f"All users deleted. Rows affected: {result.rowcount}")
    except SQLAlchemyError as e:
        print(f"Error deleting all users: {e}")
        raise


# ──────────────────────────────────────────────
# Inspect
# ──────────────────────────────────────────────

def inspect_database():
    """Print all table names found in the database."""
    try:
        inspector = inspect(engine)
        print("Tables in the database:")
        print(inspector.get_table_names())
    except SQLAlchemyError as e:
        print(f"Error inspecting database: {e}")
        raise


def inspect_table(table_name: str):
    """Print all column names and types for a given table."""
    try:
        inspector = inspect(engine)
        print(f"Columns in the '{table_name}' table:")
        for column in inspector.get_columns(table_name):
            print(f"  - {column['name']} ({column['type']})")
    except SQLAlchemyError as e:
        print(f"Error inspecting table '{table_name}': {e}")
        raise


# ──────────────────────────────────────────────
# Main
# ──────────────────────────────────────────────

if __name__ == "__main__":
    try:
        # --- Setup: drop first if exists, then recreate ---
        drop_tables()
        create_tables()
        inspect_database()
        inspect_table('users_table')

        # --- Insert single user ---
        insert_user("Alice", "alice@example.com")

        # --- Insert multiple users ---
        insert_many_users([
            {"name": "Bob",     "email": "bob@example.com"},
            {"name": "Charlie", "email": "charlie@example.com"},
            {"name": "Diana",   "email": "diana@example.com"},
        ])

        # --- Read ---
        select_all_users()
        select_user_by_id(1)
        select_user_by_id(99)       # Non-existent user — shows "not found" message

        # --- Update ---
        update_user_email(1, "alice_new@example.com")
        update_user_name(2, "Bobby")
        update_user_email(99, "ghost@example.com")  # Non-existent user

        # --- Verify updates ---
        select_all_users()

        # --- Delete single user ---
        delete_user(3)
        select_all_users()

        # --- Delete all users ---
        delete_all_users()
        select_all_users()

        # --- Teardown ---
        drop_tables()
        inspect_database()

    except SQLAlchemyError:
        print("Pipeline stopped due to a database error.")