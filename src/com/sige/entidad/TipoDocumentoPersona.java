package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "tipodocumpersona")
public class TipoDocumentoPersona extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "abrev")
	private String abreviatura;

	private String nombre;

	// bi-directional many-to-one association to Personasdocum
	@OneToMany(mappedBy = "tipoDocumentoPersona")
	private List<PersonaDocumento> personasDocumentos;

	public TipoDocumentoPersona() {
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PersonaDocumento> getPersonasDocumentos() {
		return this.personasDocumentos;
	}

	public void setPersonasdocums(List<PersonaDocumento> personasDocumentos) {
		this.personasDocumentos = personasDocumentos;
	}

	public PersonaDocumento addPersonasdocum(PersonaDocumento personaDocumento) {
		getPersonasDocumentos().add(personaDocumento);
		personaDocumento.setTipoDocumentoPersona(this);

		return personaDocumento;
	}

	public PersonaDocumento removePersonasdocum(
			PersonaDocumento personaDocumento) {
		getPersonasDocumentos().remove(personaDocumento);
		personaDocumento.setTipoDocumentoPersona(null);

		return personaDocumento;
	}

}