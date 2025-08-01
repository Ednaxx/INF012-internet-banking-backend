package edu.ifba.internet_banking_main_api.services;

import edu.ifba.internet_banking_main_api.clients.EmailClient;
import edu.ifba.internet_banking_main_api.dtos.mail.EmailRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.mail.EmailResponseDTO;
import edu.ifba.internet_banking_main_api.models.Account;
import edu.ifba.internet_banking_main_api.models.Operation;
import edu.ifba.internet_banking_main_api.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService {

    private final EmailClient emailClient;

    @Async("emailTaskExecutor")
    public void sendWelcomeEmail(User user, Account account) {
        try {
            String subject = "Bem vindo ao Internet Banking!";
            String body = String.format("""
                Olá %s,
                
                Bem vindo ao nosso sistema de Internet Banking!
                
                Sua conta foi criada com sucesso:
                - Número da conta: %s
                - Agência: %s
                
                Você pode acessar sua conta através do nosso portal online.
                
                Atenciosamente,
                Internet Banking Team
                """, user.getName(), account.getNumber(), account.getBranch());

            EmailRequestDTO emailRequest = new EmailRequestDTO(user.getEmail(), subject, body);
            sendEmailWithLogging(emailRequest, "Welcome Email");
        } catch (Exception e) {
            log.error("Failed to send welcome email to user: {}", user.getEmail(), e);
        }
    }

    @Async("emailTaskExecutor")
    public void sendDepositNotification(User user, Account account, Operation operation) {
        try {
            String subject = "Confirmação de Depósito";
            String body = String.format("""
                Olá %s,
                
                Seu depósito foi processado com sucesso:
                
                - Conta: %s (Agência: %s)
                - Valor: $%.2f
                - Descrição: %s
                - Data: %s
                - Saldo novo: $%.2f
                
                Obrigado por utilizar nossos serviços.
                
                Atenciosamente,
                Internet Banking Team
                """,
                user.getName(), 
                account.getNumber(), 
                account.getBranch(),
                operation.getAmount(),
                operation.getDescription(),
                operation.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                account.getBalance());

            EmailRequestDTO emailRequest = new EmailRequestDTO(user.getEmail(), subject, body);
            sendEmailWithLogging(emailRequest, "Deposit Notification");
        } catch (Exception e) {
            log.error("Failed to send deposit notification to user: {}", user.getEmail(), e);
        }
    }

    @Async("emailTaskExecutor")
    public void sendWithdrawalNotification(User user, Account account, Operation operation) {
        try {
            String subject = "Confirmação de Saque";
            String body = String.format("""
                Olá %s,
                
                Seu saque foi processado com sucesso:
                
                - Conta: %s (Agência: %s)
                - Valor: $%.2f
                - Descrição: %s
                - Data: %s
                - Saldo novo: $%.2f
                
                Obrigado por utilizar nossos serviços.
                
                Atenciosamente,
                Internet Banking Team
                """, 
                user.getName(), 
                account.getNumber(), 
                account.getBranch(),
                operation.getAmount(),
                operation.getDescription(),
                operation.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                account.getBalance());

            EmailRequestDTO emailRequest = new EmailRequestDTO(user.getEmail(), subject, body);
            sendEmailWithLogging(emailRequest, "Withdrawal Notification");
        } catch (Exception e) {
            log.error("Failed to send withdrawal notification to user: {}", user.getEmail(), e);
        }
    }

    @Async("emailTaskExecutor")
    public void sendPaymentNotification(User user, Account account, Operation operation) {
        try {
            String subject = "Confirmação de Pagamento";
            String body = String.format("""
                Olá %s,
                
                Seu pagamento foi processado com sucesso:
                
                - Conta: %s (Agência: %s)
                - Valor: $%.2f
                - Descrição: %s
                - Data: %s
                - Saldo novo: $%.2f
                
                Obrigado por utilizar nossos serviços.
                
                Atenciosamente,
                Internet Banking Team
                """, 
                user.getName(), 
                account.getNumber(), 
                account.getBranch(),
                operation.getAmount(),
                operation.getDescription(),
                operation.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                account.getBalance());

            EmailRequestDTO emailRequest = new EmailRequestDTO(user.getEmail(), subject, body);
            sendEmailWithLogging(emailRequest, "Payment Notification");
        } catch (Exception e) {
            log.error("Failed to send payment notification to user: {}", user.getEmail(), e);
        }
    }

    private void sendEmailWithLogging(EmailRequestDTO emailRequest, String emailType) {
        try {
            EmailResponseDTO response = emailClient.sendEmail(emailRequest);
            if (response.isSuccess()) {
                log.info("{} sent successfully to: {}", emailType, emailRequest.getTo());
            } else {
                log.error("Failed to send {}: {}", emailType, response.getMessage());
            }
        } catch (Exception e) {
            log.error("Error sending {}: {}", emailType, e.getMessage());
        }
    }
}
