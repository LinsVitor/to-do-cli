package com.todocli.cli.commands;

import com.todocli.cli.Command;
import com.todocli.cli.CommandExecutor;

public record HelpCommand(CommandExecutor commandExecutor) implements Command {

    @Override
    public void execute() {
        commandExecutor.help();
    }
}
