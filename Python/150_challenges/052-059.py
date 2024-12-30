'''052
Display a random integer between 1 and 100 inclusive.
'''
import random
num = random.randint(1, 100)
print(num)

'''053
Display a random fruit from a list of five fruits.
'''
import random
fruit = random.choice(['banana', 'apple', 'melon', 'mango', 'cherry'])
print(fruit)

'''054
Randomly choose either heads or tails (“h” or “t”). Ask the user to make their choice. 
If their choice is the same as the randomly selected value, 
display the message “You win”, otherwise display “Bad luck”. 
At the end, tell the user if the computer selected heads or tails.
'''
import random
var = random.choice(['h','t'])

var2 = input('Choice the tail or heads (t/h)')
if var2 == var:
    print('You win')
else:
    print('Bad luck')
if var == 'h':
    print('It\'s was head')
else:
    print('It\'s was tail')

'''055
Randomly choose a number between 1 and 5. Ask the user to pick a number. 
If they guess correctly, display the message “Well done”, 
otherwise tell them if they are too high or too low and ask them to pick a second number. 
If they guess correctly on their second guess, display “Correct”, otherwise display “You lose”.
'''
import random
num = random.randint(1 , 5)
guess = int(input('Enter a number between 1 and 5'))

if num == guess:
    print('Well done')
elif guess > num:
    print('the number is too high')
    guess = int(input('Enter a number between 1 and 5 again'))
    if num == guess:
        print('correct')
    else:
        print('You lose')
elif guess < num:
    print('the number is too low')
    guess = int(input('Enter a number between 1 and 5 again'))
    if num == guess:
        print('correct')
    else:
        print('You lose')




'''056
Randomly pick a whole number between 1 and 10. 
Ask the user to enter a number and keep entering numbers 
until they enter the number that was randomly picked.
'''
import random
num = random.randint(1, 10)
print(num)
num2 = False
while num2 == False:
    guess = int(input('Choice the number between 1 and 10'))
    if guess == num:
        num2 = True
        print('the number is correct')

'''057
Update program 056 so that it tells the user 
if they are too high or too low before they pick again.
'''

import random
num = random.randint(1, 10)
print(num)
num2 = False
while num2 == False:
    guess = int(input('Choice the number between 1 and 10'))
    if guess == num:
        num2 = True
        print('the number is correct')
    elif guess > num:
        print('too high')
    else:
        print('too low')



'''058
Make a maths quiz that asks five questions by randomly generating two whole numbers
to make the question (e.g. [num1] + [num2]). 
Ask the user to enter the answer. 
If they get it right add a point to their score. 
At the end of the quiz, tell them how many they got correct out of five
'''
import random
score = 0
for i in range(1, 6):
    num1 = random.randint(1, 50)
    num2 = random.randint(1, 50)
    print(num1, '+', num2, '=' )
    answer = int(input('Enter the answer'))
    print()
    if answer == num1 + num2:
        score += 1
    else:
        print('wrong answer')
print(score, 'from 5 are correct')


'''059
Display five colours and ask the user to pick one. 
If they pick the same as the program has chosen, say “Well done”, 
otherwise display a witty answer which involves the correct colour, e.g. 
“I bet you are GREEN with envy” or “You are probably feeling BLUE right now”. 
Ask them to guess again; if they have still not got it right, 
keep giving them the same clue and ask the user to enter a colour until they guess it correctly.
'''
