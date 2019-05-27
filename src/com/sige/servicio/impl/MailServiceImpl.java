package com.sige.servicio.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.sige.entidad.Expediente;
import com.sige.servicio.MailService;
import com.sige.util.MailSenderUtil;
import com.sige.util.PlantillaVelocity;

@Service
public class MailServiceImpl implements MailService {
	
	private JavaMailSender mailSender;

	// @Autowired
	// private SimpleMailMessage preConfiguredMessage;

	//@Autowired
	private PlantillaVelocity plantillaVelocity;
	
	public MailServiceImpl() {
		super();
		this.mailSender=new MailSenderUtil().javaMailSender();
		this.plantillaVelocity= new PlantillaVelocity();
	}

	@Override
	public void sendPreConfiguredMail(String message) {
		// SimpleMailMessage mailMessage = new
		// SimpleMailMessage(preConfiguredMessage);
		// mailMessage.setText(message);
		// mailSender.send(mailMessage);
	}

	@Override
	public void sendMail(final String to, final String subject,
			final List<Expediente> documentosVencidos, final Boolean vencidos) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true);
				message.setTo(to);
				message.setSubject(subject);
				// message.addAttachment("perfil.png", new
				// ClassPathResource("perfil.png"));
				Map model = new HashMap();
				model.put("vencidos", vencidos);
				model.put("expedientesVencidos", documentosVencidos);
				String text = plantillaVelocity.enviarFormato(model,
						"email-template.vm");
				message.setText(text, true);
			}

		};
		mailSender.send(preparator);
	}

}