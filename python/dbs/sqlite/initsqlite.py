import sqlite3

try:
    with sqlite3.connect('test.db') as conn:
        print(f"Opened SQLite database with version {sqlite3.sqlite_version} successfully.")

except sqlite3.OperationalError as err:
    print("Failed to open databse:", err)







