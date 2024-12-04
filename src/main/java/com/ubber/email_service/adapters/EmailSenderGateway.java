package com.ubber.email_service.adapters;

public interface EmailSenderGateway {
	void sendEmail(String to, String subjet, String body);
}
