import sqlite3

try:
    with sqlite3.connect('test.db') as conn:
        print(f"Opened SQLite database with version {sqlite3.sqlite_version} successfully.")

    cursor = conn.cursor()
    print("Database successfully created.")

    create_table_query = '''
    CREATE TABLE IF NOT EXISTS Students (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        age INTEGER NOT NULL,
        email TEXT NOT NULL
    );
    '''

    cursor.execute(create_table_query)
    conn.commit()
    print("Table successfully created.")

    # No need to call connection.close(); it's done automatically!
except sqlite3.OperationalError as err:
    print("Failed to open databse:", err)








