'''088
Ask the user for a list of five integers.
Store them in an array.
Sort the list and display it in reverse order.
'''

from array import *
nums = array('i', [])
for i in range(0,5):
    nums.append(int(input('Enter a number: ')))
nums = sorted(nums)
nums.reverse()

print(nums)

'''089
Create an array which will store a list of integers. 
Generate five random numbers and store them in the array. 
Display the array (showing each item on a separate line).
'''
from array import *
import random
nums = array('i', [])
for i in range (0,5):
    nums.append(random.randint(1,100))

for i in nums:
    print(i)

'''090
Ask the user to enter numbers. 
If they enter a number between 10 and 20, save it in the array, 
otherwise display the message “Outside the range”. 
Once five numbers have been successfully added, 
display the message “Thank you” and display the array 
with each item shown on a separate line.
'''
from array import *
nums = array('i', [])
while len(nums) < 5:
    num =(int(input('Enter a number: ')))
    if 10 <= num <= 20:
        nums.append(num)
    else:
        print('Outside the range')

print('Thank you')
for i in nums:
    print(i)


'''091
Create an array which contains five numbers 
(two of which should be repeated). Display the whole array to the user. 
Ask the user to enter one of the numbers from the array and 
then display a message saying how many times that number appears in the list.
'''

nums = array('i', [1,2,3,3,4])
print(nums)
num = (int(input('Select a number from the list: ')))
if num in nums:
    print('in the list is ', nums.count(num), 'times')
else:
    print('Out the range')


'''092
Create two arrays (one containing three numbers that the user enters 
and one containing a set of five random numbers). 
Join these two arrays together into one large array. 
Sort this large array and display it so that each number appears on a separate line.
'''
from array import *
import random

nums1 = array('i', [])
nums2 = array('i', [])

for i in range(5):
    nums1.append(random.randint(1,20))

while len(nums2) < 3:
    num =(int(input('Enter a number: ')))
    if 1 <= num <= 20:
        nums2.append(num)
    else:
        print('Outside the range')

nums3 = nums2+nums1
nums3 = sorted(nums3)
for i in nums3:
    print(i)



'''093
Ask the user to enter five numbers. 
Sort them into order and present them to the user. 
Ask them to select one of the numbers. 
Remove it from the original array and save it in a new array.
'''
from array import *
nums = array('i', [])
while len(nums) < 5:
    num =(int(input('Enter a number: ')))
    if 1 <= num <= 20:
        nums.append(num)
nums = sorted(nums)
for i in nums:
    print(i)

select = (int(input('Select a number from the list: ')))

nums.remove(select)
new_nums = nums
print(new_nums)


'''094
Display an array of five numbers. 
Ask the user to select one of the numbers. 
Once they have selected a number, display the position of that item in the array. 
If they enter something that is not in the array, 
ask them to try again until they select a relevant item.
'''
from array import *
nums = array('i', [1,2,3,3,4])
print(nums)

while True:
    num = (int(input('Select a number from the list: ')))
    if num in nums:
        print('The position of number in the list is:', nums.index(num))
        break
    else:
        print('the number is not in the list.\n Try again')



'''095
Create an array of five numbers between 10 and 100 
which each have two decimal places. 
Ask the user to enter a whole number between 2 and 5. 
If they enter something outside of that range, 
display a suitable error message and ask them to try again until they enter a valid amount. 
Divide each of the numbers in the array by the number the user entered 
and display the answers shown to two decimal places.
'''
from array import *
nums = array('f', [1.21, 2.33, 3.75, 3.64, 4.13])
print(nums)
num=0
while num < 2 or num > 5:
    num = int(input('Enter the number between 2 and 5'))
else:
    for i in nums:
        print(round(i/num,2))
