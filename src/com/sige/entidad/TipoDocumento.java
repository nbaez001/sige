package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "tipodocumento")
public class TipoDocumento extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CodTipoDocumento")
	private String codigoTipoDocumento;

	@OneToMany(mappedBy = "tipoDocumento")
	private List<Expediente> expedientes;

	private String nombre;

	public TipoDocumento() {
	}

	public String getCodigoTipoDocumento() {
		return this.codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Long idTipoDocumento) {
		if (idTipoDocumento < 10) {
			this.codigoTipoDocumento = "0" + idTipoDocumento;
		} else {
			this.codigoTipoDocumento = "" + idTipoDocumento;
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Expediente> getExpedientes() {
		return this.expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}

	public Expediente addExpediente(Expediente expediente) {
		getExpedientes().add(expediente);
		expediente.setTipoDocumento(this);

		return expediente;
	}

	public Expediente removeExpediente(Expediente expediente) {
		getExpedientes().remove(expediente);
		expediente.setTipoDocumento(null);

		return expediente;
	}

}