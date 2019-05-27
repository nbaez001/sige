package com.sige.entidad;

import java.io.Serializable;

import javax.persistence.*;

import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: PersonaDocumento
 * 
 */
@Entity
@Table(name = "personasdocum")
public class PersonaDocumento extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codpersona", referencedColumnName = "codpersona")
	private Persona persona;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codtipodocum")
	private TipoDocumentoPersona tipoDocumentoPersona;

	@Column(name = "numdocum")
	private String numeroDocumento;

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoDocumentoPersona getTipoDocumentoPersona() {
		return tipoDocumentoPersona;
	}

	public void setTipoDocumentoPersona(
			TipoDocumentoPersona tipoDocumentoPersona) {
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public PersonaDocumento() {
	}

}
