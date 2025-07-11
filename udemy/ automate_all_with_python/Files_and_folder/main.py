from pathlib import Path

p1 = Path('files/abc.txt')
print(type(p1))

with open(p1, 'r') as file:
    print(file.read())

if not p1.exists():
    with open(p1, 'w') as file:
        file.write('Content 3')

print(p1.name)
print(p1.stem)
print(p1.suffix)


p2 = Path('files')
print(list[p2.iterdir()])


# add prefix to all filenames in folder
