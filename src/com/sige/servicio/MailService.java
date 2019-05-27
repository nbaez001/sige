package com.sige.servicio;

import java.util.List;

import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.Expediente;

public interface MailService {
	public void sendMail(final String to, final String subject,
			final List<Expediente> documentosVencidos, final Boolean vencidos);

	public void sendPreConfiguredMail(String message);
}
