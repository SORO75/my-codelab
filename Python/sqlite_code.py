import sqlite3
from sqlite3 import Error

def create_connection (db_file):
    """create a database connetion to a SQLite database"""
    conn = None
    try:
        conn = sqlite3.connect (db_file)
        print (sqlite3.version)
    except Error as e:
        print (e)
    finally:
        if conn:
            conn.close()

if __name__ == '__main__':
    create_connection(r"/Users/ril/workspace/GitHub/howto/Python/test.db")


def create_connection():
    """ create a database connection to a database that resides
        in the memory
    """
    conn = None;
    try:
        conn = sqlite3.connect(':memory:')
        print(sqlite3.version)
    except Error as e:
        print(e)
    finally:
        if conn:
            conn.close()


if __name__ == '__main__':
    create_connection()
