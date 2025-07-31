package edu.ifba.internet_banking_main_api.controllers;

import edu.ifba.internet_banking_main_api.dtos.request.DepositRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.request.PaymentRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.request.WithdrawalRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.BalanceResponseDTO;
import edu.ifba.internet_banking_main_api.dtos.response.OperationResponseDTO;
import edu.ifba.internet_banking_main_api.dtos.response.StatementResponseDTO;
import edu.ifba.internet_banking_main_api.dtos.response.ErrorResponse;
import edu.ifba.internet_banking_main_api.models.Operation;
import edu.ifba.internet_banking_main_api.services.AccountService;
import edu.ifba.internet_banking_main_api.services.OperationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Account Operations", description = "Banking operations including balance, deposits, withdrawals, payments and statements")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {

    private final AccountService accountService;
    private final OperationService operationService;

    @GetMapping("/balance")
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Get account balance",
        description = "Retrieves the current account balance for the authenticated user"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Balance retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = BalanceResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid or missing JWT token",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Account not found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    public ResponseEntity<BalanceResponseDTO> getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        BalanceResponseDTO balance = accountService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/deposit")
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Make a deposit",
        description = "Deposits money into the authenticated user's account"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Deposit completed successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OperationResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data or amount",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid or missing JWT token",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    public ResponseEntity<OperationResponseDTO> deposit(@Valid @RequestBody DepositRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        OperationResponseDTO operation = operationService.deposit(userId, request);
        return ResponseEntity.ok(operation);
    }

    @PostMapping("/withdraw")
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Make a withdrawal",
        description = "Withdraws money from the authenticated user's account"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Withdrawal completed successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OperationResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data, insufficient funds, or invalid amount",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid or missing JWT token",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    public ResponseEntity<OperationResponseDTO> withdraw(@Valid @RequestBody WithdrawalRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        OperationResponseDTO operation = operationService.withdraw(userId, request);
        return ResponseEntity.ok(operation);
    }

    @PostMapping("/payment")
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Make a payment",
        description = "Processes a payment from the authenticated user's account to another account"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment completed successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OperationResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data, insufficient funds, or invalid destination account",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid or missing JWT token",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Destination account not found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    public ResponseEntity<OperationResponseDTO> payment(@Valid @RequestBody PaymentRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        
        OperationResponseDTO operation = operationService.payment(userId, request);
        return ResponseEntity.ok(operation);
    }

    @GetMapping("/statement")
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Get account statement",
        description = "Retrieves the account statement with balance and transaction history for the authenticated user"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Statement retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = StatementResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid or missing JWT token",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Account not found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
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
                    null
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
