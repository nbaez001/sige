package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: ExpedienteMovimiento
 * 
 */
@Entity
@Table(name = "expedientemov")
public class ExpedienteMovimiento extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "correlativo")
	private Integer correlativo;

	@Column(name = "escopia")
	private Character escopia;

	@Column(name = "orden")
	private Integer orden;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coddocumento", referencedColumnName = "coddocumento")
	private DocumentoInterno documentoInterno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coddependencia")
	private Dependencia dependencia;

	public ExpedienteMovimiento() {
		super();
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public Character getEscopia() {
		return escopia;
	}

	public void setEscopia(Character escopia) {
		this.escopia = escopia;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public DocumentoInterno getDocumentoInterno() {
		return documentoInterno;
	}

	public void setDocumentoInterno(DocumentoInterno documentoInterno) {
		this.documentoInterno = documentoInterno;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

}
