package edu.ifba.internet_banking_main_api.clients;

import edu.ifba.internet_banking_main_api.dtos.mail.EmailRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.mail.EmailResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "internet-banking-mail-service", fallback = EmailClientFallback.class)
public interface EmailClient {
    
    @PostMapping("/send")
    EmailResponseDTO sendEmail(@RequestBody EmailRequestDTO emailRequest);
}
