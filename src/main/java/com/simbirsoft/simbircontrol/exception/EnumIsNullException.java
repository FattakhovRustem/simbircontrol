package com.simbirsoft.simbircontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnumIsNullException extends RuntimeException {
    public EnumIsNullException() {
    }

    public EnumIsNullException(String message) {
        super(message);
    }
}
