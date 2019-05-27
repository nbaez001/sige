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
	private String año;

	private Date fecha;

	public DiaNoHabil() {
	}

	public String getAño() {
		return this.año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}