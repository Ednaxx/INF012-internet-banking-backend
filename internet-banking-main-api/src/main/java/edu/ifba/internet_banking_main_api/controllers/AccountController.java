package edu.ifba.internet_banking_main_api.controllers;

import edu.ifba.internet_banking_main_api.dtos.request.DepositRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.request.WithdrawalRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.BalanceResponseDTO;
import edu.ifba.internet_banking_main_api.dtos.response.OperationResponseDTO;
import edu.ifba.internet_banking_main_api.dtos.response.StatementResponseDTO;
import edu.ifba.internet_banking_main_api.models.Operation;
import edu.ifba.internet_banking_main_api.services.AccountService;
import edu.ifba.internet_banking_main_api.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final OperationService operationService;

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        BalanceResponseDTO balance = accountService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/deposit")
    public ResponseEntity<OperationResponseDTO> deposit(@Valid @RequestBody DepositRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        OperationResponseDTO operation = operationService.deposit(userId, request);
        return ResponseEntity.ok(operation);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<OperationResponseDTO> withdraw(@Valid @RequestBody WithdrawalRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        OperationResponseDTO operation = operationService.withdraw(userId, request);
        return ResponseEntity.ok(operation);
    }

    @GetMapping("/statement")
    public ResponseEntity<StatementResponseDTO> getStatement() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        BalanceResponseDTO balance = accountService.getBalance(userId);
        List<Operation> operations = operationService.getAccountStatement(userId);
        
        List<OperationResponseDTO> operationDTOs = operations.stream()
                .map(op -> new OperationResponseDTO(
                    op.getId(),
                    op.getType(),
                    op.getAmount(),
                    op.getDescription(),
                    op.getCreatedAt(),
                    null // We don't show balance for each operation in statement
                ))
                .collect(Collectors.toList());

        StatementResponseDTO statement = new StatementResponseDTO(
            balance.getAccountNumber(),
            balance.getBranch(),
            balance.getBalance(),
            operationDTOs
        );
        
        return ResponseEntity.ok(statement);
    }
}
