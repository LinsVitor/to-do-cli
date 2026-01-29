package com.todocli.model;

import com.todocli.exception.InvalidArgumentException;
import com.todocli.util.ErrorMessage;

import java.util.Arrays;

public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    public static Status fromString(String value) {
        if (value.trim().isEmpty()) {
            throw new InvalidArgumentException("Status " + ErrorMessage.EMPTY_FIELD.getMessage());
        }
        if (value.equalsIgnoreCase("null")) {
            throw new InvalidArgumentException("Status " + ErrorMessage.NULL_FIELD.getMessage());
        }
        String validValue = value.replaceFirst(" ", "_").replace("-", "_").trim().toUpperCase();
        if (Arrays.stream(Status.values()).noneMatch(status -> status.name().equals(validValue))) {
            throw new InvalidArgumentException(ErrorMessage.NO_MATCH_STATUS.getMessage());
        }
        return Status.valueOf(validValue);
    }
}
