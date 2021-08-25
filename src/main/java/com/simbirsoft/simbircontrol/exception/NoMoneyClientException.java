package com.simbirsoft.simbircontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoMoneyClientException extends RuntimeException {
    public NoMoneyClientException() {
    }

    public NoMoneyClientException(String message) {
        super(message);
    }
}
