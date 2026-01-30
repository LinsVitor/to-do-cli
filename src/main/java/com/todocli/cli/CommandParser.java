package com.todocli.cli;

import com.todocli.cli.commands.*;
import com.todocli.util.ErrorMessage;

import java.util.Map;

public class CommandParser {

    private final CommandExecutor commandExecutor;

    public CommandParser(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void parser(String[] args) {
        if (args.length >= 1) {
            Map<String, Command> commandMap = Map.ofEntries(
                    Map.entry("create", new CreateCommand(commandExecutor, args)),
                    Map.entry("delete", new DeleteCommand(commandExecutor, args)),
                    Map.entry("update", new UpdateCommand(commandExecutor, args)),
                    Map.entry("mark", new MarkCommand(commandExecutor, args)),
                    Map.entry("find", new FindCommand(commandExecutor, args)),
                    Map.entry("list", new ListCommand(commandExecutor, args)),
                    Map.entry("search", new SearchCommand(commandExecutor, args)),
                    Map.entry("help", new HelpCommand(commandExecutor))
            );
            if (commandMap.containsKey(args[0].trim().toLowerCase())) {
                commandMap.get(args[0].trim().toLowerCase()).execute();
            }
            else {
                throw new IllegalArgumentException(ErrorMessage.BAD_SYNTAX.getMessage());
            }
        }
        else {
            throw new IllegalArgumentException(ErrorMessage.BAD_SYNTAX.getMessage());
        }
    }
}
