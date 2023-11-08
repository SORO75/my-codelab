'''
020
Ask the user to enter their first name and then display the length of their name
'''

firstname = input('What ist your firstname?')
length = len(firstname)
print('The firstname is', length, 'long')

'''021
Ask the user to enter their first name and then ask them to enter their surname. 
Join them together with a space between and display the name and the length of whole name.
'''

firstname = input('What ist your firstname?')
surname = input('What ist your surname?')
name = firstname + ' ' + surname
length2 = len(name)
print(name, ',', length2)

'''022
Ask the user to enter their first name and surname in lower case. 
Change the case to title case and join them together. 
Display the finished result.
'''
firstname = input('Enter your firstname in lowercase')
surname = input('Enter your surname in lowercase')
firstname = firstname.title()
surname = surname.title()
name = firstname + ' ' + surname
print(name)


'''023
Ask the user to type in the first line of a nursery rhyme
and display the length of the string. 
Ask for a starting number and an ending number and then display
just that section of the text (remember Python starts counting from 0 and not 1).
'''
phrase = input('Enter a nursery rhyme: ')
length = len(phrase)
print(length)
str = int(input('Enter the startnumber of the string'))
end = int(input('Enter the endnumber of the string'))

print(phrase[str+1:end+1])

'''024
Ask the user to type in any word and display it in upper case.
'''

word = input('enter a word:')
word = word.upper()
print(word)

'''025
Ask the user to enter their first name. 
If the length of their first name is under five characters, 
ask them to enter their surname and join them together 
(without a space) and display the name in upper case. 
If the length of the first name is five or more characters, 
display their first name in lower case.
'''

firstname = input('Enter your firstname')
if len(firstname) < 5:
    surname = input('Enter your surname')
    name = firstname.join(surname)
    print(name.upper())
else:
    print(firstname.lower())


'''026
Pig Latin takes the first consonant of a word, 
moves it to the end of the word and adds on an “ay”. 
If a word begins with a vowel you just add “way” to the end. 
For example, pig becomes igpay, banana becomes ananabay, 
and aadvark becomes aadvarkway. Create a program 
that will ask the user to enter a word and change it into Pig Latin. 
Make sure the new word is displayed in lower case.
'''

word = input('Enter a word: ')
if word[0] not in ['a','e','i','o','u']:
    newword = word[1:] + word[0] + 'ay'
else:
    newword = word + 'way'
print(newword.lower())
