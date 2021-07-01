package com.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired//automaticamente injeta os dados do properties de email
	private MailSender mailSender;
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email..");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
	}

	
	
	
}
