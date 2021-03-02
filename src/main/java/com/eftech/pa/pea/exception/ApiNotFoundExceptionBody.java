package com.eftech.pa.pea.exception;

import org.springframework.http.HttpStatus;

public class ApiNotFoundExceptionBody extends ApiExceptionBody {

    private static final String NOT_FOUND_ERROR_TITLE = "error.notFound";

    /**
     * New error defining only the message
     * @param detailMessage the detail message
     */
    public ApiNotFoundExceptionBody(String detailMessage) {
        super(HttpStatus.NOT_FOUND.value(), NOT_FOUND_ERROR_TITLE, detailMessage);
    }

    /**
     * New error defining both the title and the message
     * @param title the title to use instead of {@value NOT_FOUND_ERROR_TITLE}.
     * @param detailMessage the detail message
     */
    public ApiNotFoundExceptionBody(String title, String detailMessage) {
        super(HttpStatus.NOT_FOUND.value(), title, detailMessage);
    }
}
