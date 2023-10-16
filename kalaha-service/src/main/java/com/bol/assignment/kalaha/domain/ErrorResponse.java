package com.bol.assignment.kalaha.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The class ErrorResponse is a Java component that represents an error response with an error code and error message.
 */

@Data
@NoArgsConstructor
@Component
public class ErrorResponse {

    private int errorCode;

    private String errorMessage;

    public ErrorResponse(final int errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
