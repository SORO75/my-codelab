from converter14 import convert
from parser14 import parse

feet_inches = input("Enter feet and inches: ")

f, i = parse(feet_inches)
print("fi", f, i)
result = convert(f, i)

if result < 1:
    print("Kid is too small.")
else:
    print("Kid can user the slide.")

'''
def get_nr_items(user_input):
    words = user_input.split(',')

    return len(words)

print(get_nr_items("john,lisa, teresa"))




'''