package edu.ifba.internet_banking_mail_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailResponseDTO {
    private String message;
    private boolean success;
}
