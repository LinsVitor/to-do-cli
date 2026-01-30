package com.todocli.cli.commands;

import com.todocli.cli.Command;
import com.todocli.cli.CommandExecutor;

public record FindCommand(CommandExecutor commandExecutor, String[] args) implements Command {

    @Override
    public void execute() {
        commandExecutor.find(args);
    }
}
