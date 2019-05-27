package com.sige.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "diasnohabiles")
public class DiaNoHabil extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "anio")
	private String a�o;

	private Date fecha;

	public DiaNoHabil() {
	}

	public String getA�o() {
		return this.a�o;
	}

	public void setA�o(String a�o) {
		this.a�o = a�o;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}