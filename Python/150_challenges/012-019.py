'''012
Ask for two numbers.
If the first one is larger than the second,
display the second number first and then the first number,
otherwise show the first number first and then the second.
'''

first = int(input('Enter the first number'))
second = int(input('Enter the second number'))
if first > second:
    print(second, first)
else:
    print(first, second)

'''013
Ask the user to enter a number that is under 20. 
If they enter a number that is 20 or more, display the message “Too high”, 
otherwise display “Thank you”.
'''

num_20 = int(input('Enter the number under 20'))
if num_20 >= 20:
    print('too high')
else:
    print('Thank you')

'''014
Ask the user to enter a number between 10 and 20 (inclusive). 
If they enter a number within this range, 
display the message “Thank you”, otherwise display the message “Incorrect answer”.
'''

num_10_20 = int(input('Enter the number between 10 and 20'))
if 10 < num_10_20 < 20:
    print('Thank you')
else:
    print('Incorrect answer')

'''015
Ask the user to enter their favourite colour. 
If they enter “red”, “RED” or “Red” display the message “I like red too”, 
otherwise display the message “I don’t like [colour], I prefer red”.
'''

color = input('enter your favourite colour')
if color in ('red', 'RED', 'Red'):
    print('I like red too')
else:
    print('I don\'t like', color, 'I prefer red')

'''016
Ask the user if it is raining and convert their answer
to lower case so it doesn’t matter what case they type it in. 
If they answer “yes”, ask if it is windy. 
If they answer “yes” to this second question, 
display the answer “It is too windy for an umbrella”, 
otherwise display the message “Take an umbrella”. 
If they did not answer yes to the first question, display the answer “Enjoy your day”.
'''

raining = input('Is it raining?')
raining = str.lower(raining)
if raining == 'yes':
    windy = input('Is it windy?')
    windy = str.lower(windy)
    if windy == 'yes':
        print('it is too windy for an umbrella')
    else:
        print('take an umbrella')
else:
    print('Enjoy your day')





'''017
Ask the user’s age. If they are 18 or over, display the message “You can vote”, 
if they are aged 17, display the message “You can learn to drive”, 
if they are 16, display the message “You can buy a lottery ticket”, 
if they are under 16, display the message “You can go Trick- or-Treating”.

'''
age = int(input('how old are you?'))
if age >= 18:
    print('You can vote')
elif age == 17:
    print('you can learn to drive')
elif age == 16:
    print('You can buy a lottery ticket')
else:
    print('you can go Trick- or Treating')


'''018
Ask the user to enter a number. 
If it is under 10, display the message “Too low”, 
if their number is between 10 and 20, 
display “Correct”, otherwise display “Too high”.
'''

num = int(input('Enter a number'))
if num < 10:
    print('too low')
elif 10 <= num <= 20:
    print('Correct')
else:
    print('Too high')



'''019
Ask the user to enter 1, 2 or 3. 
If they enter a 1, display the message “Thank you”, 
if they enter a 2, display “Well done”, if they enter a 3, display “Correct”. 
If they enter anything else, display “Error message”.
'''

num = int(input('Enter 1, 2 or 3'))
if num == 1:
    print('Thank you')
elif num == 2:
    print('Well done')
elif num == 3:
    print('Correct')
else:
    print('Error message')
