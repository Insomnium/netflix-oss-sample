package com.epam.hw.netflix.controllers.handler;

import com.epam.hw.netflix.exceptions.OrderNotCreatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class OrderControllerExceptionHandler {

    @ExceptionHandler(OrderNotCreatedException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public void handleOrderNotFound() { }
}
