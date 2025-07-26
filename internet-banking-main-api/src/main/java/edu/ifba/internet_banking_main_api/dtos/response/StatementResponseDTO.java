package edu.ifba.internet_banking_main_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class StatementResponseDTO {
    private String accountNumber;
    private String branch;
    private BigDecimal currentBalance;
    private List<OperationResponseDTO> operations;
}
