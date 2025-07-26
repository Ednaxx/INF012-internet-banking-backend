package edu.ifba.internet_banking_main_api.services;

import edu.ifba.internet_banking_main_api.dtos.request.LoginRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.LoginResponseDTO;
import edu.ifba.internet_banking_main_api.exceptions.ApiException;
import edu.ifba.internet_banking_main_api.exceptions.ErrorType;
import edu.ifba.internet_banking_main_api.models.User;
import edu.ifba.internet_banking_main_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<User> userOptional = userRepository.findByEmail(request.email().toLowerCase().trim());
        
        if (userOptional.isEmpty()) {
            throw new ApiException(ErrorType.UNAUTHORIZED, "Invalid email or password");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException(ErrorType.UNAUTHORIZED, "Invalid email or password");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return new LoginResponseDTO(token);
    }
}