package com.centimeapp.database.exception;

import lombok.Data;

@Data
public class CustomUserException extends RuntimeException{

    private String errorCode;
    public CustomUserException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }
}