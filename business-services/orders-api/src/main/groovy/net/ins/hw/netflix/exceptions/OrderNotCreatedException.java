package net.ins.hw.netflix.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ResponseStatus(NOT_ACCEPTABLE)
public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException(String message) {
        super(message);
    }
}
