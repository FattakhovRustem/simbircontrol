package com.simbirsoft.simbircontrol.exception;

public class EnumIsNullException extends RuntimeException {
    public EnumIsNullException() {
    }

    public EnumIsNullException(String message) {
        super(message);
    }
}
