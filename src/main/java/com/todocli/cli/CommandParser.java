package com.todocli.cli;

import com.todocli.model.Task;
import com.todocli.service.TaskService;
import com.todocli.util.Color;

import java.util.List;

public class CommandParser {

    private static final TaskService service = new TaskService();
    private static final String help = """
                            +----------+----------------------------------+-----------------------------------------------+
                            | Command  | Description                      | Example                                       |
                            +----------+----------------------------------+-----------------------------------------------+
                            | create   | Create a new task                | todo-cli create "Market List" "Buy groceries" |
                            | update   | Update an existing task by ID    | todo-cli update 1 "To learn" "Learn C"        |
                            | delete   | Remove a task                    | todo-cli delete 1                             |
                            | search   | Search a task by keyword         | todo-cli find 1                               |
                            | find     | Find a task by ID                | todo-cli find 1                               |
                            | list     | List all tasks                   | todo-cli list                                 |
                            | list     | List tasks by status             | todo-cli list "todo"                          |
                            | mark     | Mark the status of a task        | todo-cli mark 1 "done"                        |
                            +----------+----------------------------------+-----------------------------------------------+
                            """;

    public static void execute(String[] args) {
        if (args.length == 0) {
            IO.println(help);
            return;
        }
        switch (args[0]) {
            case "create":
                if (args.length == 3) {
                    int id = service.create(args[1], args[2]);
                    CliConsole.println(Color.getGREEN()
                            + "Task created successfully (ID: " + id + ")"
                            + Color.getRESET()
                    );
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.\nTry: create <\"title\"> <\"description\">");
                }
                break;
            case "delete":
                if (args.length == 2) {
                    service.delete(Integer.parseInt(args[1]));
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.\nTry: delete <ID>");
                }
                break;
            case "update":
                if (args.length == 4) {
                    service.update(Integer.parseInt(args[1]), args[2], args[3]);
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.\nTry: update <ID> <\"new title\"> <\"new description\">");
                }
                break;
            case "mark":
                if (args.length == 3) {
                    service.mark(Integer.parseInt(args[1]), args[2]);
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.\nTry: mark <ID> <\"status (todo, done, in progress)\">");
                }
                break;
            case "find":
                if (args.length == 2) {
                    Task task = service.find(Integer.parseInt(args[1]));
                    CliConsole.println(task);
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.\nTry: find <ID>");
                }
                break;
            case "list":
                if (args.length == 1) {
                    List<Task> tasks = service.findAll();
                    CliConsole.printTasks(tasks);
                }
                else if (args.length == 2){
                    List<Task> tasksByStatus = service.findByStatus(args[1]);
                    CliConsole.printTasks(tasksByStatus);
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.");
                }
                break;
            case "search":
                if (args.length == 2) {
                    List<Task> tasksBySearch = service.searchByTitle(args[1]);
                    CliConsole.printTasks(tasksBySearch);
                }
                else {
                    CliConsole.printErr("Bad syntax. Use the help command to see available commands.\nTry: search <\"keyword\">");
                }
                break;
            case "help":
                if (args.length == 1) {
                    IO.println(help);
                }
                break;
        }
    }
}
