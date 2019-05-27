package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the tipolugar database table.
 * 
 */

@Entity
@Table(name = "tipolugar")
public class TipoLugar extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "abrev")
	private String abreviatura;

	private String nombre;

	// bi-directional many-to-one association to Lugare
	@OneToMany(mappedBy = "tipoLugar")
	private List<Lugar> lugares;

	public TipoLugar() {
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Lugar> getLugares() {
		return lugares;
	}

	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
	}

}