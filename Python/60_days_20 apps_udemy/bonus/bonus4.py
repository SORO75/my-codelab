filenames = ["1.New Data.txt", "2.Reports.txt", "3.Presentation.txt"]

for filename in filenames:
    filename = filename.replace('.', '-', 1)
    print(filename)