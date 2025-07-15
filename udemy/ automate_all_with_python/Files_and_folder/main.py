from pathlib import Path
import zipfile
from datetime import datetime

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


#add created date to all filenames in folder

root_dir = Path('files')

for path in root_dir.glob("**/*"):
  if path.is_file():
    created_date = datetime.fromtimestamp(path.stat().st_ctime)
    created_date_str = created_date.strftime("%Y-%m-%d_%H:%M:%S")
    new_filename = created_date_str + '_' + path.name
    new_filepath = path.with_name(new_filename)
    path.rename(new_filepath)

# change file extensions
for path in root_dir.rglob("*.csv"):
    if path.is_file():
        new_filepath = path.with_suffix(".txt")
        path.rename(new_filepath)

# create empty files
for i in range(10, 21):
  filename = str(i) + '.txt'
  filepath = root_dir / Path(filename)
  filepath.touch()

# create archive from files

root_dir = Path('files')
archive_path = root_dir / Path ('archive.zip')

with zipfile.ZipFile(archive_path, 'w') as zf:
    for path in root_dir.rglob("*.txt"):
        zf.wirte(path)
        path.unlink()


# extract all zip files
root_dir = Path('.')
destionation_path = Path('destination')

for path in root_dir.glob("*.zip"):
    with zipfile.ZipFile(path, 'r') as zf:
        final_path =destionation_path / Path(path.stem)
        zf.extactall(path= final_path)

#seach file in computer
# destroy files forever


