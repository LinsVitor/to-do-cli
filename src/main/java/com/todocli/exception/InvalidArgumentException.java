package com.todocli.exception;

import com.todocli.util.Color;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(Color.getRED() + message + Color.getRESET());
    }
}
