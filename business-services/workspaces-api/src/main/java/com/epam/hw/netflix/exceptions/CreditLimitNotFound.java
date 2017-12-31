package com.epam.hw.netflix.exceptions;


public class CreditLimitNotFound extends RuntimeException {
    public CreditLimitNotFound(String message) {
        super(message);
    }
}
