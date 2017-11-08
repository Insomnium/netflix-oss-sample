package com.epam.hw.netflix.controllers.handler;

import com.epam.hw.netflix.exceptions.NoWorkspaceFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class WorkspaceControllerExceptionHandler {

    @ExceptionHandler(NoWorkspaceFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handleNotFound() {}
}
