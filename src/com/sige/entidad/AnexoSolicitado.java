package com.sige.entidad;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the anexossolicitados database table.
 * 
 */
@Entity
@Table(name = "anexossolicitados")
public class AnexoSolicitado extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "codanexo")
	private String codigoAnexo;

	@Column(name = "codtipopedido")
	private String codigoTipoPedido;

	private String correlativo;

	@Column(name = "fechaanexo")
	private Timestamp fechaAnexo;

	@Column(name = "fechaentreganotif")
	private Timestamp fechaEntregaNotificacion;

	@Column(name = "plazootorgado")
	private Short plazoOtorgado;

	@Column(name = "registraentreganotif")
	private String registraEntregaNotificacion;

	// bi-directional many-to-one association to Dependencia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coddependencia")
	private Dependencia dependencia;

	// bi-directional many-to-one association to Expediente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	// bi-directional many-to-one association to Anexospresentado
	@OneToMany(mappedBy = "anexoSolicitado")
	private List<AnexoPresentado> anexoPresentados;

	public AnexoSolicitado() {
	}

	public String getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public Dependencia getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public List<AnexoPresentado> getAnexospresentados() {
		return this.anexoPresentados;
	}

	public void setAnexospresentados(List<AnexoPresentado> anexospresentados) {
		this.anexoPresentados = anexospresentados;
	}

	public AnexoPresentado addAnexospresentado(AnexoPresentado anexospresentado) {
		getAnexospresentados().add(anexospresentado);
		anexospresentado.setAnexoSolicitado(this);

		return anexospresentado;
	}

	public AnexoPresentado removeAnexospresentado(
			AnexoPresentado anexospresentado) {
		getAnexospresentados().remove(anexospresentado);
		anexospresentado.setAnexoSolicitado(null);

		return anexospresentado;
	}

	public String getCodigoTipoPedido() {
		return codigoTipoPedido;
	}

	public void setCodigoTipoPedido(String codigoTipoPedido) {
		this.codigoTipoPedido = codigoTipoPedido;
	}

	public Timestamp getFechaAnexo() {
		return fechaAnexo;
	}

	public void setFechaAnexo(Timestamp fechaAnexo) {
		this.fechaAnexo = fechaAnexo;
	}

	public Timestamp getFechaEntregaNotificacion() {
		return fechaEntregaNotificacion;
	}

	public void setFechaEntregaNotificacion(Timestamp fechaEntregaNotificacion) {
		this.fechaEntregaNotificacion = fechaEntregaNotificacion;
	}

	public Short getPlazoOtorgado() {
		return plazoOtorgado;
	}

	public void setPlazoOtorgado(Short plazoOtorgado) {
		this.plazoOtorgado = plazoOtorgado;
	}

	public String getRegistraEntregaNotificacion() {
		return registraEntregaNotificacion;
	}

	public void setRegistraEntregaNotificacion(
			String registraEntregaNotificacion) {
		this.registraEntregaNotificacion = registraEntregaNotificacion;
	}

	public String getCodigoAnexo() {
		return codigoAnexo;
	}

	public void setCodigoAnexo(String codigoAnexo) {
		this.codigoAnexo = codigoAnexo;
	}

	public List<AnexoPresentado> getAnexoPresentados() {
		return anexoPresentados;
	}

	public void setAnexoPresentados(List<AnexoPresentado> anexoPresentados) {
		this.anexoPresentados = anexoPresentados;
	}

}