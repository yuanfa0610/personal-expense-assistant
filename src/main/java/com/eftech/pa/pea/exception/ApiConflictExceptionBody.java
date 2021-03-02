package com.eftech.pa.pea.exception;

import org.springframework.http.HttpStatus;

public class ApiConflictExceptionBody extends ApiExceptionBody {

    private static final String CONFLICT_ERROR_TITLE= "error.conflict";

    /**
     * New error defining only the message
     * @param detailMessage the detail message
     */
    public ApiConflictExceptionBody(String detailMessage) {
        super(HttpStatus.CONFLICT.value(), CONFLICT_ERROR_TITLE, detailMessage);
    }

    /**
     * New error defining both the title and the message
     * @param title the title to use instead of {@value CONFLICT_ERROR_TITLE}.
     * @param detailMessage the detail message
     */
    public ApiConflictExceptionBody(String title, String detailMessage) {
        super(HttpStatus.CONFLICT.value(), title, detailMessage);
    }
}
