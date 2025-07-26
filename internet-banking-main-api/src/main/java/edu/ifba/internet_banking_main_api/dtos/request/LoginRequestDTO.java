package edu.ifba.internet_banking_main_api.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "Account number is required")
    String accountNumber,

    @NotBlank(message = "Branch is required")
    String branch,

    @NotBlank(message = "Password is required")
    String password
) {}