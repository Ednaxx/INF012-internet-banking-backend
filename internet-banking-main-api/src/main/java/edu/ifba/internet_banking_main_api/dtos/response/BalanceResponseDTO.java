package edu.ifba.internet_banking_main_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalanceResponseDTO {
    private BigDecimal balance;
    private String accountNumber;
    private String branch;
}
