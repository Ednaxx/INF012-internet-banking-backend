package edu.ifba.internet_banking_main_api.services;

import edu.ifba.internet_banking_main_api.dtos.CreateUserRequestDTO;
import edu.ifba.internet_banking_main_api.exceptions.ApiException;
import edu.ifba.internet_banking_main_api.exceptions.ErrorType;
import edu.ifba.internet_banking_main_api.models.Account;
import edu.ifba.internet_banking_main_api.models.User;
import edu.ifba.internet_banking_main_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(CreateUserRequestDTO request) {
        if (userRepository.existsByCpf(request.cpf())) {
            throw new ApiException(ErrorType.CONFLICT, "CPF already registered");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new ApiException(ErrorType.CONFLICT, "Email already registered");
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.name(), request.cpf(), request.email(), hashedPassword);

        Account account = new Account(user);
        user.setConta(account);

        userRepository.save(user);
    }
}