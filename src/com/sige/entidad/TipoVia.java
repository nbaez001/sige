package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "tipovia")
public class TipoVia extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "abrev")
	private String abreviatura;

	@OneToMany(mappedBy = "tipoVia")
	private List<Via> vias;

	public TipoVia() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public List<Via> getVias() {
		return vias;
	}

	public void setVias(List<Via> vias) {
		this.vias = vias;
	}

}