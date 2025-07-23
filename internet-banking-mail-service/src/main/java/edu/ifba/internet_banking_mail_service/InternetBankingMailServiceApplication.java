package edu.ifba.internet_banking_mail_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InternetBankingMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetBankingMailServiceApplication.class, args);
	}

}
