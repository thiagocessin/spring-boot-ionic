package com.demo.services;

import org.springframework.mail.SimpleMailMessage;

import com.demo.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
}
