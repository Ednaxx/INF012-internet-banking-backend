package edu.ifba.internet_banking_main_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "operations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType type;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Operation(OperationType type, BigDecimal amount, String description, Account account) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.createdAt = LocalDateTime.now();
    }
}
