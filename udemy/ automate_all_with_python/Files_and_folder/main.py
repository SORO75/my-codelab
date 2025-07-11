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

root_dir = Path('files')
file_paths = root_dir.iterdir()
print(Path.cwd())

for path in file_paths:
    new_filename =  "new-" + path.stem +path.suffix
    new_filepath = path.with_name(new_filename)
    print(new_filepath)
    path.rename(new_filepath)

# rename all files based on folder

file_paths = root_dir.glob("**/*")

for path in file_paths:
    if path.is_file():
        parent_folder = path.parts[-2]
        new_filename = parent_folder + '-'+ path.name
        path.rename(new_filepath)
        print(parent_folder)


