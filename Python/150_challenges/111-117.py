'''111
Create a .csv file that will store the following data. Call it “Books.csv”.
  Book
0 To Kill A Mockingbird
1 A Brief History of Time
2 The Great Gatsby
3 The Man Who Mistook His Wife for a Hat
4 Pride and Prejudice
Author
Harper Lee Stephen Hawking F. Scott Fitzgerald Oliver Sacks
Jane Austen
Year Released 1960
1988
1922
1985
1813

'''
import csv
file = open("Books.csv","w")
newrecord = "To Kill A Mockingbird, Harper Lee, 1960\n"
file.write(str(newrecord))
newrecord = "A Brief History of Time, Stephen Hawking, 1988\n"
file.write(str(newrecord))
newrecord = "The Great Gatsby, F. Scott Fitzgerald, 1922\n"
file.write(str(newrecord))
newrecord = "The Man Who Mistook His Wife for a Har, Oliver Sacks, 1985\n"
file.write(str(newrecord))
newrecord = "Pride and Prejudice, Jane Austen, 1813\n"
file.write(str(newrecord))
file.close()

'''112
Using the Books.csv file from program 111, ask the user to enter another record and add it to the end of the file. 
Display each row of the .csv file on a separate line.
'''
import csv
title = input("Enter a title:")
author = input("Enter author:")
year = input("Enter the year it was released: ")
newrecord = title+","+author+","+year+"\n"
file = open("Books.csv","a")

file.write(str(newrecord))
file.close()

file = open("Books.csv","r")
for row in file:
    print(row)
file.close()



'''113
Using the Books.csv file, ask the user how many records they want to add to the list 
and then allow them to add that many. 
After all the data has been added, ask for an author
 and display all the books in the list by that author. 
 If there are no books by that author in the list, display a suitable message
'''
import csv
n = int(input("How many records do you want to add to the list"))
file = open("Books.csv", "a")
for i in range(0,n):
    title = input("Enter a title:")
    author = input("Enter author:")
    year = input("Enter the year it was released: ")
    newrecord = title+","+author+","+year+"\n"
    file.write(str(newrecord))
file.close()

author = input("Which Author want you see? ")
file = open("Books.csv", "r")
reader = csv.reader(file)
count = 0
for row in file:
    if author in str(row):
        print(row)
        count = count+1
if count == 0:
    print("Theare are no books by that author in this list.")
file.close()


'''114
Using the Books.csv file, ask the user to enter a starting year and an end year. 
Display all books released between those two years.
'''
import csv
start = int(input("Enter the starting year"))
end = int(input("Enter the end year"))

file = list(csv.reader(open("Books.csv")))
tmp = []
for row in file:
    tmp.append(row)
#print(tmp)

x = 0
for row in tmp:
    if int(tmp[x][2])>= start and int(tmp[x][2]) <=end:
        print(tmp[x])
        x=x+1




'''115

Using the Books.csv file, display the data in the file along with the row number of each.
'''
import csv
file = open("Books.csv", "r")
x = 1
for row in file:
    print(str(x)+" - "+row )
    x=+1
file.close()


'''116
Import the data from the Books.csv file into a list.
Display the list to the user. Ask them to select which row from 
the list they want to delete and remove it from the list. 
Ask the user which data they want to change and allow them to change it. 
Write the data back to the original .csv file, overwriting the existing data with the amended data.
'''
import csv
file = list(csv.reader(open("Books.csv")))
booklist = []
for row in file:
    booklist.append(row)
#print(tmp)

x = 0
for row in booklist:
    print(x+1,booklist[x])
    x+=1
getId = int(input("Enter a row number to delete: "))
del booklist[getId]


x = 0
for row in booklist:
    print(x+1,booklist[x])
    x+=1
file.close()

alterId = int(input("Enter a row number to alter: "))
x=0
for row in booklist[alter-1]
    print(x+1,booklist[alter-1][x])
    x+=1

file = open("Books.csv", "w")
x = 0
for row in booklist:
    newrecord = booklist[x] [0] + ", " +booklist [x][1] + ", " +booklist [x][2] + "\n"
    file.write(newrecord)
    x = x+1
file.close()









'''117
Create a simple maths quiz that will ask the user for their name and then generate two random questions. 
Store their name, the questions they were asked, their answers and their final score in a .csv file. 
Whenever the program is run it should add to the .csv file and not overwrite anything.
'''

