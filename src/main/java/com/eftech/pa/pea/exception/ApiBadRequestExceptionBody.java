package com.eftech.pa.pea.exception;

import org.springframework.http.HttpStatus;

public class ApiBadRequestExceptionBody extends ApiExceptionBody {

    private static final String BAD_REQUEST_ERROR_TITLE= "error.badRequest";

    /**
     * New error defining only the message
     * @param detailMessage the detail message
     */
    public ApiBadRequestExceptionBody(String detailMessage) {
        super(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_ERROR_TITLE, detailMessage);
    }

    /**
     * New error defining both the title and the message
     * @param title the title to use instead of {@value BAD_REQUEST_ERROR_TITLE}.
     * @param detailMessage the detail message
     */
    public ApiBadRequestExceptionBody(String title, String detailMessage) {
        super(HttpStatus.BAD_REQUEST.value(), title, detailMessage);
    }
}
