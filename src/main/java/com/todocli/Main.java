package com.todocli;

import com.todocli.cli.CliConsole;
import com.todocli.cli.CommandParser;
import com.todocli.cli.Commands;
import com.todocli.exception.InvalidArgumentException;
import com.todocli.exception.ServiceException;
import com.todocli.repository.SQLiteRepository;
import com.todocli.service.TaskService;

public class Main {
    static void main(String[] args) {
        try {
            SQLiteRepository sqLiteRepository = new SQLiteRepository();
            TaskService taskService = new TaskService(sqLiteRepository);
            Commands commands = new Commands(taskService);
            CommandParser commandParser = new CommandParser(commands);
            commandParser.parser(args);
        }
        catch (ServiceException | InvalidArgumentException e) {
            CliConsole.printErr(e.getMessage());
        }
    }
}
