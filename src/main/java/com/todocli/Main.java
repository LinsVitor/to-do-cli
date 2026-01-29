package com.todocli;

import com.todocli.cli.CliConsole;
import com.todocli.cli.CommandExecutor;
import com.todocli.cli.CommandParser;
import com.todocli.exception.DbException;
import com.todocli.exception.InvalidArgumentException;
import com.todocli.exception.ServiceException;
import com.todocli.repository.SQLiteRepository;
import com.todocli.service.TaskService;

public class Main {
    public static void main(String[] args) {
        try {
            SQLiteRepository sqLiteRepository = new SQLiteRepository();
            TaskService taskService = new TaskService(sqLiteRepository);
            CommandExecutor commandExecutor = new CommandExecutor(taskService);
            CommandParser commandParser = new CommandParser(commandExecutor);
            commandParser.parser(args);
        }
        catch (ServiceException | InvalidArgumentException | DbException e) {
            CliConsole.printErr(e.getMessage());
        }
    }
}
