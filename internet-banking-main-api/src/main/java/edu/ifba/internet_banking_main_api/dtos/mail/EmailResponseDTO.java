package edu.ifba.internet_banking_main_api.dtos.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponseDTO {
    private String message;
    private boolean success;
}
