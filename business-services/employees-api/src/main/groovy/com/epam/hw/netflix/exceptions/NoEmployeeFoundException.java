package com.epam.hw.netflix.exceptions;

/**
 * Created by ins on 3/25/17.
 */
public class NoEmployeeFoundException extends RuntimeException {
    public NoEmployeeFoundException() {
    }

    public NoEmployeeFoundException(String message) {
        super(message);
    }
}
