package edu.ifba.internet_banking_main_api.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Account statement containing balance and transaction history")
public class StatementResponseDTO {
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
    
    @Schema(
        description = "Current account balance",
        example = "1250.75"
    )
    private BigDecimal currentBalance;
    
    @Schema(
        description = "List of account operations/transactions"
    )
    private List<OperationResponseDTO> operations;
}
