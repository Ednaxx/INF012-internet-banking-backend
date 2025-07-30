package edu.ifba.internet_banking_mail_service.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
    CONFLICT(HttpStatus.CONFLICT, "Resource conflict"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation failed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final HttpStatus status;
    private final String message;

    ErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}