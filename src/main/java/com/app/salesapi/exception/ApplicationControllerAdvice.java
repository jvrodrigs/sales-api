package com.app.salesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleCustomException(CustomException ex){
        String error = ex.getMessage();
        return new ApiErros(error);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleCustomException(OrderNotFoundException ex){
        String error = ex.getMessage();
        return new ApiErros(error);
    }

    @ExceptionHandler(InforNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleCustomException(InforNotFoundException ex){
        String error = ex.getMessage();
        return new ApiErros(error);
    }
}
