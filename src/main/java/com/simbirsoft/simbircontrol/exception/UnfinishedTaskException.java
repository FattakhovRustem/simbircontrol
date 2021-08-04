package com.simbirsoft.simbircontrol.exception;

public class UnfinishedTaskException extends RuntimeException {
    public UnfinishedTaskException() {
    }

    public UnfinishedTaskException(String message) {
        super(message);
    }
}
