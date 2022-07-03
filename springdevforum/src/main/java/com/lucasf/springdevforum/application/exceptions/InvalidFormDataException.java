package com.lucasf.springdevforum.application.exceptions;

public class InvalidFormDataException extends RuntimeException{
    public InvalidFormDataException(String message){
        super(message);
    }
}
