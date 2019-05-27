package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the usuarioacceso database table.
 * 
 */
@Entity
@Table(name = "usuarioacceso")
public class UsuarioAcceso extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Grupo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codgrupo")
	private Grupo grupo;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codusuario")
	private Usuario usuario;

	public UsuarioAcceso() {
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}