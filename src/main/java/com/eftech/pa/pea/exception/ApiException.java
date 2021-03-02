package com.eftech.pa.pea.exception;

import lombok.Getter;

@Getter
public class ApiException extends Exception {


    ApiExceptionBody exceptionBody;

    /**
     * Constructor
     * @param message the message to use for this exception
     * @param exceptionBody the exception this is wrapping
     */
    public ApiException(String message, ApiExceptionBody exceptionBody) {
        super(message);
        this.exceptionBody = exceptionBody;
    }
}