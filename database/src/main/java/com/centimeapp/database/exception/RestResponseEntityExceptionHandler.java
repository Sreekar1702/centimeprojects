package com.centimeapp.database.exception;

import com.centimeapp.database.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomUserException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(CustomUserException productServiceException){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .message(productServiceException.getMessage())
                .errorCode(productServiceException.getErrorCode()).build(), HttpStatus.NOT_FOUND);
    }
}