package com.eftech.pa.pea.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ApiExceptionBody implements Serializable {

    private int status;

    private String title;

    private String detail;

    /**
     * @return an ApiException for this exception
     */
    public ApiException wrap() {
        return new ApiException(getMessage(), this);
    }
    /**
     * Use detail by default and defer to title if detail is blank.
     * @return the message to use for the exception message.
     */
    private String getMessage() {
        if (detail != null && detail.length() > 0) {
            return detail;
        }

        return title;
    }

}
