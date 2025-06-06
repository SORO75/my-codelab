import random

from hangman_words import words

chosen_word = random.choice(words)
word_length = len(chosen_word)

end_of_game = False
lives = 6

from hangman_art import logo
from hangman_art import welcome
print(welcome)
print(logo)

display = []
for _ in range(word_length):
    display += "_"

while not end_of_game:
    guess = input("Guess a letter: ").lower()

    if guess in display:
        print(f"You've already guessed {guess}")


    for position in range(word_length):
        letter = chosen_word[position]
        if letter == guess:
            display[position] = letter

    if guess not in chosen_word:
        lives -= 1
        print(f"You guessed {guess}, that's not in the word. You lose a life.",lives,"/6")
        if lives == 0:
            end_of_game = True
            print("You lose.")
            print("The word was:",chosen_word)

    print(f"{' '.join(display)}")

    if "_" not in display:
        end_of_game = True
        print("You win.")


    from hangman_art import stages
    print(stages[lives])