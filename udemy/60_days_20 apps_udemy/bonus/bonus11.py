def get_average():
    with open("files/data.txt", "r") as file:
        data = file.readlines()

    values = data[1:]
    values = [float(i) for i in values]

    average_local =sum(values) / len(values)
    return average_local

average = get_average()
print(average)


############################################
#exercise
###########################################

def format_filename():
    filename = "report.txt"
    filename = filename[:-4].capitalize()
    return filename
print(format_filename())



########################################

def get_maximum():
    celsius = [14, 15.1, 12.3]
    maximum = max(celsius)
    return (maximum)


celsius = get_maximum()
fahrenheit = celsius * 1.8 + 32

print(fahrenheit)