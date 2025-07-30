package edu.ifba.internet_banking_main_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String name;
    private String accountNumber;
    private String branch;
}