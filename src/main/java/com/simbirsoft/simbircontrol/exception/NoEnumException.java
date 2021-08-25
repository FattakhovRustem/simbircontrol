package com.simbirsoft.simbircontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEnumException extends RuntimeException {
    public NoEnumException() {
    }

    public NoEnumException(String message) {
        super(message);
    }
}
