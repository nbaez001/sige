package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.*;

import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: ExpedienteDocumento
 * 
 */
@Entity
@Table(name = "expedientedocumento")
public class ExpedienteDocumento extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigodependencia")
	private Dependencia dependencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigoexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigodependenciatipotramiteplantilla")
	private DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla;

	@Column(name = "nombrearchivo")
	private String nombreArchivo;

	public ExpedienteDocumento() {

	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public DependenciaTipoTramitePlantilla getDependenciaTipoTramitePlantilla() {
		return dependenciaTipoTramitePlantilla;
	}

	public void setDependenciaTipoTramitePlantilla(
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla) {
		this.dependenciaTipoTramitePlantilla = dependenciaTipoTramitePlantilla;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}
