package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "RequisitoTupa")
public class RequisitoTupa extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Item")
	private Integer item;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "fedatear")
	private Boolean fedatear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodTipoTramite", referencedColumnName = "CodTipoTramite")
	private Tupa tupa;

	public RequisitoTupa() {

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

	public Boolean getFedatear() {
		return fedatear;
	}

	public void setFedatear(Boolean fedatear) {
		this.fedatear = fedatear;
	}

	public Tupa getTupa() {
		return tupa;
	}

	public void setTupa(Tupa tupa) {
		this.tupa = tupa;
	}

}
