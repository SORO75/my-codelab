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

