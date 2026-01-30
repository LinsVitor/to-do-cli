package com.todocli.cli.commands;

import com.todocli.cli.Command;
import com.todocli.cli.CommandExecutor;

public record MarkCommand(CommandExecutor commandExecutor, String[] args) implements Command {

    @Override
    public void execute() {
        commandExecutor.mark(args);
    }
}
