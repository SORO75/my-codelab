alphabet_lower = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                  'u', 'v', 'w', 'x', 'y', 'z']

print(len(alphabet_lower))

direction = input("Type 'encode' to encrypt, type 'decode' to decrypt:\n").lower()
text = input("Type your message:\n").lower()
shift = int(input("Type the shift number\n"))


# TODO-1: create a function called 'encrypt()' that takes 'original_text' and 'shift_amount' as 2 inputs




# TODO-2: Inside the 'encrypt()' function, shift each letter of the 'original_text' forwards in the alphabet
#  by the shift mount and print the encrypted text

def encrypt(original_text, shift_amount):
    output_text = ""
    for l in original_text:
       # print(alphabet_lower.index(l))
      #  if enc_dec == "decode":
       #    shift_amount *= -1

        shifted_position = alphabet_lower.index(l)+shift_amount
        shifted_position %= len(alphabet_lower)
        output_text+=alphabet_lower[shifted_position]
    print(f"Here is the encoded result: {output_text}")


def decrypt(original_text, shift_amount):
    output_text = ""
    for l in original_text:
        shifted_position = alphabet_lower.index(l) - shift_amount
        shifted_position %= len(alphabet_lower)
        output_text += alphabet_lower[shifted_position]
    print(f"Here is the encoded result: {output_text}")

def caesar(original_text=text, shift_amount=shift, enc_dec=direction):
    output_text = ""
    if enc_dec =='decode':
        shift_amount *= -1
    for l in original_text:
        shifted_position = alphabet_lower.index(l) + shift_amount
        shifted_position %= len(alphabet_lower)
        output_text += alphabet_lower[shifted_position]
    print(f"Here is the encoded result: {output_text}")



encrypt(original_text=text, shift_amount=shift, enc_dec=direction)

#print(alphabet_lower[0])


# TODO-3: Call the 'encrypt()' function and  pass in the user inputs.
#  You should be able to test the code and encrypt a message

#TODO-4: What happens if you try to shift z forwards by 9? Can you fix the code?


