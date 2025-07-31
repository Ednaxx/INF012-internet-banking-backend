package edu.ifba.internet_banking_main_api.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Success response containing confirmation message")
public class SuccessResponse {
    @Schema(
        description = "Success message",
        example = "User successfully created"
    )
    private String message;
}