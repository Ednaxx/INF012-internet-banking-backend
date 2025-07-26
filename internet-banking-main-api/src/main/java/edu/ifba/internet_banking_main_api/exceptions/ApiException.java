package edu.ifba.internet_banking_main_api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(ErrorType errorType) {
        super(errorType.getMessage());
        this.status = errorType.getStatus();
    }

    public ApiException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.status = errorType.getStatus();
    }
}
