package com.sige.util;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false)
	private String creadoPor;

	@Column(nullable = false)
	private Boolean estado;

	@Column(nullable = false)
	private Timestamp fechaCreacion;

	@Column(nullable = false)
	private Timestamp fechaModificacion;

	@Column(nullable = false)
	private String modificadoPor;

	@Column(nullable = false)
	private String creadoPorhostName;

	@Column(nullable = false)
	private String creadoPorip;

	@Column(nullable = false)
	private String modificadoPorhostName;

	@Column(nullable = false)
	private String modificadoPorip;

	@Column(name = "motivomodifica", nullable = true)
	private String motivoModificacion;

	protected void copy(final AuditoriaBean source) {
		this.creadoPor = source.creadoPor;
		this.fechaCreacion = source.fechaCreacion;
		this.modificadoPor = source.modificadoPor;
		this.fechaModificacion = source.fechaModificacion;
		this.modificadoPorhostName = source.modificadoPorhostName;
		this.modificadoPorip = source.modificadoPorip;
		this.creadoPorhostName = source.creadoPorhostName;
		this.creadoPorip = source.creadoPorip;
		this.motivoModificacion = source.motivoModificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public String getCreadoPorhostName() {
		return creadoPorhostName;
	}

	public void setCreadoPorhostName(String creadoPorhostName) {
		this.creadoPorhostName = creadoPorhostName;
	}

	public String getCreadoPorip() {
		return creadoPorip;
	}

	public void setCreadoPorip(String creadoPorip) {
		this.creadoPorip = creadoPorip;
	}

	public String getModificadoPorhostName() {
		return modificadoPorhostName;
	}

	public void setModificadoPorhostName(String modificadoPorhostName) {
		this.modificadoPorhostName = modificadoPorhostName;
	}

	public String getModificadoPorip() {
		return modificadoPorip;
	}

	public void setModificadoPorip(String modificadoPorip) {
		this.modificadoPorip = modificadoPorip;
	}

	public String getMotivoModificacion() {
		return motivoModificacion;
	}

	public void setMotivoModificacion(String motivoModificacion) {
		this.motivoModificacion = motivoModificacion;
	}

	public boolean esNuevo() {
		return this.id == null && this.creadoPor == null
				&& this.fechaCreacion == null;
	}

}