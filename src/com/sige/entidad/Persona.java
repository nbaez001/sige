package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "persona")
public class Persona extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "apellidomaterno")
	private String apellidoMaterno;

	@Column(name = "apellidopaterno")
	private String apellidoPaterno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codlugar", referencedColumnName = "codlugar")
	private Lugar lugar;

	@Column(name = "codpersona")
	private String codigoPersona;

	@Column(name = "codtipopersona")
	private Character codigoTipoPersona;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codtipopersonajuridica")
	private TipoPersonaJuridica tipoPersonaJuridica;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codvia")
	private Via via;

	@OneToMany(mappedBy = "representanteLegal")
	private List<Expediente> expedientesRepresentanteLegal;

	@OneToMany(mappedBy = "solicitante")
	private List<Expediente> expedientesSolicitante;

	@OneToMany(mappedBy = "solicitante")
	private List<AnexoPresentado> anexoPresentados;

	@Column(name = "direcciondpto")
	private String direccionDepartamento;

	@Column(name = "direccionlote")
	private String direccionLote;

	@Column(name = "direccionmzna")
	private String direccionManzana;

	@Column(name = "direccionnumero")
	private String direccionNumero;

	@Column(name = "direccionpiso")
	private String direccionPiso;

	@Column(name = "nombrecompleto")
	private String nombreCompleto;

	@Column(name = "nombres")
	private String nombres;

	@OneToMany(mappedBy = "persona")
	private List<PersonaDocumento> personasDocumentos;

	public Persona() {
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getDireccionDepartamento() {
		return this.direccionDepartamento;
	}

	public void setDireccionDepartamento(String direccionDepartamento) {
		this.direccionDepartamento = direccionDepartamento;
	}

	public String getDireccionLote() {
		return this.direccionLote;
	}

	public void setDireccionLote(String direccionLote) {
		this.direccionLote = direccionLote;
	}

	public String getDireccionManzana() {
		return this.direccionManzana;
	}

	public void setDireccionManzana(String direccionManzana) {
		this.direccionManzana = direccionManzana;
	}

	public String getDireccionNumero() {
		return this.direccionNumero;
	}

	public void setDireccionNumero(String direccionNumero) {
		this.direccionNumero = direccionNumero;
	}

	public String getDireccionPiso() {
		return this.direccionPiso;
	}

	public void setDireccionPiso(String direccionPiso) {
		this.direccionPiso = direccionPiso;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Character getCodigoTipoPersona() {
		return codigoTipoPersona;
	}

	public void setCodigoTipoPersona(Character codigoTipoPersona) {
		this.codigoTipoPersona = codigoTipoPersona;
	}

	public TipoPersonaJuridica getTipoPersonaJuridica() {
		return tipoPersonaJuridica;
	}

	public void setTipoPersonaJuridica(TipoPersonaJuridica tipoPersonaJuridica) {
		this.tipoPersonaJuridica = tipoPersonaJuridica;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public String getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(String codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public List<Expediente> getExpedientesRepresentanteLegal() {
		return expedientesRepresentanteLegal;
	}

	public void setExpedientesRepresentanteLegal(
			List<Expediente> expedientesRepresentanteLegal) {
		this.expedientesRepresentanteLegal = expedientesRepresentanteLegal;
	}

	public List<Expediente> getExpedientesSolicitante() {
		return expedientesSolicitante;
	}

	public void setExpedientesSolicitante(
			List<Expediente> expedientesSolicitante) {
		this.expedientesSolicitante = expedientesSolicitante;
	}

	public List<AnexoPresentado> getAnexoPresentados() {
		return anexoPresentados;
	}

	public void setAnexoPresentados(List<AnexoPresentado> anexoPresentados) {
		this.anexoPresentados = anexoPresentados;
	}

	public List<PersonaDocumento> getPersonasDocumentos() {
		return personasDocumentos;
	}

	public void setPersonasDocumentos(List<PersonaDocumento> personasDocumentos) {
		this.personasDocumentos = personasDocumentos;
	}

}