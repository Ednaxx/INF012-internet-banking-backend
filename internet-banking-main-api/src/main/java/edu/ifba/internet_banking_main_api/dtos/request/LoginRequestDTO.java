package edu.ifba.internet_banking_main_api.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request containing account credentials")
public record LoginRequestDTO(
    @Schema(
        description = "Bank account number",
        example = "12345678",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Account number is required")
    String accountNumber,

    @Schema(
        description = "Bank branch number",
        example = "001",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Branch is required")
    String branch,

    @Schema(
        description = "Account password",
        example = "mySecurePassword123",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required")
    String password
) {}