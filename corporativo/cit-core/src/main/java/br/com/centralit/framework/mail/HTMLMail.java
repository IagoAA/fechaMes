/*
 * Created on 29/09/2005
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.centralit.framework.mail;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import br.com.centralit.framework.util.UtilHTML;
import br.com.centralit.framework.util.UtilString;

public class HTMLMail  { 
	
	@Value("${mail.enabled}")
	private String enabled;
	
	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.from}")
	private String defaultFrom;

	private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        
        // Configurações necessárias para funcionar gmail
        if (!UtilString.isNullOrEmpty(host) && host.equalsIgnoreCase("smtp.gmail.com")) {
        	JavaMailSenderImpl sender = (JavaMailSenderImpl) this.mailSender;
        	if (sender.getJavaMailProperties().getProperty("mail.smtp.quitwait") == null) {
        		sender.getJavaMailProperties().put("mail.smtp.quitwait", "false");
        	}
        	if (sender.getJavaMailProperties().getProperty("mail.smtp.socketFactory.class") == null) {
        		sender.getJavaMailProperties().put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        	}
        	if (sender.getJavaMailProperties().getProperty("mail.smtp.socketFactory.fallback") == null) {
        		sender.getJavaMailProperties().put("mail.smtp.socketFactory.fallback", "false");
        	}
        }
    }
	
	public void sendMail(String[] to, String from, String subject, String msg, List<File> files) {
		if (UtilString.isNullOrEmpty(enabled) || !enabled.equalsIgnoreCase("true")) {
			return;
		}
		
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	
	        message.setSubject(subject);
	        MimeMessageHelper helper;
	        helper = new MimeMessageHelper(message, true);
	        helper.setFrom(UtilString.isNullOrEmpty(from) ? defaultFrom : from);
	        helper.setTo(to);
	        helper.setText(UtilHTML.encodeHTML(msg), true);
	        
	        if (files != null) {
	        	for (File file : files) {
        			helper.addAttachment(file.getName(), file);
				}
	        }	
	        
			new Thread(new MailManager(mailSender, message)).start();
	    } catch (MessagingException ex) {
	        Logger.getLogger(HTMLMail.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}
