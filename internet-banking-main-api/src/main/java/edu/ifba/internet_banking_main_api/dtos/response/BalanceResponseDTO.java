package edu.ifba.internet_banking_main_api.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Schema(description = "Account balance information")
public class BalanceResponseDTO {
    @Schema(
        description = "Current account balance",
        example = "1250.75"
    )
    private BigDecimal balance;
    
    @Schema(
        description = "Account number",
        example = "12345678"
    )
    private String accountNumber;
    
    @Schema(
        description = "Branch number",
        example = "0001"
    )
    private String branch;
}
