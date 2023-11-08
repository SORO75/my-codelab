'''027
Ask the user to enter a number with lots of decimal places.
Multiply this number by two and display the answer.
'''

num = float(input('Enter a number with lots of decimal places '))
print(num*2)

'''028
Update program 027 so that it will display the answer to two decimal places.
'''
num = float(input('Enter a number with lots of decimal places '))
num = round(num*2, 2)
print(num)

'''029
Ask the user to enter an integer that is over 500. 
Work out the square root of that number and display it to two decimal places.
'''
import math
num = int(input('Enter an integer that is over 500'))
answer = math.sqrt(num)
print(round(answer, 2))


'''030
Display pi (π) to five decimal places.
'''
import math
new_pi = round(math.pi,5)
print(new_pi)

'''031
Ask the user to enter the radius of a circle (measurement from the centre point to the edge). 
Work out the area of the circle (π*radius2).
'''
import math
radius = float(input('Enter the radius of a circle:'))
area = round(math.pi*radius*2, 2)

print(area)


'''032
Ask for the radius and the depth of a cylinder and work out the total volume (circle area*depth) rounded to three decimal places.
'''
import math
radius = float(input('Enter the radius of a cylinder:'))
depth = float(input('Enter the depth of a cylinder:'))
area = round(math.pi*radius*2*depth, 2)
print('The area of cylinder is:', area)

'''033
Ask the user to enter two numbers. 
Use whole number division to divide the first number by the second
and also work out the remainder and display the answer in a user-friendly way 
(e.g. if they enter 7 and 2 display “7 divided by 2 is 3 with 1 remaining”).
'''
import math
num1 = int(input('Enter a  first number'))
num2 = int(input('Enter a second number'))

div1 = num1//num2
div2 = num1%num2

print(num1,'divided by', num2, 'is', div1, 'with', div2, 'remaining')


'''034
Display the following message:
1) Square
2) Triangle

Enter a number:
If the user enters 1, then it should ask them for the length of one of its sides and display the area. 
If they select 2, it should ask for the base and height of the triangle and display the area. 
If they type in anything else, it should give them a suitable error message.
'''

print('1) Square\n2) Triangle\nchoose a number for a figure')
selection = int(input('Enter the number'))
if selection == 1:
   side = float(input('Enter the lenght of one side:'))
   area = side**2
   print ('The area of your chosen shape is', area)
elif selection == 2:
    base = float(input('Enter the length of the base: '))
    height = float(input('enter the height of the triangle: '))
    area = (base*height)/2
    print('The area of your shosen shape is', area)
else:
    print('Incorrect enter')
