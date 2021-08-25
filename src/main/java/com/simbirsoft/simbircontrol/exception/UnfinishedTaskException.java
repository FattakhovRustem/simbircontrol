package com.simbirsoft.simbircontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnfinishedTaskException extends RuntimeException {
    public UnfinishedTaskException() {
    }

    public UnfinishedTaskException(String message) {
        super(message);
    }
}
