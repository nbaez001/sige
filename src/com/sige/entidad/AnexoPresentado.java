package com.sige.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: AnexoPresentado
 * 
 */
@Entity
@Table(name = "anexospresentados")
public class AnexoPresentado extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codanexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codanexosolicitado")
	private AnexoSolicitado anexoSolicitado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	private String correlativo;

	@Column(name = "fechaingreso")
	private Timestamp fechaIngreso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codsolicitante", referencedColumnName = "codpersona")
	private Persona solicitante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coddependencia")
	private Dependencia dependencia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coddocumento", referencedColumnName = "coddocumento")
	private DocumentoInterno documentoInterno;

	@Column(name = "numerofolios")
	private String numeroFolios;

	@Column(name = "asunto")
	private String asunto;

	private String observaciones;

	@Column(name = "codtipopedido")
	private String codigoTipoPedido;

	@Column(name = "recepcionadopor")
	private String recepcionadoPor;

	@Column(name = "fecharecepcion")
	private Timestamp fechaRecepcion;

	public AnexoPresentado() {

	}

	public AnexoSolicitado getAnexoSolicitado() {
		return anexoSolicitado;
	}

	public void setAnexoSolicitado(AnexoSolicitado anexoSolicitado) {
		this.anexoSolicitado = anexoSolicitado;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Persona getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public DocumentoInterno getDocumentoInterno() {
		return documentoInterno;
	}

	public void setDocumentoInterno(DocumentoInterno documentoInterno) {
		this.documentoInterno = documentoInterno;
	}

	public String getNumeroFolios() {
		return numeroFolios;
	}

	public void setNumeroFolios(String numeroFolios) {
		this.numeroFolios = numeroFolios;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getCodigoTipoPedido() {
		return codigoTipoPedido;
	}

	public void setCodigoTipoPedido(String codigoTipoPedido) {
		this.codigoTipoPedido = codigoTipoPedido;
	}

	public String getRecepcionadoPor() {
		return recepcionadoPor;
	}

	public void setRecepcionadoPor(String recepcionadoPor) {
		this.recepcionadoPor = recepcionadoPor;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getCodanexo() {
		return codanexo;
	}

	public void setCodanexo(String codanexo) {
		this.codanexo = codanexo;
	}

}
