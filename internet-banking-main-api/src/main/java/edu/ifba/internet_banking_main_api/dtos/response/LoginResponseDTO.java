package edu.ifba.internet_banking_main_api.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Login response containing authentication token and user information")
public class LoginResponseDTO {
    @Schema(
        description = "JWT authentication token",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String token;
    
    @Schema(
        description = "User's full name",
        example = "João Silva Santos"
    )
    private String name;
    
    @Schema(
        description = "Account number",
        example = "12345678"
    )
    private String accountNumber;
    
    @Schema(
        description = "Branch number",
        example = "001"
    )
    private String branch;
}