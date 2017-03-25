package com.epam.hw.netflix.exceptions;

/**
 * Created by ins on 3/25/17.
 */
public class NoWorkspaceFoundException extends RuntimeException {
    public NoWorkspaceFoundException() {
    }

    public NoWorkspaceFoundException(String message) {
        super(message);
    }
}
