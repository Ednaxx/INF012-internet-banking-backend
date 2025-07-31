package edu.ifba.internet_banking_main_api.dtos.response;

import edu.ifba.internet_banking_main_api.models.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "Response containing operation details")
public class OperationResponseDTO {
    @Schema(
        description = "Unique operation identifier",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID id;
    
    @Schema(
        description = "Type of operation performed",
        example = "DEPOSIT"
    )
    private OperationType type;
    
    @Schema(
        description = "Amount of the operation",
        example = "100.50"
    )
    private BigDecimal amount;
    
    @Schema(
        description = "Description of the operation",
        example = "Salary deposit"
    )
    private String description;
    
    @Schema(
        description = "Date and time when the operation was created",
        example = "2025-01-31T10:30:00"
    )
    private LocalDateTime createdAt;
    
    @Schema(
        description = "New account balance after the operation",
        example = "1351.25"
    )
    private BigDecimal newBalance;
}
