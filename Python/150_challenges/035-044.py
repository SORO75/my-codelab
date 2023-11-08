'''035
Ask the user to enter their name and then display their name three times
'''
name = input('enter your name:')
for i in range(0,3):
    print(name)

'''036
Alter program 035 so that it will ask the user to enter
their name and a number and then display their name that number of times.
'''
name = input('enter your name:')
num = int(input('Enter the number'))
for i in range(0,num):
    print(name)

'''037
Ask the user to enter their name and display each letter in their name on a separate line.
'''
name = input('enter your name:')
for i in name:
    print(i)


'''038
Change program 037 to also ask for a number. 
Display their name (one letter at a time on each line)
and repeat this for the number of times they entered.
'''
name = input('enter your name:')
num = int(input('Enter the number'))
for i in range(0, num):
    for n in name:
        print(n)

'''039
Ask the user to enter a number between 1 and 12 
and then display the times table for that number.
'''
num = int(input('Enter the number'))
for i in range(1, 13):
    answer = i*num
    print(i, 'x', num, '=', answer)


'''040
Ask for a number below 50 and then count down from 50 to that number, 
making sure you show the number they entered in the output.
'''
num = int(input('Enter the number below 50'))
for i in range(num, 0, -1):
    print(i)

'''041
Ask the user to enter their name and a number. 
If the number is less than 10, then display their name that number of times; 
otherwise display the message “Too high” three times.
'''
name = input('enter your name:')
num = int(input('Enter the number'))
if num < 10:
    for i in range(0, num):
        print(name)
else:
    for i in range(0, 3):
        print('Too high')


'''042
Set a variable called total to 0. 
Ask the user to enter five numbers and after each input ask them if they want that number included. 
If they do, then add the number to the total. 
If they do not want it included, don’t add it to the total. 
After they have entered all five numbers, display the total.
'''
total = 0
for i in range(0, 5):
    num = int(input('Enter a number'))
    answer = input('Do you want this number included? (y/n)')
    if answer == 'y':
        total += num
print(total)

'''043
Ask which direction the user wants to count (up or down). 
If they select up, then ask them for the top number and then count from 1 to that number. 
If they select down, ask them to enter a number below 20 and then count down from 20 to that number. 
If they entered something other than up or down, display the message “I don’t understand”.
'''
dir = input('Do you want to count up or down? (u/d)')
if dir == 'u':
    num = int(input('What is the top number?'))
    for i in range(1, num+1):
        print(i)
elif dir == 'd':
    num = int(input('Enter a number below 20: '))
    for i in range(20, num-1, -1):
        print(i)
else:
    print('I don\'t understand')



'''044
Ask how many people the user wants to invite to a party. 
If they enter a number below 10, ask for the names and after each name display “[name] has been invited”. 
If they enter a number which is 10 or higher, display the message “Too many people”.
'''
people = int(input('How many people were invited to the party?'))
if people < 10:
    for i in range (0, people):
        name = input('Enter a name:')
        print(name, 'has been invited')
else:
    print('Too many people')