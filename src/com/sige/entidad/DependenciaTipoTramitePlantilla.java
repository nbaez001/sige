package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "dependenciatipotramiteplantilla")
public class DependenciaTipoTramitePlantilla extends AuditoriaBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigodependenciatipotramite")
	private DependenciaTipoTramite dependenciaTipoTramite;

	@OneToMany(mappedBy = "dependenciaTipoTramitePlantilla")
	private List<ExpedienteDocumento> expedienteDocumentos;

	@Column(name = "nombreplantilla")
	private String nombrePlantilla;

	@Column(name = "nombrearchivo")
	private String nombreArchivo;

	@Transient
	private String rutaTemp;

	public DependenciaTipoTramitePlantilla() {

	}

	public DependenciaTipoTramite getDependenciaTipoTramite() {
		return dependenciaTipoTramite;
	}

	public void setDependenciaTipoTramite(
			DependenciaTipoTramite dependenciaTipoTramite) {
		this.dependenciaTipoTramite = dependenciaTipoTramite;
	}

	public String getNombrePlantilla() {
		return nombrePlantilla;
	}

	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public List<ExpedienteDocumento> getExpedienteDocumentos() {
		return expedienteDocumentos;
	}

	public void setExpedienteDocumentos(
			List<ExpedienteDocumento> expedienteDocumentos) {
		this.expedienteDocumentos = expedienteDocumentos;
	}

	public String getRutaTemp() {
		return rutaTemp;
	}

	public void setRutaTemp(String rutaTemp) {
		this.rutaTemp = rutaTemp;
	}

}
