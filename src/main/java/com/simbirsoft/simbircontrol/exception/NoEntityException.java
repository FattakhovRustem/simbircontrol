package com.simbirsoft.simbircontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoEntityException extends RuntimeException {
    public NoEntityException() {

    }

    public NoEntityException(String message) {
        super(message);
    }
}
