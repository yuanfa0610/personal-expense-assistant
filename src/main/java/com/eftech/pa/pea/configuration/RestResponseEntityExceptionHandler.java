package com.eftech.pa.pea.configuration;

import com.eftech.pa.pea.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<Object> handleAllExceptions(ApiException apiException) {
        return new ResponseEntity(apiException.getExceptionBody(), HttpStatus.valueOf(apiException.getExceptionBody().getStatus()));
    }
}
