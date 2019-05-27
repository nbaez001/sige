package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(name = "grupo")
public class Grupo extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	// bi-directional many-to-one association to Grupomenu
	@OneToMany(mappedBy = "grupo")
	private List<GrupoMenu> grupomenus;

	// bi-directional many-to-one association to Usuarioacceso
	@OneToMany(mappedBy = "grupo")
	private List<UsuarioAcceso> usuarioaccesos;

	public Grupo() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<GrupoMenu> getGrupomenus() {
		return this.grupomenus;
	}

	public void setGrupomenus(List<GrupoMenu> grupomenus) {
		this.grupomenus = grupomenus;
	}

	public GrupoMenu addGrupomenus(GrupoMenu grupomenus) {
		getGrupomenus().add(grupomenus);
		grupomenus.setGrupo(this);

		return grupomenus;
	}

	public GrupoMenu removeGrupomenus(GrupoMenu grupomenus) {
		getGrupomenus().remove(grupomenus);
		grupomenus.setGrupo(null);

		return grupomenus;
	}

	public List<UsuarioAcceso> getUsuarioaccesos() {
		return this.usuarioaccesos;
	}

	public void setUsuarioaccesos(List<UsuarioAcceso> usuarioaccesos) {
		this.usuarioaccesos = usuarioaccesos;
	}

	public UsuarioAcceso addUsuarioacceso(UsuarioAcceso usuarioacceso) {
		getUsuarioaccesos().add(usuarioacceso);
		usuarioacceso.setGrupo(this);

		return usuarioacceso;
	}

	public UsuarioAcceso removeUsuarioacceso(UsuarioAcceso usuarioacceso) {
		getUsuarioaccesos().remove(usuarioacceso);
		usuarioacceso.setGrupo(null);

		return usuarioacceso;
	}

}