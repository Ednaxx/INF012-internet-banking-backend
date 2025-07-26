package edu.ifba.internet_banking_main_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceResponseDTO {
    private Double balance;
    private String accountNumber;
    private String branch;
}
