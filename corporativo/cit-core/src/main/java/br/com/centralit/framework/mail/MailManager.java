/*
 * Created on 29/09/2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.centralit.framework.mail;


import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

public class MailManager implements Runnable {
	
	private JavaMailSender mailSender;
	private MimeMessage message;
	
	public MailManager(JavaMailSender mailSender, MimeMessage message) {
		this.mailSender = mailSender;
		this.message = message;
	}

	public void send() {
		mailSender.send(message);
	}
	
	@Override
	public void run() {
		send();
	}
}