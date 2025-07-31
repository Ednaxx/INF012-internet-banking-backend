package edu.ifba.internet_banking_main_api.dtos.request;

import edu.ifba.internet_banking_main_api.validators.ValidCpf;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a new user account")
public record CreateUserRequestDTO(
    @Schema(
        description = "Full name of the user",
        example = "João Silva Santos",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name is required")
    String name,

    @Schema(
        description = "Brazilian CPF number (11 digits only)",
        example = "12345678901",
        requiredMode = Schema.RequiredMode.REQUIRED,
        pattern = "\\d{11}"
    )
    @NotBlank(message = "CPF is required")
    @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
    @ValidCpf
    String cpf,

    @Schema(
        description = "Email address for the user",
        example = "joao.silva@email.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    String email,

    @Schema(
        description = "Password for the account (minimum 6 characters)",
        example = "mySecurePassword123",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minLength = 6
    )
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password
) {}
