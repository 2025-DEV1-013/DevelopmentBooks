package com.bnpp.kata.developmentbooks.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class InvalidBookException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public InvalidBookException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }

    public InvalidBookException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

}
