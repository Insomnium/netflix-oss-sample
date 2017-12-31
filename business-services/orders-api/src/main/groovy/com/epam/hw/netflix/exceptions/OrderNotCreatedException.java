package com.epam.hw.netflix.exceptions;


public class OrderNotCreatedException
        extends RuntimeException {
    public OrderNotCreatedException(String message) {
        super(message);
    }
}
