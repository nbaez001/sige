package com.sige.util;

import com.sige.entidad.GrupoMenu;

public class Permiso {

	private Boolean consultar;

	private Boolean eliminar;

	private Boolean imprimir;

	private Boolean modificar;

	private Boolean nuevo;

	public Permiso(GrupoMenu grupoMenu) {
		setConsultar(grupoMenu.getConsultar());
		setEliminar(grupoMenu.getEliminar());
		setImprimir(grupoMenu.getImprimir());
		setModificar(grupoMenu.getModificar());
		setNuevo(grupoMenu.getNuevo());
	}

	private void inicializar() {
		consultar = false;
		eliminar = false;
		imprimir = false;
		modificar = false;
		nuevo = false;
	}

	public void addPermisos(GrupoMenu grupoMenu) {
		if (getConsultar() || grupoMenu.getConsultar()) {
			setConsultar(true);
		}
		if (getEliminar() || grupoMenu.getEliminar()) {
			setEliminar(true);
		}
		if (getImprimir() || grupoMenu.getImprimir()) {
			setImprimir(true);
		}
		if (getNuevo() || grupoMenu.getNuevo()) {
			setNuevo(true);
		}
		if (getModificar() || grupoMenu.getModificar()) {
			setModificar(true);
		}

	}

	public Boolean getConsultar() {
		return consultar;
	}

	public void setConsultar(Boolean consultar) {
		this.consultar = consultar;
	}

	public Boolean getEliminar() {
		return eliminar;
	}

	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}

	public Boolean getImprimir() {
		return imprimir;
	}

	public void setImprimir(Boolean imprimir) {
		this.imprimir = imprimir;
	}

	public Boolean getModificar() {
		return modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}

	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

}
