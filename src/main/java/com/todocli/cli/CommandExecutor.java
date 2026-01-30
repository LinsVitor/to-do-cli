package com.todocli.cli;

import com.todocli.exception.InvalidArgumentException;
import com.todocli.model.Task;
import com.todocli.service.TaskService;
import com.todocli.util.Color;
import com.todocli.util.ErrorMessage;

import java.util.List;

public class CommandExecutor {

    private final TaskService taskService;

    public CommandExecutor(TaskService taskService) {
        this.taskService = taskService;
    }

    public void create(String[] args) {
        if (args.length != 3) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ARGS.getMessage()
                    + "\nTry: create <\"title\"> <\"description\">"
            );
        }
        verifyField(args[1], "Title ");
        verifyField(args[2], "Description ");
        int id = taskService.create(args[1], args[2]);
        CliConsole.println(Color.GREEN + "Task created successfully (ID: " + id + ")" + Color.RESET);
    }

    public void delete(String[] args) {
        if (args.length != 2) {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage()
                    + "\nTry: delete <ID>"
            );
        }
        verifyField(args[1], "ID ");
        try {
            Task task = taskService.find(Integer.parseInt(args[1]));
            if (task == null) {
                CliConsole.printErr(ErrorMessage.NULL_TASK.getMessage());
                return;
            }
            String choice = IO.readln(Color.YELLOW + "You are sure? (yes/no) " + Color.RESET);
            while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("no") && !choice.equalsIgnoreCase("n")) {
                choice = IO.readln(Color.YELLOW + "You are sure? (yes/no) " + Color.RESET);
            }
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                taskService.delete(Integer.parseInt(args[1]));
            }
            else {
                CliConsole.println("Operation is aborted");
            }
        }
        catch (NumberFormatException e) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ID.getMessage());
        }
    }

    public void update(String[] args) {
        if (args.length != 4) {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage()
                    + "\nTry: update <ID> <\"title\"> <\"description\">"
            );
        }
        verifyField(args[1], "ID ");
        verifyField(args[2], "Title ");
        verifyField(args[3], "Description ");
        try {
            taskService.update(Integer.parseInt(args[1]), args[2], args[3]);
        }
        catch (NumberFormatException e) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ID.getMessage());
        }
    }

    public void mark(String[] args) {
        if (args.length != 3) {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage()
                    + "\nTry: mark <ID> <\"status\">"
            );
        }
        verifyField(args[1], "ID ");
        try {
            taskService.mark(Integer.parseInt(args[1]), args[2]);
        }
        catch (NumberFormatException e) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ID.getMessage());
        }
    }

    public void find(String[] args) {
        if (args.length != 2) {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage()
                    + "\nTry: find <ID>"
            );
        }
        verifyField(args[1], "ID ");
        try {
            Task task = taskService.find(Integer.parseInt(args[1]));
            if (task == null) {
                CliConsole.printErr(ErrorMessage.NULL_TASK.getMessage());
                return;
            }
            CliConsole.println(task);
        }
        catch (NumberFormatException e) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ID.getMessage());
        }
    }

    public void list(String[] args) {
        if (args.length == 1) {
            List<Task> tasks = taskService.findAll();
            CliConsole.printTasks(tasks);
        }
        else if (args.length == 2) {
            List<Task> tasks = taskService.findByStatus(args[1]);
            CliConsole.printTasks(tasks);
        }
        else {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage()
                    + "\nTry: list <\"status\">"
            );
        }
    }

    public void search(String[] args) {
        if (args.length != 2) {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage()
                    + "\nTry: search <\"keyword\">"
            );
        }
        verifyField(args[1], "Search ");
        List<Task> tasks = taskService.searchOnTitle(args[1]);
        CliConsole.printTasks(tasks);
    }

    public void help() {
        final String help = """
                            +----------+----------------------------------+--------------------------------------------+
                            | Command  | Description                      | Example                                    |
                            +----------+----------------------------------+--------------------------------------------+
                            | create   | Create a new task                | to-do create "Market List" "Buy groceries" |
                            | update   | Update an existing task by ID    | to-do update 1 "To learn" "Learn C"        |
                            | delete   | Remove a task                    | to-do delete 1                             |
                            | search   | Search a task by keyword         | to-do search "keyword"                     |
                            | find     | Find a task by ID                | to-do find 1                               |
                            | list     | List all tasks                   | to-do list                                 |
                            | list     | List tasks by status             | to-do list "todo"                          |
                            | mark     | Mark the status of a task        | to-do mark 1 "done"                        |
                            +----------+----------------------------------+--------------------------------------------+
                            """;

        CliConsole.println(help);
    }

    private void verifyField(String field, String nameField) {
        if (field.trim().isEmpty()) {
            throw new InvalidArgumentException(nameField + " " + ErrorMessage.EMPTY_FIELD.getMessage());
        }
        if (field.equalsIgnoreCase("null")) {
            throw new InvalidArgumentException(nameField + " " + ErrorMessage.NULL_FIELD.getMessage());
        }
    }
}
