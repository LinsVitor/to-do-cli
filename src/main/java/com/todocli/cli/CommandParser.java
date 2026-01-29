package com.todocli.cli;

import com.todocli.util.ErrorMessage;

public class CommandParser {

    private final Commands commands;

    public CommandParser(Commands commands) {
        this.commands = commands;
    }

    public void parser(String[] args) {
        if (args.length == 0) {
            commands.help();
            return;
        }

        switch (args[0]) {
            case "create":
                if (args.length > 1) {
                    commands.create(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "delete":
                if (args.length > 1) {
                    commands.delete(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "update":
                if (args.length > 1) {
                    commands.update(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "mark":
                if (args.length > 1) {
                    commands.mark(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.INVALID_ID.getMessage());
                }
                break;
            case "find":
                if (args.length > 1) {
                    commands.find(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "list":
                if (args.length >= 1) {
                    commands.list(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "search":
                if (args.length > 1) {
                    commands.search(args);
                }
                else {
                    CliConsole.printErr(ErrorMessage.BAD_SYNTAX.getMessage());
                }
                break;
            case "help":
                if (args.length == 1) {
                    commands.help();
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
