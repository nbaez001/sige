package com.sige.util;

public class ExpedientePDF {

	private String expediente;
	private String fecha;
	private String nombreCiudadano;
	private String fechaRespuesta;
	private String atendido;
	private String estado;
	private String dependenciaInicial;
	private String dependenciaActual;
	private String diasTranscurridos;
	private String diasFaltantes;
	private String procedimientoAsunto;
	private String solicitante;

	public String getDiasFaltantes() {
		return diasFaltantes;
	}

	public void setDiasFaltantes(String diasFaltantes) {
		this.diasFaltantes = diasFaltantes;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreCiudadano() {
		return nombreCiudadano;
	}

	public void setNombreCiudadano(String nombreCiudadano) {
		this.nombreCiudadano = nombreCiudadano;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDependenciaInicial() {
		return dependenciaInicial;
	}

	public void setDependenciaInicial(String dependenciaInicial) {
		this.dependenciaInicial = dependenciaInicial;
	}

	public String getDependenciaActual() {
		return dependenciaActual;
	}

	public void setDependenciaActual(String dependenciaActual) {
		this.dependenciaActual = dependenciaActual;
	}

	public String getDiasTranscurridos() {
		return diasTranscurridos;
	}

	public void setDiasTranscurridos(String diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}

	public String getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(String fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public String getAtendido() {
		return atendido;
	}

	public void setAtendido(String atendido) {
		this.atendido = atendido;
	}

	public String getProcedimientoAsunto() {
		return procedimientoAsunto;
	}

	public void setProcedimientoAsunto(String procedimientoAsunto) {
		this.procedimientoAsunto = procedimientoAsunto;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

}
