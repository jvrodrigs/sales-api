package com.app.salesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public ApiErros handleOrderNotFoundException(OrderNotFoundException ex){
        String error = ex.getMessage();
        return new ApiErros(error);
    }

    @ExceptionHandler(InforNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleInforNotFoundException(InforNotFoundException ex){
        String error = ex.getMessage();
        return new ApiErros(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErros(errors);
    }
}
