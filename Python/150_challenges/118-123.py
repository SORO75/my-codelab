'''118
Define a subprogram that will ask the user to enter a number and save it as the variable “num”.
Define another subprogram that will use “num” and count from 1 to that number.
'''

'''Define a subprogram that will ask the user to pick a low and a high number, and then generate a random number between those two values and store it in a variable called “comp_num”.
Define another subprogram that will give the instruction “I am thinking of a number...” and then ask the user to guess the number they are thinking of.
Define a third subprogram that will check to see if the comp_num is the same as the user’s guess. If it is, it should display the message “Correct, you win”, otherwise it should keep looping, telling the user if they are too low or too high and asking them to guess again until they guess correctly.

'''

'''120
Display the following menu to the user:
1) Addition
2) Substraction
Enter 1 or 2

If they enter a 1, it should run a subprogram that will generate two random numbers between 5 and 20, and ask the user to add them together. Work out the correct answer and return both the user’s answer and the correct answer.
If they entered 2 as their selection on the menu, it should run a subprogram that will generate one number between 25 and 50 and another number between 1 and 25 and ask them to work out num1 minus num2. This way they will not have to worry about negative answers. Return both the user’s answer and the correct answer.
Create another subprogram that will check if the user’s answer matches the actual answer. If it does, display “Correct”, otherwise display a message that will say “Incorrect, the answer is” and display the real answer.
If they do not select a relevant option on the first menu you should display a suitable message.
'''


'''121
Create a program that will allow the user to easily manage a list of names. 
You should display a menu that will allow them to add a name to the list, 
change a name in the list, delete a name from the list or view all the names in the list. 
There should also be a menu option to allow the user to end the program. 
If they select an option that is not relevant, then it should display a suitable message. 
After they have made a selection to either add a name, change a name, delete a name or view all the names, 
they should see the menu again without having to restart the program. 
The program should be made as easy to use as possible.
'''

'''122
Create the following menu:
1) Add to file
2) View all records
3) Quit program

Enter the number of your selection:

If the user selects 1, allow them to add to a file called Salaries.csv which will store their name and salary. If they select 2 it should display all records in the Salaries.csv file. If they select 3 it should stop the program. If they select an incorrect option they should see an error message. 
They should keep returning to the menu until they select option 3.
'''

'''123
n Python, it is not technically possible to directly delete a record from a .csv file. Instead you need to save the file to a temporary list in Python, make the changes to the list and then overwrite the original file with the temporary list.
Change the previous program to allow you to do this. Your menu should now look like this:

1) Add to file
2) View all records
3) Delete a record
4) Quit program

Enter the number of your selection:
'''