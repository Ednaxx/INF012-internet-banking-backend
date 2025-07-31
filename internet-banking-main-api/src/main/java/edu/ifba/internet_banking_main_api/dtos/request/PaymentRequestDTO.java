package edu.ifba.internet_banking_main_api.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request to make a payment to another account")
public record PaymentRequestDTO(
    @Schema(
        description = "Target account number to receive the payment",
        example = "87654321",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Target account number is required")
    String targetAccountNumber,
    
    @Schema(
        description = "Target branch number",
        example = "0002",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Target branch is required")
    String targetBranch,
    
    @Schema(
        description = "Amount to transfer",
        example = "250.00",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minimum = "0.01"
    )
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount,
    
    @Schema(
        description = "Optional description for the payment",
        example = "Payment for services",
        maxLength = 500
    )
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    String description
) {}
