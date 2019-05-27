package com.sige.servicio;

import java.util.Date;

import com.sige.entidad.DocumentoInternoDestino;

public interface DocumentoInternoDestinoService extends
		BaseServicio<DocumentoInternoDestino, Long> {

	public Long obtenerUltimoCorrelativo();

	public DocumentoInternoDestino obtenerDocInternoDestinoPorDocInterno(
			String codDocInterno);

	public Long getTotalPorRecepcionista(String usuario, Date desde, Date hasta);

}
