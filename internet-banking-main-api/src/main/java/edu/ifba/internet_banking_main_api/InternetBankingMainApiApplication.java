package edu.ifba.internet_banking_main_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class InternetBankingMainApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetBankingMainApiApplication.class, args);
	}

}
