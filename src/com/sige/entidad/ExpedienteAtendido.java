package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the expedienteatendido database table.
 * 
 */
@Entity
@Table(name = "expedienteatendido")
public class ExpedienteAtendido extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	private String modificadoporhostname;

	// bi-directional many-to-one association to Expediente
	@OneToMany(mappedBy = "expedienteatendido")
	private List<Expediente> expedientes;

	public ExpedienteAtendido() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getModificadoporhostname() {
		return this.modificadoporhostname;
	}

	public void setModificadoporhostname(String modificadoporhostname) {
		this.modificadoporhostname = modificadoporhostname;
	}

	public List<Expediente> getExpedientes() {
		return this.expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}

	public Expediente addExpediente(Expediente expediente) {
		getExpedientes().add(expediente);
		expediente.setExpedienteatendido(this);

		return expediente;
	}

	public Expediente removeExpediente(Expediente expediente) {
		getExpedientes().remove(expediente);
		expediente.setExpedienteatendido(null);

		return expediente;
	}

}