'''069
Create a tuple containing the names of five countries and display the whole tuple.
Ask the user to enter one of the countries that have been shown to them and
then display the index number (i.e. position in the list) of that item in the tuple.
'''

countries = ('USA', 'Germany', 'Canada', 'China', 'Italy')
print(countries)
country = input('Entry the country')
if country in countries:
    print(countries.index(country))
else:
    print('not in the list')

'''070
Add to program 069 to ask the user to enter a number
and display the country in that position.
'''

countries = ('USA', 'Germany', 'Canada', 'China', 'Italy')
print(countries)
num = int(input('Entry a number between 0 und 4'))
print(countries[num])

'''071
Create a list of two sports. Ask the user what their favourite sport
is and add this to the end of the list. 
Sort the list and display it.
'''
sports=['Basketball', 'Tennis']
print(sports)
sports.append(input('What is your favorite sport: '))
print(sorted(sports))

'''072
Create a list of six school subjects. 
Ask the user which of these subjects they don’t like. 
Delete the subject they have chosen from the list before you display the list again.
'''
subjects=['Math', 'Sport', 'Bio', 'Chemistry', 'English']
print(subjects)
subjects.remove(input('What subject don\'t you like'))
print(subjects)

'''073
Ask the user to enter four of their favourite foods and store them
in a dictionary so that they are indexed with numbers starting
from 1. Display the dictionary in full, showing the index number and the item. 
Ask them which they want to get rid of and remove it from the list. 
Sort the remaining data and display the dictionary.
'''
foods = {}
for i in range(1,5):
    food = input('Enter your favourite food')
    foods[i] = food
#print('There are foods', foods)
print('There are your favorite foods:')
for key, value in foods.items():
    print(key, value)

key = int(input('Do you want delete ahy?'))

if key in foods:
    del foods[key]
print('there your favorite foods',sorted(foods.values()))


'''074
Enter a list of ten colours. Ask the user for a starting number 
between 0 and 4 and an end number between 5 and 9. 
Display the list for those colours 
between the start and end numbers the user input.
'''
colors = ['blue', 'yellow', 'red', 'black', 'white', 'green', 'brown', 'purple', 'grey', 'lila']

start = int(input('Enter the start number between 0 and 4'))
end = int(input('Enter the end number between 5 and 9:'))

print('Colors: ', colors[start:end])


'''075
Create a list of four three-digit numbers. 
Display the list to the user, showing each item from the list on a separate line. 
Ask the user to enter a three-digit number. 
If the number they have typed in matches one in the list, 
display the position of that number in the list, 
otherwise display the message “That is not in the list”
'''
numbers =[234,345,456,567]

for i in numbers:
    print(i)
num = int(input('Enter a three-digit number'))

if num in numbers:
    print('the position of number is:', numbers.index(num))
else:
    print('that is not in the list')

'''076
Ask the user to enter the names of three people they
want to invite to a party and store them in a list. 
After they have entered all three names, ask them if they want to add another. 
If they do, allow them to add more names until they answer “no”. 
When they answer “no”, display how many people they have invited to the party.
'''
names = []
for i in range(3):
    names.append(input('Add a name'))
print(names)

answer = input('Do you want to add another name (y/n): ')
while answer != 'n':
        names.append(input('Add a name'))
        answer = input('Do you want to add another name:')
print('You have', len(names), 'people coming to you party')

'''077
Change program 076 so that once the user has completed their list of names, 
display the full list and ask them to type in one of the names on the list. 
Display the position of that name in the list. 
Ask the user if they still want that person to come to the party. 
If they answer “no”, delete that entry from the list and display the list again.
'''
names = []
for i in range(3):
    names.append(input('Add a person'))
print(names)

answer = input('Do you want to add another name (y/n): ')
while answer != 'n':
        names.append(input('Add a name'))
        answer = input('Do you want to add another name:')
print('You have', len(names), 'people coming to you party')
print(names)
selection = input('Enter one the names: ')
print(selection, 'is in position', names.index(selection), 'on the list')
ans = input ('Do you still want them to come (y/n): ')
if ans == 'n':
    names.remove(selection)
print(names)


'''078
Create a list containing the titles of four TV programmes 
and display them on separate lines. Ask the user to enter another show and a position
they want it inserted into the list. 
Display the list again, showing all five TV programmes in their new positions.
'''

TVs = ['SWR', 'ARD','ZDF', 'MTV']
for i in TVs:
    print (i)
print()
newTV = input('Enter another TV show:')
position = int(input('Enter a number between and 4: '))
TVs.insert(position, newTV)
for i in TVs:
    print (i)


'''079
Create an empty list called “nums”. 
Ask the user to enter numbers. 
After each number is entered, add it to the end of the nums list and display the list. 
Once they have entered three numbers, 
ask them if they still want the last number they entered saved. 
If they say “no”, remove the last item from the list. 
Display the list of numbers.
'''

nums = []
for i in range(3):
    nums.append(int(input('Enter a number: ')))
    print(nums)

ans = input('Do want to save the last number (y/n):')
if ans == 'n':
    nums.pop()
print(nums)
