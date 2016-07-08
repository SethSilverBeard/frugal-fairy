package com.notify;

import javax.mail.internet.MimeMessage;

import org.junit.Test;

import com.google.api.services.gmail.Gmail;

public class GmailSenderTest {
	@Test
	public void testConnectToGmail() throws Exception {
		Gmail service = GmailSender.getGmailService();
		MimeMessage email = GmailSender.createEmail("sethwingert@gmail.com", "sethwingert@gmail.com", "Harro!", "Seth you are nice");
		GmailSender.sendMessage(service, "sethwingert@gmail.com", email);
	}
}
