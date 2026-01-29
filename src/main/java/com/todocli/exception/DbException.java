package com.todocli.exception;

public class DbException extends RuntimeException {
    public DbException(String message) {
        super(message);
    }
}
