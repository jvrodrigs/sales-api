package com.app.salesapi.exception;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
