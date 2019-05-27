package com.sige.util;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSenderUtil {
	public JavaMailSender javaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	    Properties mailProperties = new Properties();
	    //mailProperties.put("mail.transport.protocol", "smtp");
	    mailProperties.put("mail.smtp.auth", true);
	    mailProperties.put("mail.smtp.starttls.enable", true);
	    mailProperties.put("mail.debug", true);
	    //mailProperties.put("mail.smtp.starttls.required", startlls_required);
	    //mailProperties.put("mail.smtp.socketFactory.port", socketPort);
	    //mailProperties.put("mail.smtp.debug", debug);
	    //mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    //mailProperties.put("mail.smtp.socketFactory.fallback", fallback);

	    mailSender.setJavaMailProperties(mailProperties);
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(25);
	    mailSender.setProtocol("smtp");
	    mailSender.setUsername("angel.gutierrez.swgoodidea@gmail.com");
	    mailSender.setPassword("");
	    return mailSender;
	}
}