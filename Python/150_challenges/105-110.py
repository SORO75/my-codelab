'''105
Write a new file called “Numbers.txt”.
Add five numbers to the document which are stored on the same
line and only separated by a comma.
Once you have run the program, look in the location where your
program is stored and you should see that the file has been created.
The easiest way to view the contents
of the new text file on a Windows system is to read it using Notepad.
'''

file = open("Numbers.txt", "w")
file.write("1, ")
file.write("2, ")
file.write("3, ")
file.write("4, ")
file.write("5 ")
file.close()




'''106
Create a new file called “Names.txt”. Add five names to the document, which are stored on separate lines. Once you have run the program, look in the location where your program is stored and check that the file has been created properly.
'''
file = open("Namens.txt", "w")
file.write("Bob\n")
file.write("Tim\n")
file.write("Inna\n")
file.write("SArah\n")
file.write("Timothy\n")
file.close()

'''107 
Open the Names.txt file and display the data in Python.
'''
file = open("Namens.txt","r")
print(file.read())
file.close()



'''108
Open the Names.txt file. Ask the user to input a new name. 
Add this to the end of the file and display the entire file.
'''
file = open("Namens.txt","a")
newname = input("Enter a new name: ")
file.write(newname+"\n")
file = open("Namens.txt","r")
print(file.read())
file.close()

'''109
Display the following menu to the user:
1) Create a new file
2) Display the file
3) add a new item to the file
Make a selection 1, 2 or 3

Ask the user to enter 1, 2 or 3. If they select anything other than 1, 2 or 3 it should
 display a suitable error message.
If they select 1, ask the user to enter a school subject and save 
it to a new file called “Subject.txt”. It should overwrite any existing file with a new file.
If they select 2, display the contents of the “Subject.txt” file.
If they select 3, ask the user to enter a new subject and save it to the file and
 then display the entire contents of the file.
Run the program several times to test the options.
'''
print("1) Create a new file")
print("2) Display a file")
print("3) Add a new item to the file")
selection = int(input("Make a selection and choise a number 1, 2 or 3"))

if selection == 1:
    subject = input("Enter a school subject: ")
    file = open("Subject.txt", "w")
    file.write(subject + "\n")
    file.close()
elif selection == 2:
    file = open("Subject.txt", "r")
    print(file.read())
    file.close()
elif selection == 3:
    subject = input("Enter a school subject: ")
    file = open("Subject.txt", "a")
    file.write(subject + "\n")
    file.close()
    file = open("Subject.txt", "r")
    print(file.read())
    file.close()
else:
    print("Invalid option")

'''110
Using the Names.txt file you created earlier, 
display the list of names in Python. 
Ask the user to type in one of the names and then save all the names
 except the one they entered into a new file called Names2.txt.

'''