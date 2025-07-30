package edu.ifba.internet_banking_main_api.clients;

import edu.ifba.internet_banking_main_api.dtos.mail.EmailRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.mail.EmailResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailClientFallback implements EmailClient {
    
    @Override
    public EmailResponseDTO sendEmail(EmailRequestDTO emailRequest) {
        log.error("Email service is unavailable. Failed to send email to: {}", emailRequest.getTo());
        return new EmailResponseDTO("Email service is unavailable", false);
    }
}
