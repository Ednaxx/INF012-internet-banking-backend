package edu.ifba.internet_banking_main_api.dtos.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PaymentRequestDTO(
    @NotBlank(message = "Target account number is required")
    String targetAccountNumber,
    
    @NotBlank(message = "Target branch is required")
    String targetBranch,
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount,
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    String description
) {}
