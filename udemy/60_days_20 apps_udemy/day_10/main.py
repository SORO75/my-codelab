while True:
    user_action = input("Type add, show, complete or exit \n ")
    user_action = user_action.strip()

    if user_action.startswith('add'):
        todo = user_action[4:]
        #todo = input("Enter a todo: ") + "\n"
        with open('todos.txt', 'r') as file:
             todos = file.readlines()

        todos.append(todo + '\n')
        with open('todos.txt', 'w') as file:
            file.writelines(todos)

    elif user_action.startswith('show'):
        with open('todos.txt', 'r') as file:
            todos = file.readlines()
        # new_todos = [item.strip('\n') for item in todos]

        for index, item in enumerate(todos):
            item = item.strip('\n')
            row = f"{index+1}-{item}"
            print(row)

    elif user_action.startswith('edit'):
        try:
            number = int(user_action[5:])
            #number = int(input("Number of the todo to edit: "))
            number -= number

            with open('todos.txt', 'r') as file:
                todos = file.readlines()
            print('Here is todos exiting', todos)

            new_todo = input("Enter new todo: ")
            todos[number] = new_todo + '\n'

            with open('todos.txt', 'w') as file:
                file.writelines(todos)
        except ValueError:
            print("Your command is not valid.")
            continue


        print('Here is how it will be', todos)

    elif user_action.startswith('complete'):
        try:
            number = int(user_action[9:])
            #number = int(input("Number of the todo to complete: "))

            with open('todos.txt', 'r') as file:
                todos = file.readlines()
            index = number - 1
            todo_to_remove = todos[index].strip('\n')
            todos.pop(index)

            with open('todos.txt', 'w') as file:
                file.writelines(todos)
            message = f"Todo{todo_to_remove} was removed from the list."
            print(message)
        except IndexError:
            print("There is no itme with that number.")
            continue

    elif user_action.startswith('exit'):
        break
    else:
        print("Command is not valid")
print("Bye!")