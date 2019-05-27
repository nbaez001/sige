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
 * The persistent class for the expedientereq database table.
 * 
 */
@Entity
@Table(name = "expedientereq")
public class ExpedienteRequisito extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "codtipotramite")
	private String codigoTipoTramite;

	private Integer correlativo;

	@Column(name = "documsustento")
	private String documentoSustento;

	private Boolean fedatado;

	@Column(name = "numdocsustento")
	private String numeroDocumentoSustento;

	private Boolean presenta;

	// bi-directional many-to-one association to Expediente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	public ExpedienteRequisito() {
	}

	public String getCodigoTipoTramite() {
		return this.codigoTipoTramite;
	}

	public void setCodigoTipoTramite(String codigoTipoTramite) {
		this.codigoTipoTramite = codigoTipoTramite;
	}

	public Integer getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public String getDocumentoSustento() {
		return this.documentoSustento;
	}

	public void setDocumentoSustento(String documentoSustento) {
		this.documentoSustento = documentoSustento;
	}

	public Boolean getFedatado() {
		return this.fedatado;
	}

	public void setFedatado(Boolean fedatado) {
		this.fedatado = fedatado;
	}

	public String getNumeroDocumentoSustento() {
		return this.numeroDocumentoSustento;
	}

	public void setNumeroDocumentoSustento(String numeroDocumentoSustento) {
		this.numeroDocumentoSustento = numeroDocumentoSustento;
	}

	public Boolean getPresenta() {
		return this.presenta;
	}

	public void setPresenta(Boolean presenta) {
		this.presenta = presenta;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

}