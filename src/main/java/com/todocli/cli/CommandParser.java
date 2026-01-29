package com.todocli.cli;

import com.todocli.util.ErrorMessage;

public class CommandParser {

    private final CommandExecutor commandExecutor;

    public CommandParser(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void parser(String[] args) {
        if (args.length == 0) {
            commandExecutor.help();
            return;
        }

        switch (args[0]) {
            case "create":
                if (args.length > 1) {
                    commandExecutor.create(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "delete":
                if (args.length > 1) {
                    commandExecutor.delete(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "update":
                if (args.length > 1) {
                    commandExecutor.update(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "mark":
                if (args.length > 1) {
                    commandExecutor.mark(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.INVALID_ID.getMessage());
                }
                break;
            case "find":
                if (args.length > 1) {
                    commandExecutor.find(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "list":
                if (args.length >= 1) {
                    commandExecutor.list(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "search":
                if (args.length > 1) {
                    commandExecutor.search(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "help":
                if (args.length == 1) {
                    commandExecutor.help();
                }
                break;
            default:
                if (args.length == 1) {
                    CliConsole.printErr(ErrorMessage.INVALID_COMMAND);
                }
                break;
        }
    }
}
