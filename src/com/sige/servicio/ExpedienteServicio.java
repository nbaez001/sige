package com.sige.servicio;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.DocumentoInternoReferen;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteMovimiento;
import com.sige.entidad.ExpedientePago;
import com.sige.entidad.ExpedienteRequisito;
import com.sige.entidad.Persona;
import com.sige.entidad.TipoTramite;
import com.sige.entidad.Usuario;
import com.sige.util.Busqueda;
import com.sige.util.ExpedienteCanonico;
import com.sige.util.TramiteTupaEstadistica;

public interface ExpedienteServicio extends BaseServicio<Expediente, Long> {

	public List<Expediente> obtenerUltimoRegistro();

	public void registrarExpediente(Expediente expediente,
			ExpedienteMovimiento expedienteMovimiento,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino,
			DocumentoInternoReferen referencia);

	public void registrarExpediente(Expediente expediente,
			ExpedienteMovimiento expedienteMovimiento,
			List<ExpedienteRequisito> expedienteRequisitos,
			List<ExpedientePago> expedientePagos,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino,
			DocumentoInternoReferen referencia);

	public void derivarExpediente(
			List<ExpedienteMovimiento> expedientesMovimiento,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino,
			List<DocumentoInternoReferen> referencias);

	public void responderExpediente(
			List<ExpedienteMovimiento> expedientesMovimiento,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino,
			List<DocumentoInternoReferen> referencias);

	public Long obtenerUltimoCorrelativo();

	public Busqueda buscarExpediente(Expediente expediente, Long paginaActual,
			ExpedienteCanonico expedienteCanonico);

	public Expediente obtenerExpediente(String codExpediente);

	public List<Expediente> obtenerExpedientesArchivadosProvicionalmente();

	public List<Expediente> obtenerExpedientesPendientesAtencion();

	public List<Expediente> obtenerExpedientes(List<String> codigosExpediente);

	public List<Expediente> consultar(Date desde, Date hasta,
			Dependencia inicial, Dependencia actual, Persona contribuyente,
			Usuario terminalista, TipoTramite procedimiento, Integer mostrar);

	public TramiteTupaEstadistica consultarEstadistica(Integer tipoEstadistica,
			String idTipoTramite, Date desde, Date hasta, Boolean finalizados);

	public Map<String, Integer[]> consultaTrazabilidad(Date desde, Date hasta,
			String idTipoTramite, String expedienteId);

	public List<Expediente> obtenerPorTipoTramite(String idTipoTramite,
			Date desde, Date hasta);
}