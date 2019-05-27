package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "expedienteescaneado")
public class ExpedienteEscaneado extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigoexpediente", referencedColumnName = "codexpediente")
	private Expediente expediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigodependencia")
	private Dependencia dependencia;

	private String descripcion;

	@Column(name = "nombrearchivoreal")
	private String nombreArchivoReal;

	@Column(name = "nombrearchivo")
	private String nombreArchivo;

	@Transient
	private String nombreArchivoTemporal;

	public ExpedienteEscaneado() {

	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreArchivoReal() {
		return nombreArchivoReal;
	}

	public void setNombreArchivoReal(String nombreArchivoReal) {
		this.nombreArchivoReal = nombreArchivoReal;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getNombreArchivoTemporal() {
		return nombreArchivoTemporal;
	}

	public void setNombreArchivoTemporal(String nombreArchivoTemporal) {
		this.nombreArchivoTemporal = nombreArchivoTemporal;
	}

}
