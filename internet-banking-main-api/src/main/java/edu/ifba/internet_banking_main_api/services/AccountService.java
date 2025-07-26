package edu.ifba.internet_banking_main_api.services;

import edu.ifba.internet_banking_main_api.dtos.response.BalanceResponseDTO;
import edu.ifba.internet_banking_main_api.exceptions.ApiException;
import edu.ifba.internet_banking_main_api.exceptions.ErrorType;
import edu.ifba.internet_banking_main_api.models.Account;
import edu.ifba.internet_banking_main_api.models.User;
import edu.ifba.internet_banking_main_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    public BalanceResponseDTO getBalance(String userId) {
        UUID userUuid = UUID.fromString(userId);
        
        Optional<User> userOptional = userRepository.findById(userUuid);
        
        if (userOptional.isEmpty()) {
            throw new ApiException(ErrorType.INTERNAL_SERVER_ERROR, "Something went wrong while fetching the user");
        }

        User user = userOptional.get();
        Account account = user.getConta();
        
        if (account == null) {
            throw new ApiException(ErrorType.INTERNAL_SERVER_ERROR, "Something went wrong while fetching the account");
        }

        return new BalanceResponseDTO(
            account.getBalance(),
            account.getNumber(),
            account.getBranch()
        );
    }
}
