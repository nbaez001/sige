package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the docinternoreferencia database table.
 * 
 */
@Entity
@Table(name = "docinternoreferencia")
public class DocumentoInternoReferen extends AuditoriaBean implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "coddependencia")
	private Long codigoDependencia;

	@Column(name = "coddocumento")
	private String codigoDocumento;

	@Column(name = "coddocumentoref")
	private String codigoDocumentoReferencia;

	private Short orden;

	public DocumentoInternoReferen() {
	}

	public Long getCodigoDependencia() {
		return this.codigoDependencia;
	}

	public void setCodigoDependencia(Long codigoDependencia) {
		this.codigoDependencia = codigoDependencia;
	}

	public String getCodigoDocumento() {
		return this.codigoDocumento;
	}

	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	public String getCodigoDocumentoReferencia() {
		return this.codigoDocumentoReferencia;
	}

	public void setCodigoDocumentoReferencia(String codigoDocumentoReferencia) {
		this.codigoDocumentoReferencia = codigoDocumentoReferencia;
	}

	public Short getOrden() {
		return this.orden;
	}

	public void setOrden(Short orden) {
		this.orden = orden;
	}

}