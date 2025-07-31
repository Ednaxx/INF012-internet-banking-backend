package edu.ifba.internet_banking_main_api.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Error response containing error message")
public class ErrorResponse {
    @Schema(
        description = "Error message describing what went wrong",
        example = "Invalid credentials provided"
    )
    private String message;
}