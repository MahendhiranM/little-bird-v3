package com.transport.buspass.service;

public interface EmailService {

	void sendEmail(String to, String subject, String message);

}
