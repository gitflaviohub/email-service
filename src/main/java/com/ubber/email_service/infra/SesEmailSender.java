package com.ubber.email_service.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.ubber.email_service.adapters.EmailSenderGateway;
import com.ubber.email_service.exception.EmailServiceException;

@Service
public class SesEmailSender implements EmailSenderGateway {

	private final AmazonSimpleEmailService amazonSimpleEmailService;

	@Autowired
	public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		this.amazonSimpleEmailService = amazonSimpleEmailService;
	}

	@Override
	public void sendEmail(String to, String subjet, String body) throws EmailServiceException {
		SendEmailRequest request = new SendEmailRequest().withSource("")
				.withDestination(new Destination().withToAddresses(to)).withMessage(new Message()
						.withSubject(new Content(subjet)).withBody(new Body().withText(new Content(body))));
		
		try {
			this.amazonSimpleEmailService.sendEmail(request);
		} catch (AmazonServiceException ex) {
			throw new EmailServiceException("Falha...", ex);
		}
		
	}

}
