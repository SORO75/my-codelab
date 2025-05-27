import random

rock = '''
    _______
---'   ____)
      (_____)
      (_____)
      (____)
---.__(___)
'''

paper = '''
    _______
---'   ____)____
          ______)
          _______)
         _______)
---.__________)
'''

scissors = '''
    _______
---'   ____)____
          ______)
       __________)
      (____)
---.__(___)
'''

game_images = [rock, paper, scissors]

user_choice = int(input("What do you choose?\n Type 1 for Rock, 2 for Paper or 3 for Scissors.\n"))


computer_choice = random.randint(1, 3)
print("Computer chose:")
print(game_images[computer_choice])

if user_choice >= 4 or user_choice < 1:
    print("You typed an invalid number, you lose!")
elif user_choice == 1 and computer_choice == 3:
    print("You win!")
elif computer_choice == 1 and user_choice == 3:
    print("You lose")
elif computer_choice > user_choice:
    print("You lose")
elif user_choice > computer_choice:
    print("You win!")
elif computer_choice == user_choice:
    print("It's a draw")