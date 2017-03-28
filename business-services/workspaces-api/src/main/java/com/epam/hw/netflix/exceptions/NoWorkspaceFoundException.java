package com.epam.hw.netflix.exceptions;


public class NoWorkspaceFoundException extends RuntimeException {
    public NoWorkspaceFoundException() {
    }

    public NoWorkspaceFoundException(String message) {
        super(message);
    }
}
