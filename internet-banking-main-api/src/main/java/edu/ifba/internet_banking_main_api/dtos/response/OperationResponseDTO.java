package edu.ifba.internet_banking_main_api.dtos.response;

import edu.ifba.internet_banking_main_api.models.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OperationResponseDTO {
    private UUID id;
    private OperationType type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private BigDecimal newBalance;
}
