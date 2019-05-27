package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "grupomenu")
public class GrupoMenu extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean acceso;

	private Boolean consultar;

	private Boolean eliminar;

	private Boolean imprimir;

	private Boolean modificar;

	private Boolean nuevo;

	// bi-directional many-to-one association to Grupo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codgrupo")
	private Grupo grupo;

	// bi-directional many-to-one association to Menu
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codmenu")
	private Menu menu;

	public GrupoMenu() {
	}

	public Boolean getAcceso() {
		return this.acceso;
	}

	public void setAcceso(Boolean acceso) {
		this.acceso = acceso;
	}

	public Boolean getConsultar() {
		return this.consultar;
	}

	public void setConsultar(Boolean consultar) {
		this.consultar = consultar;
	}

	public Boolean getEliminar() {
		return this.eliminar;
	}

	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}

	public Boolean getImprimir() {
		return this.imprimir;
	}

	public void setImprimir(Boolean imprimir) {
		this.imprimir = imprimir;
	}

	public Boolean getModificar() {
		return this.modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}

	public Boolean getNuevo() {
		return this.nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}