package com.todocli.util;

public enum ErrorMessage {
    BAD_SYNTAX("Bad syntax. Use the help command to see available commands."),
    INVALID_ID("ID must be a numeric value."),
    INVALID_COMMAND("Invalid command. Use the help command to see available commands."),
    INVALID_ARGS("Invalid argument(s)."),
    EMPTY_FIELD("can't be empty."),
    EMPTY_TASK_LIST("No tasks found."),
    NULL_TASK("No task found"),
    NULL_FIELD("is required."),
    LARGE_TITLE("Title must not exceed 28 characters."),
    NEGATIVE_ID("The task ID can't be less than zero."),
    NO_MATCH_STATUS("None match for this status. Valid status (to do, in progress or done)");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
