package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "tipopersonajuridica")
public class TipoPersonaJuridica extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;

	@OneToMany(mappedBy = "tipoPersonaJuridica", fetch = FetchType.LAZY)
	private List<Persona> personas;

	@Column(name = "codpersona")
	private Character codigoPersona;

	public TipoPersonaJuridica() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Character getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(Character codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

}