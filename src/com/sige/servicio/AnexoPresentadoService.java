package com.sige.servicio;

import java.util.Date;
import java.util.List;

import com.sige.entidad.AnexoPresentado;
import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.DocumentoInternoReferen;
import com.sige.entidad.Expediente;
import com.sige.util.ExpedienteCanonico;

public interface AnexoPresentadoService extends
		BaseServicio<AnexoPresentado, Long> {

	public Long obtenerCorrelativo(Expediente expediente);

	public void AnexarExpediente(AnexoPresentado anexoPresentado,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino docInternoDestino,
			DocumentoInternoReferen referencias);

	public List<AnexoPresentado> getAllAnexos(Dependencia dependencia,
			ExpedienteCanonico expedienteCanonico);

	public List<AnexoPresentado> getAllAnexos(Dependencia dependencia,
			Date fechaInicio, Date FechaFin,
			ExpedienteCanonico expedienteCanonico);

	public List<AnexoPresentado> obtenerAnexosDeExpediente(Expediente expediente);

	public Integer obtenerCantidadAnexosDeExpediente(Expediente expediente);

}
