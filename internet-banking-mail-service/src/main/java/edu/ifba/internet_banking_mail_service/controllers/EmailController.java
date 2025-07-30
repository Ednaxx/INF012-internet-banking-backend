package edu.ifba.internet_banking_mail_service.controllers;

import edu.ifba.internet_banking_mail_service.dtos.EmailRequestDTO;
import edu.ifba.internet_banking_mail_service.dtos.EmailResponseDTO;
import edu.ifba.internet_banking_mail_service.exceptions.ApiException;
import edu.ifba.internet_banking_mail_service.exceptions.ErrorType;
import edu.ifba.internet_banking_mail_service.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<EmailResponseDTO> sendEmail(@Valid @RequestBody EmailRequestDTO emailRequest) {
        try {
            emailService.sendEmail(emailRequest);
            
            EmailResponseDTO response = new EmailResponseDTO(
                "Email sent successfully", 
                true
            );
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("Error sending email", e);
            
            throw new ApiException(ErrorType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Mail service is running");
    }
}
