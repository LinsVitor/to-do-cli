package com.todocli.exception;

import com.todocli.util.Color;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(Color.getRED() + message + Color.getRESET());
    }
}
