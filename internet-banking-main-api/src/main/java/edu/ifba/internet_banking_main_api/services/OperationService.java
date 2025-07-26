package edu.ifba.internet_banking_main_api.services;

import edu.ifba.internet_banking_main_api.dtos.request.DepositRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.request.WithdrawalRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.OperationResponseDTO;
import edu.ifba.internet_banking_main_api.exceptions.ApiException;
import edu.ifba.internet_banking_main_api.exceptions.ErrorType;
import edu.ifba.internet_banking_main_api.models.Account;
import edu.ifba.internet_banking_main_api.models.Operation;
import edu.ifba.internet_banking_main_api.models.OperationType;
import edu.ifba.internet_banking_main_api.models.User;
import edu.ifba.internet_banking_main_api.repositories.AccountRepository;
import edu.ifba.internet_banking_main_api.repositories.OperationRepository;
import edu.ifba.internet_banking_main_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Transactional
    public OperationResponseDTO deposit(String userId, DepositRequestDTO request) {
        Account account = getUserAccount(userId);
        
        // Validate amount
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(ErrorType.BAD_REQUEST, "Deposit amount must be greater than zero");
        }

        // Update account balance
        BigDecimal newBalance = account.getBalance().add(request.amount());
        account.setBalance(newBalance);

        // Create operation record
        Operation operation = new Operation(
            OperationType.DEPOSIT,
            request.amount(),
            request.description() != null ? request.description() : "Deposit",
            account
        );

        // Save both account and operation
        accountRepository.save(account);
        Operation savedOperation = operationRepository.save(operation);

        return new OperationResponseDTO(
            savedOperation.getId(),
            savedOperation.getType(),
            savedOperation.getAmount(),
            savedOperation.getDescription(),
            savedOperation.getCreatedAt(),
            newBalance
        );
    }

    @Transactional
    public OperationResponseDTO withdraw(String userId, WithdrawalRequestDTO request) {
        Account account = getUserAccount(userId);
        
        // Validate amount
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(ErrorType.BAD_REQUEST, "Withdrawal amount must be greater than zero");
        }

        // Check sufficient balance
        if (account.getBalance().compareTo(request.amount()) < 0) {
            throw new ApiException(ErrorType.BAD_REQUEST, "Insufficient balance");
        }

        // Update account balance
        BigDecimal newBalance = account.getBalance().subtract(request.amount());
        account.setBalance(newBalance);

        // Create operation record
        Operation operation = new Operation(
            OperationType.WITHDRAWAL,
            request.amount(),
            request.description() != null ? request.description() : "Withdrawal",
            account
        );

        // Save both account and operation
        accountRepository.save(account);
        Operation savedOperation = operationRepository.save(operation);

        return new OperationResponseDTO(
            savedOperation.getId(),
            savedOperation.getType(),
            savedOperation.getAmount(),
            savedOperation.getDescription(),
            savedOperation.getCreatedAt(),
            newBalance
        );
    }

    private Account getUserAccount(String userId) {
        UUID userUuid = UUID.fromString(userId);
        
        Optional<User> userOptional = userRepository.findById(userUuid);
        if (userOptional.isEmpty()) {
            throw new ApiException(ErrorType.RESOURCE_NOT_FOUND, "User not found");
        }

        User user = userOptional.get();
        Account account = user.getConta();
        
        if (account == null) {
            throw new ApiException(ErrorType.RESOURCE_NOT_FOUND, "Account not found for user");
        }

        return account;
    }
}