
'''001
Ask for the user’s first name and display the output message Hello [First Name] .
'''

name = input('What is your name?\n')
print('Hello', name)
print('Hello ' + name)
print()

'''002 
Ask for the user’s first name and then ask for their surname and 
display the output message Hello [First Name] [Surname].
'''
firstname = input('What is your firstname? ')
surname = input('What is your surname? ')
print('Hello', firstname, surname)


'''003
Write code that will display the joke “What do you call a bear with no teeth?”
and on the next line display the answer “A gummy bear!” 
Try to create it using only one line of code.

'''
print('What do you call a bear with no teeth?\nA gummy bear!')


'''004
Ask the user to enter two numbers.
Add them together and display the answer as The total is [answer].
'''
num1 = int(input('Enter your first number, please: '))
num2 = int(input('Enter your second number, please: '))
print('The sum of two numbers is:', num1+num2)


'''005
Ask the user to enter three numbers.
Add together the first two numbers and then multiply this total by the third. 
Display the answer as The answer is [answer].
'''
num1 = int(input('Enter your first number, please: '))
num2 = int(input('Enter your second number, please: '))
num3 = int(input('Enter your third number, please: '))
answer = (num1+num2)*num3
print('The answer is:', answer)


'''006
Ask how many slices of pizza the user started with and ask how many slices they have eaten. 
Work out how many slices they have left and display the answer in a user- friendly format.
'''

pizza_start = int(input('Enter the number of slices of pizza you started wich:'))
pizza_eaten = int(input('How many slices have you eaten?'))
slicesLeft = pizza_start - pizza_eaten
print(slicesLeft, 'pieces of pizza are left')

'''007
Ask the user for their name and their age. 
Add 1 to their age and display the output [Name] next birthday you will be [new age].
'''
name = input('What is your name?')
year = int(input('How old are you?'))
birthday = year + 1
print(name, ', next birthday you will be', birthday, 'old')


'''008
Ask for the total price of the bill, then ask how many diners there are. 
Divide the total bill by the number of diners and show how much each person must pay
'''
bill = int(input('wie hoch ist die Rechnung?'))
persons = int(input('Wie viele Personen haben zusammen gegessen?'))
pay = float(bill/persons)
print ('Jeder soll', pay, 'Euro zahlen')
'''009
Write a program that will ask for a number of days and 
then will show how many hours, minutes and seconds are in that number of days.
'''
days = int(input('Geben Sie Anzahl des Tages'))
hours = days*12
minutes = hours*60
seconds = minutes*60

print('In', days, 'days there are: ')
print(hours, 'hours')
print(minutes, 'minutes')
print(seconds, 'seconds')


'''010
There are 2,204 pounds in a kilogram. 
Ask the user to enter a weight in kilograms and convert it to pounds.
'''
weight_kg = int(input('Geben Sie die Anzahl des Kilos'))
weight_pg = float(weight_kg*2.204)
print ('In', weight_kg, 'sind', weight_pg,'Pfund')



'''011
Task the user to enter a number over 100 and then enter a number under 10 
and tell them how many times the smaller number goes into the larger number
in a user-friendly format.
'''
gros = int(input('Geben sie eine  Zahl grosser als 100 ein'))
klein = int(input('Geben Sie eine Zahl zwischen 1 und 10 ein'))
antwort = int(gros/klein)
print (klein, 'goes into', gros, antwort, 'times')