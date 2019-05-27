package com.sige.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "pagostupa")
public class PagosTupa extends AuditoriaBean implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codtipotramite", referencedColumnName = "CodTipoTramite")
	private Tupa tupaPago;

	private Integer item;

	private String descripcion;

	private BigDecimal costo;

	public PagosTupa() {

	}

	public Tupa getTupaPago() {
		return tupaPago;
	}

	public void setTupaPago(Tupa tupaPago) {
		this.tupaPago = tupaPago;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

}
