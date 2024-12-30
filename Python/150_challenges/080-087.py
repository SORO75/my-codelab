'''80
Ask the user to enter their first name and then display the length of their first name.
Then ask for their surname and display the length of their surname.
Join their first name and surname together with a space between and display the result.
Finally, display the length of their full name (including the space).
'''

firstname = input('Enter your firstname')
print('the length of your firstname is: ', len(firstname))
surname = input('Enter your surname')
print('the length of your surname is: ', len(surname))

fullname = firstname + ' ' + surname

print(fullname)
print('the length of the fullname is:', len(fullname))

'''81
Ask the user to type in their favourite school subject. 
Display it with “-” after each letter, e.g. S-p-a-n-i-s-h-.
'''
subject = input('Enter your favourite school subject')
print('-'.join(subject))

'''82
Show the user a line of text from your favourite poem 
and ask for a starting and ending point.
Display the characters between those two points.
'''
poem = 'Now you are familiar with dealing with lists, strings should hold no ' \
       'problems for you as they use the same methods you have used with lists. ' \
       'However, I have included some additional code which may prove useful.'
startpoint = int(input('Enter a startpoint:'))
endpoint = int(input('Enter a startpoint: '))
print(poem[startpoint:endpoint])

'''83
Ask the user to type in a word in upper case. 
If they type it in lower case, ask them to try again. 
Keep repeating this until they type in a message all in uppercase.
'''
while True:
    user_input = input("Type in a word in upper case: ")
    if user_input.isupper():
        print("You entered:", user_input)
        break
    else:
        print("Try again. Please type the word in upper case.")

'''84
Ask the user to type in their postcode. Display the first two letters in uppercase.
'''

plz = input('Enter your postcode: ')
twoletter = plz[0:2]
print(twoletter.upper())

'''85
Ask the user to type in their name and then tell them how many vowels are in their name.
'''
user_name = input("Type in your name: ")

# Count the number of vowels in the name
vowel_count = sum(1 for char in user_name if char.lower() in 'aeiou')

print(f"There are {vowel_count} vowels in your name.")


'''86
Ask the user to enter a new password. 
Ask them to enter it again. If the two passwords match, display “Thank you”. 
If the letters are correct but in the wrong case, 
display the message “They must be in the same case”, 
otherwise display the message “Incorrect”.
'''
passwd1 = input('enter the password')
passwd2 = input('enter the password again')

if passwd1 == passwd2:
   print('Thank you')
elif passwd1.lower() == passwd2.lower():
   print('They must be in the same case')
else:
   print('Incorrect')


'''87
Ask the user to type in a word and then display it backwards on separate lines. 
For instance, if they type in “Hello” it should display as shown below:
Enter a word: Hello
o
l
l
e
H
'''

word = input('Enter a word:')

for i in word[::-1]:
    print(i)
