package com.epam.hw.netflix.exceptions;


public class NoEmployeeFoundException extends RuntimeException {
    public NoEmployeeFoundException() {
    }

    public NoEmployeeFoundException(String message) {
        super(message);
    }
}
