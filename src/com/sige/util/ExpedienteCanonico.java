package com.sige.util;

import java.util.List;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.Expediente;

public class ExpedienteCanonico {
	public Boolean recibido;
	public Long codDependencia;
	public String dependencia;
	public String asunto;
	public String numExpediente;
	public String numDocumentoInterno;
	public String codExpediente;
	public String codDocumentoInterno;
	public Long codExpedienteMovimiento;
	public Integer tipoBusqueda;
	private Expediente expediente;
	private DocumentoInternoDestino docInternoDestino;
	private Dependencia dependenciaEntidad;
	private List<String> expedientesRepetidos;
	private List<String> documentosRepetidos;
	private List<Long> codigoMovimientos;
	private Boolean derivar;
	private String nombreSolicitante;

	public Boolean getRecibido() {
		return recibido;
	}

	public void setRecibido(Boolean recibido) {
		this.recibido = recibido;
	}

	public Long getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(Long codDependencia) {
		this.codDependencia = codDependencia;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNumExpediente() {
		return numExpediente;
	}

	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getNumDocumentoInterno() {
		return numDocumentoInterno;
	}

	public void setNumDocumentoInterno(String numDocumentoInterno) {
		this.numDocumentoInterno = numDocumentoInterno;
	}

	public String getCodExpediente() {
		return codExpediente;
	}

	public void setCodExpediente(String codExpediente) {
		this.codExpediente = codExpediente;
	}

	public String getCodDocumentoInterno() {
		return codDocumentoInterno;
	}

	public void setCodDocumentoInterno(String codDocumentoInterno) {
		this.codDocumentoInterno = codDocumentoInterno;
	}

	public Long getCodExpedienteMovimiento() {
		return codExpedienteMovimiento;
	}

	public void setCodExpedienteMovimiento(Long codExpedienteMovimiento) {
		this.codExpedienteMovimiento = codExpedienteMovimiento;
	}

	public Integer getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(Integer tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public DocumentoInternoDestino getDocInternoDestino() {
		return docInternoDestino;
	}

	public void setDocInternoDestino(DocumentoInternoDestino docInternoDestino) {
		this.docInternoDestino = docInternoDestino;
	}

	public Dependencia getDependenciaEntidad() {
		return dependenciaEntidad;
	}

	public void setDependenciaEntidad(Dependencia dependenciaEntidad) {
		this.dependenciaEntidad = dependenciaEntidad;
	}

	public List<String> getExpedientesRepetidos() {
		return expedientesRepetidos;
	}

	public void setExpedientesRepetidos(List<String> expedientesRepetidos) {
		this.expedientesRepetidos = expedientesRepetidos;
	}

	public List<String> getDocumentosRepetidos() {
		return documentosRepetidos;
	}

	public void setDocumentosRepetidos(List<String> documentosRepetidos) {
		this.documentosRepetidos = documentosRepetidos;
	}

	public Boolean getDerivar() {
		return derivar;
	}

	public void setDerivar(Boolean derivar) {
		this.derivar = derivar;
	}

	public List<Long> getCodigoMovimientos() {
		return codigoMovimientos;
	}

	public void setCodigoMovimientos(List<Long> codigoMovimientos) {
		this.codigoMovimientos = codigoMovimientos;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

}
