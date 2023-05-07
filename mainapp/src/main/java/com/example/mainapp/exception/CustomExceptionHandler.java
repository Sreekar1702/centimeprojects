package com.example.mainapp.exception;

import lombok.Data;

@Data
public class CustomExceptionHandler extends RuntimeException{

    private String errorCode;
    private String message;
    public CustomExceptionHandler(String message, String errorCode){
        this.message=message;
        this.errorCode=errorCode;
    }
}