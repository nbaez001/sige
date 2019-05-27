package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "usuariodependencia")
public class UsuarioDependencia extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coddependencia")
	private Dependencia dependencia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codusuario")
	private Usuario usuario;

	public UsuarioDependencia() {
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
