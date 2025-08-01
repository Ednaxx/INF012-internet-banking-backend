package edu.ifba.internet_banking_main_api.services;

import edu.ifba.internet_banking_main_api.dtos.request.LoginRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.LoginResponseDTO;
import edu.ifba.internet_banking_main_api.exceptions.ApiException;
import edu.ifba.internet_banking_main_api.exceptions.ErrorType;
import edu.ifba.internet_banking_main_api.models.Account;
import edu.ifba.internet_banking_main_api.models.User;
import edu.ifba.internet_banking_main_api.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<Account> accountOptional = accountRepository.findByNumberAndBranch(
            request.accountNumber().trim(), 
            request.branch().trim()
        );
        
        if (accountOptional.isEmpty()) {
            throw new ApiException(ErrorType.UNAUTHORIZED, "Invalid account credentials");
        }

        Account account = accountOptional.get();
        User user = account.getUser();

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException(ErrorType.UNAUTHORIZED, "Invalid account credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return new LoginResponseDTO(
            token,
            user.getName(),
            account.getNumber(),
            account.getBranch()
        );
    }
}