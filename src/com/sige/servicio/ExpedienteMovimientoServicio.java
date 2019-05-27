package com.sige.servicio;

import java.util.Date;
import java.util.List;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteMovimiento;
import com.sige.util.Busqueda;
import com.sige.util.ExpedienteCanonico;

public interface ExpedienteMovimientoServicio extends
		BaseServicio<ExpedienteMovimiento, Long> {
	public Integer obtenerUltimoCorrelativo();

	public String obtenerPrimerOficinaExpediente(String codExpediente);

	public ExpedienteMovimiento obtenerExpedienteMovimiento(String codExpediente);

	public List<ExpedienteMovimiento> obtenerExpedientesOficina(
			Dependencia dependencia, ExpedienteCanonico expedienteCanonico);

	public List<ExpedienteMovimiento> obtenerExpedientesOficina(
			Dependencia dependencia, Date fechaInicio, Date FechaFin,
			ExpedienteCanonico expedienteCanonico);

	public void eliminarExpediente(Expediente expediente,
			DocumentoInterno documentoInterno,
			List<DocumentoInternoDestino> docInternoDestino,
			ExpedienteMovimiento expedienteMovimiento);

	public Integer obtenerCorrelativoPorExpediente(Expediente expediente);

	public Integer obtenerCantidadMovimientosPorExpediente(Expediente expediente);

	public List<ExpedienteMovimiento> obtenerTodosMovimientosExpediente(
			String codExpediente);

	public ExpedienteMovimiento obtenerMovimientoPorCorrelativo(
			Expediente expediente, Integer Correlativo);

	public Busqueda obtenerUltimosMovimientosdeExpediente(
			Expediente expediente, Long paginaActual);

	public ExpedienteMovimiento obtenerPrimerMovimientoExpediente(
			Expediente expediente);

	public List<ExpedienteMovimiento> buscarPorCodigoDocumento(
			String codigoDocumento);

	public Long getTotalExpedientesDependencia(Long idDependencia, Date inicio,
			Date hasta);

	public Long getTotalExpedienteNoTupaPorCodigoDocumento(
			String codigoDocumento);

	public Dependencia obtenerDependenciaEnvio(ExpedienteMovimiento movimiento);

	public Dependencia obtenerPrimerOficinaEnvio(String codigoExpediente);

	public Dependencia obtenerDependenciaActual(String codigoExpediente);

	public Boolean validarDependenciaMovimientos(Dependencia dependencia);

	public List<ExpedienteMovimiento> obtenerTodosPorRangoFechas(
			String codExpediente, Date inicio, Date hasta);

	public List<ExpedienteMovimiento> obtenerTodosPorRangoRecepcionFechas(
			String codExpediente, Date inicio, Date hasta);

}
