package com.example.mainapp.exception;

import com.example.mainapp.model.ErrorResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ApiOperation("Custom Exception handler")
    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(CustomExceptionHandler productServiceException){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .message(productServiceException.getMessage())
                .errorCode(productServiceException.getErrorCode()).build(), HttpStatus.NOT_FOUND);
    }
}