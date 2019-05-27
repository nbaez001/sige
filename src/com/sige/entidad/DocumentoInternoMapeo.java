package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the docinternomapeo database table.
 * 
 */
@Entity
@Table(name = "docinternomapeo")
public class DocumentoInternoMapeo extends AuditoriaBean implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "coddocumento")
	private String codigoDocumento;

	@Column(name = "coddocumentopadre")
	private String codigoDocumentoPadre;

	private Long codigodependencia;

	private String codigotipodocumento;

	@Column(name = "cosdocumentoref")
	private String codigoDocumentoReferencia;

	private Short nivel;

	public DocumentoInternoMapeo() {
	}

	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	public String getCodigoDocumentoPadre() {
		return codigoDocumentoPadre;
	}

	public void setCodigoDocumentoPadre(String codigoDocumentoPadre) {
		this.codigoDocumentoPadre = codigoDocumentoPadre;
	}

	public Long getCodigodependencia() {
		return codigodependencia;
	}

	public void setCodigodependencia(Long codigodependencia) {
		this.codigodependencia = codigodependencia;
	}

	public String getCodigotipodocumento() {
		return codigotipodocumento;
	}

	public void setCodigotipodocumento(String codigotipodocumento) {
		this.codigotipodocumento = codigotipodocumento;
	}

	public String getCodigoDocumentoReferencia() {
		return codigoDocumentoReferencia;
	}

	public void setCodigoDocumentoReferencia(String codigoDocumentoReferencia) {
		this.codigoDocumentoReferencia = codigoDocumentoReferencia;
	}

	public Short getNivel() {
		return nivel;
	}

	public void setNivel(Short nivel) {
		this.nivel = nivel;
	}

}