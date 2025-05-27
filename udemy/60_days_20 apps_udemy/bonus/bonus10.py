try:
    wight = float(input("Enter rectangle width: "))
    length = float(input("Enter rectangle length:"))

    if wight == length:
        exit("That looks like a square.")
    area = wight *length
    print(area)
except ValueError:
    print("Please enter a number.")