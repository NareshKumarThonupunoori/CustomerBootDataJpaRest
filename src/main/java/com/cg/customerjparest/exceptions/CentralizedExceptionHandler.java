package com.cg.customerjparest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    private static final Logger Log= LoggerFactory.getLogger(CentralizedExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public String handleStudentNotFound(CustomerNotFoundException e){
         return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleAll(Exception e){
        Log.error("exception caught",e);
        return e.getMessage();
    }

}
