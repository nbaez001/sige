package com.sige.entidad;

import java.io.Serializable;
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
 * Entity implementation class for Entity: Lugar
 * 
 */
@Entity
@Table(name = "lugares")
public class Lugar extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codtiplugar")
	private TipoLugar tipoLugar;

	@Column(name = "codlugar")
	private String codigoLugar;
	// @Column(name="codtiplugar")
	// private String codtipolugar;

	private String nombre;

	@OneToMany(mappedBy = "lugar", fetch = FetchType.LAZY)
	private List<Persona> personas;

	public Lugar() {
		super();
	}

	public TipoLugar getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(TipoLugar tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public String getCodigoLugar() {
		return codigoLugar;
	}

	public void setCodigoLugar(String codigoLugar) {
		this.codigoLugar = codigoLugar;
	}

	public String getNombre() {
		return nombre;
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

}
