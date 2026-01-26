package com.todocli;

import com.todocli.cli.CommandParser;
import com.todocli.service.ServiceException;

public class Main {
    static void main(String[] args) {
        try {
            CommandParser.execute(args);
        }
        catch (ServiceException e) {
            IO.println(e.getMessage());
        }
    }
}
