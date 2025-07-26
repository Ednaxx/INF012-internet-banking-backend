package edu.ifba.internet_banking_main_api.controllers;

import edu.ifba.internet_banking_main_api.dtos.response.BalanceResponseDTO;
import edu.ifba.internet_banking_main_api.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        BalanceResponseDTO balance = accountService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }
}
