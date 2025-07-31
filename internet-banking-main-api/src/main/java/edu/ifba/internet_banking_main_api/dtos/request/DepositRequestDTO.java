package edu.ifba.internet_banking_main_api.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request to make a deposit to the account")
public record DepositRequestDTO(
    @Schema(
        description = "Amount to deposit",
        example = "100.50",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minimum = "0.01"
    )
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount,
    
    @Schema(
        description = "Optional description for the deposit",
        example = "Salary deposit",
        maxLength = 500
    )
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    String description
) {}
