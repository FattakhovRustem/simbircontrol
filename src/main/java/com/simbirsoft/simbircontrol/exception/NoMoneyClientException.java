package com.simbirsoft.simbircontrol.exception;

public class NoMoneyClientException extends RuntimeException {
    public NoMoneyClientException() {
    }

    public NoMoneyClientException(String message) {
        super(message);
    }
}
