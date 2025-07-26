package edu.ifba.internet_banking_main_api.controllers;

import edu.ifba.internet_banking_main_api.dtos.request.DepositRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.request.WithdrawalRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.BalanceResponseDTO;
import edu.ifba.internet_banking_main_api.dtos.response.OperationResponseDTO;
import edu.ifba.internet_banking_main_api.services.AccountService;
import edu.ifba.internet_banking_main_api.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
}
