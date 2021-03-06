package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demo.services.DBService;
import com.demo.services.EmailService;
import com.demo.services.MockEmailService;
import com.demo.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDataBase() {
//		if (!"create".equals(strategy)) {
//			return false;
//		}
		dbService.initiate();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		//return new MockEmailService();
		return new SmtpEmailService();

	}

}
