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
 * Entity implementation class for Entity: ExpedientePago
 * 
 */
@Entity
@Table(name = "expedientepagos")
public class ExpedientePago extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "codtipotramite")
	private String codigoTipoTramite;

	@Column(name = "item")
	private Integer item;

	@Column(name = "numerorecibo")
	private String numeroRecibo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	@Column(name = "presenta")
	private Boolean presenta;

	public ExpedientePago() {

	}

	public String getCodigoTipoTramite() {
		return codigoTipoTramite;
	}

	public void setCodigoTipoTramite(String codigoTipoTramite) {
		this.codigoTipoTramite = codigoTipoTramite;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Boolean getPresenta() {
		return presenta;
	}

	public void setPresenta(Boolean presenta) {
		this.presenta = presenta;
	}

}
