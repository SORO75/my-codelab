import sqlite3

with sqlite3.connect('test.db') as conn:
    # Create a cursor object
    cursor = conn.cursor()

    # Write the SQL command to select all records from the Students table
    select_query = "SELECT * FROM Students;"

    # Execute the SQL command
    cursor.execute(select_query)

    # Fetch all records
    all_students = cursor.fetchall()

    # Display results in the terminal
    print("ALl Students:")
    for student in all_students:
        print (student)

    # SQL command to update a student's age
    update_query = '''UPDATE Students SET Name=? WHERE ID=?'''

    # Data for the update
    new_age = 21
    student_name = 'Jane Doe'
    cursor.execute(update_query, (student_name, new_age))
    conn.commit()

    # Print a confirmation message
    print(f"Updated age for {student_name} to {new_age}")

#######################################################
# Deleting
#######################################################

# SQL command to delete a student
delete_query = '''DELETE FROM Students WHERE name=?;'''

# Name of the student to be deleted
student_name = 'Jane Doe'

# Execute the SQL command with the data
cursor.execute(delete_query, (student_name, ))
conn.commit()

# Print a confirmation message
print(f"Deleted student recort for {student_name}.")

####################################################
                #Transaction
####################################################

# Create a Customers table
create_customer_table ='''
CREATE TABLE IF NOT EXISTS Customers(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE,
    balance INTEGER NOT NULL
);
'''

cursor.execute(create_customer_table)

# Insert tho customers
cursor.execute('''
INSERT INTO Customers(name, balance) VALUES (?, ?);''', ('Ashutosh, 100.0'))

cursor.execute('''
INSERT INTO Customers(name, balance) VALUES (?, ?);''', ('Krishna, 50.0'))

conn.commit()


select_query = "SELECT * FROM Customers;"

# Execute the SQL command
cursor.execute(select_query)

 # Fetch all records
all_customers = cursor.fetchall()

# Display results in the terminal
print("ALl Customerss:")
for customer in all_customers:
    print (student)