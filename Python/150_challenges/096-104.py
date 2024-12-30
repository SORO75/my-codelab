'''096
Create the following using a simple 2D list using the standard Python indexing:
2   5   8
3   7   4
1   6   9
4   2   0
'''

new_list = [[2,5,8], [3,7,4], [1,6,9], [4,2,0]]
for i in new_list:
    print(i)



'''097
Using the 2D list from program 096, 
ask the user to select a row and a column and display that value.
'''
new_list = [[2,5,8], [3,7,4], [1,6,9], [4,2,0]]
for i in new_list:
    print(i)

row =int(input("Choice the rows number from 1 to 4, please"))
col =int(input("Choice the columns number from 1 to 3, please"))
#print (row, col)
print(new_list[row-1][col-1])




'''098
Using the 2D list from program 096, 
ask the user which row they would like displayed and display just that row. 
Ask them to enter a new value and add it to the end of the row and display the row again.
'''
new_list = [[2,5,8], [3,7,4], [1,6,9], [4,2,0]]
for i in new_list:
    print(i)
row =int(input("Choice the rows number from 1 to 4, please"))
print(new_list[row-1])
add = int(input("Enter the new number, please"))
new_list[row-1].append(add)
print(new_list[row-1])

'''099
Change your previous program
to ask the user which row they want displayed. Display that row. 
Ask which column in that row they want displayed and display the value that is held there. 
Ask the user if they want to change the value. 
If they do, ask for a new value and change the data. Finally, display the whole row again.
'''
new_list = [[2,5,8], [3,7,4], [1,6,9], [4,2,0]]
for i in new_list:
    print(i)
row =int(input("Choice the rows number from 1 to 4, please"))
print(new_list[row-1])
col = int(input("Choice the column number from the row"))
print(new_list[row-1][col-1])

answer = input('Do you want change this number? (y/n)')
if answer == 'y':
    new_number = int(input("Enter the new numb, please"))
    new_list[row - 1][col - 1]= new_number

print(new_list[row-1])

'''100
Create the following using a 2D dictionary showing the sales each person
has made in the different geographical regions:

        N       S       E       W
John    3056    8463    8441    2694
Tom     4832    6786    4737    3612
Anne    5239    4802    5820    1859
Fiona   3904    3645    8821    2451
'''

sales = {"John":{"N":3056, "S":8463, "E":8441, "W":2694},"Tom":{"N":4832, "S":6786, "E":4737, "W":3612},
           "Anne":{"N":5239, "S":4802, "E":5820, "W":1859}, "Fiona":{"N":3904, "S":3645, "E":8821, "W":2451}}

for i in sales:
    print(i, sales[i])

'''101
Using program 100, ask the user for a name and a region. 
Display the relevant data. Ask the user for the name and 
region of data they want to change and allow them to make the alteration to the sales figure. 
Display the sales for all regions for the name they choose.
'''
sales = {"John":{"N":3056, "S":8463, "E":8441, "W":2694},"Tom":{"N":4832, "S":6786, "E":4737, "W":3612},
           "Anne":{"N":5239, "S":4802, "E":5820, "W":1859}, "Fiona":{"N":3904, "S":3645, "E":8821, "W":2451}}

name = input('Enter the Name of person:')
region = input('Select the region: ')
print(sales[name][region])
new_data = int(input('Enter new data: '))
sales[name][region]=new_data
print(name, sales[name])

'''102
Ask the user to enter the name, age and shoe size for four people. 
Ask for the name of one of the people in the list and display their age and shoe size.
'''
people ={}
for i in range (0,4):
    name = input ("Enter name:")
    age = int(input("Enter age:"))
    shoe = int(input("Enter shoe size:"))
    people[name] = {"Age":age, "Shoe size": shoe}

ask = input ("Enter name:")
print(people[ask])



'''103
Adapt program 102 to display the names 
and ages of all the people in the list but do not show their shoe size.
'''
people = {}
for i in range(0, 4):
    name = input("Enter name:")
    age = int(input("Enter age:"))
    shoe = int(input("Enter shoe size:"))
    people[name] = {"Age": age, "Shoe size": shoe}
print(people)

for name in people:
    print(list[name]["Age"])
'''104
After gathering the four names, ages and shoe sizes, 
ask the user to enter the name of the person they want to remove from the list. 
Delete this row from the data and display the other rows on separate lines.
'''

people = {}
for i in range(0, 4):
    name = input("Enter name:")
    age = int(input("Enter age:"))
    shoe = int(input("Enter shoe size:"))
    people[name] = {"Age": age, "Shoe size": shoe}
print(people)

getId = input("Who do you want to remobe from the list?")
del list[getId]
for name in people:
    print((name),list[name]["Age"], list[name]["Shoe size"])
