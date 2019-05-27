package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String contrasenha;

	private String nombres;

	private String usuario;

	// bi-directional many-to-one association to Usuarioacceso
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioAcceso> usuarioaccesos;

	@OneToMany(mappedBy = "usuario")
	private List<UsuarioDependencia> usuarioDependencia;

	public Usuario() {
	}

	public String getContrasenha() {
		return this.contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioAcceso> getUsuarioaccesos() {
		return this.usuarioaccesos;
	}

	public void setUsuarioaccesos(List<UsuarioAcceso> usuarioaccesos) {
		this.usuarioaccesos = usuarioaccesos;
	}

	public UsuarioAcceso addUsuarioacceso(UsuarioAcceso usuarioacceso) {
		getUsuarioaccesos().add(usuarioacceso);
		usuarioacceso.setUsuario(this);

		return usuarioacceso;
	}

	public UsuarioAcceso removeUsuarioacceso(UsuarioAcceso usuarioacceso) {
		getUsuarioaccesos().remove(usuarioacceso);
		usuarioacceso.setUsuario(null);

		return usuarioacceso;
	}

}