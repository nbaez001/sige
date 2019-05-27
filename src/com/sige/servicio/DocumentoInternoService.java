package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.Expediente;
import com.sige.util.Busqueda;
import com.sige.util.ExpedienteCanonico;

public interface DocumentoInternoService extends
		BaseServicio<DocumentoInterno, Long> {

	public Long obtenerUltimoCorrelativo();

	public DocumentoInterno obtenerDocumentoInternoPorCodigo(String codDocumento);

	public Busqueda buscarDocumentoInterno(DocumentoInterno docInterno,
			Long paginaActual, ExpedienteCanonico expedienteCanonico);

	public List<Expediente> obtenerExpedientesPorDocumentoInterno(
			String codDocumento);

	public DocumentoInterno obtenerUltimoDocInternoPorExpediente(
			Expediente expediente);

	public Long obtenerNumDocumento(Dependencia dependencia);

}
