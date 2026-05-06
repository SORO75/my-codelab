import sqlite3
from faker import Faker

#Initialize Faker
fake = Faker (['en_IN'])


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
    print("Table successfully created.")

    insert_query = '''
    INSERT INTO Students (name, age, email) 
    VALUES (?, ?, ?)
    '''
    student_data = [(fake.name(), fake.random_int(min=18, max=25), fake.email()) for _ in range(5)]
   # cursor.execute(insert_query, student_data)
    cursor.executemany(insert_query, student_data)
    #print("Record inserted successfully.")
    print("Fake student data successfully inserted.")

    conn.commit()


    # No need to call connection.close(); it's done automatically!
except sqlite3.OperationalError as err:
    print("Failed to open databse:", err)








