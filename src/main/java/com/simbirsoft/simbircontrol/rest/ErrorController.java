package com.simbirsoft.simbircontrol.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
