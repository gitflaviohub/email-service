package com.ubber.email_service.core;

public interface EmailSenderUserCase {
	void sendEmail(String to, String subjet, String body);
}
