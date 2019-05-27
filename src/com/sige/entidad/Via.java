package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: Via
 * 
 */
@Entity
@Table(name = "vias")
public class Via extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	// bi-directional many-to-one association to Via
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codtipovia")
	private TipoVia tipoVia;

	@OneToMany(mappedBy = "via", fetch = FetchType.LAZY)
	private List<Persona> personas;

	public Via() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoVia getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(TipoVia tipoVia) {
		this.tipoVia = tipoVia;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}
