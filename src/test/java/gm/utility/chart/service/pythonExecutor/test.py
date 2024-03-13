import sys

total = 0
args = sys.argv[1]
print(args)
with open(args, "r") as file:
    # Read the entire contents of the file
    file_contents = file.read()
    print(file_contents)
    total = sum(int(arg) for arg in file_contents.split())
print(total)