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
 * Entity implementation class for Entity: DocumentoInternoDestino
 * 
 */
@Entity
@Table(name = "docinternodestino")
public class DocumentoInternoDestino extends AuditoriaBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coddocumento", referencedColumnName = "coddocumento")
	private DocumentoInterno documentoInterno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coddependencia")
	private Dependencia dependencia;

	@Column(name = "orden")
	private Short orden;

	@Column(name = "plazoatencion")
	private Short plazoAtencion;

	@Column(name = "recepcionadopor")
	private String recepcionadoPor;

	@Column(name = "fecharecepcion")
	private Timestamp fechaRecepcion;

	@Column(name = "estafinalizado")
	private Boolean estaFinalizado;

	@Column(name = "finalizadopor")
	private String finalizadoPor;

	@Column(name = "fechafinalizado")
	private Timestamp fechaFinalizacion;

	@Column(name = "textofinalizado")
	private String textoFinaliza;

	@Column(name = "tipomovimiento")
	private Character tipoMovimiento;

	public DocumentoInternoDestino() {
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

	public Short getOrden() {
		return orden;
	}

	public void setOrden(Short orden) {
		this.orden = orden;
	}

	public Short getPlazoAtencion() {
		return plazoAtencion;
	}

	public void setPlazoAtencion(Short plazoAtencion) {
		this.plazoAtencion = plazoAtencion;
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

	public Boolean getEstaFinalizado() {
		return estaFinalizado;
	}

	public void setEstaFinalizado(Boolean estaFinalizado) {
		this.estaFinalizado = estaFinalizado;
	}

	public String getFinalizadoPor() {
		return finalizadoPor;
	}

	public void setFinalizadoPor(String finalizadoPor) {
		this.finalizadoPor = finalizadoPor;
	}

	public Timestamp getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Timestamp fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public String getTextoFinaliza() {
		return textoFinaliza;
	}

	public void setTextoFinaliza(String textoFinaliza) {
		this.textoFinaliza = textoFinaliza;
	}

	public Character getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Character tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}
