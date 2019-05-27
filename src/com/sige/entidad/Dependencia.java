package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "dependencias")
public class Dependencia extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CodDependSuper")
	private Integer codigoDependSuper;

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Nomenclatura")
	private String nomenclatura;

	@Column(name = "Piso")
	private Integer piso;

	@OneToMany(mappedBy = "dependencia")
	private List<DocumentoInterno> documentosInternos;

	@OneToMany(mappedBy = "dependencia")
	private List<UsuarioDependencia> usuario;

	@OneToMany(mappedBy = "dependencia")
	private List<DocumentoInternoDestino> documentosInternosDestinos;

	@OneToMany(mappedBy = "dependencia")
	private List<ExpedienteMovimiento> expedienteMovimiento;

	@OneToMany(mappedBy = "dependencia")
	private List<AnexoSolicitado> anexoSolicitados;

	// bi-directional many-to-one association to Anexospresentado
	@OneToMany(mappedBy = "dependencia")
	private List<AnexoPresentado> anexoPresentados;

	@OneToMany(mappedBy = "dependencia")
	private List<DependenciaTipoTramite> dependenciaTipoTramites;

	@OneToMany(mappedBy = "dependencia")
	private List<ExpedienteDocumento> expedienteDocumentos;

	// bi-directional many-to-one association to Expedienteescaneado
	@OneToMany(mappedBy = "dependencia")
	private List<ExpedienteEscaneado> expedientesEscaneados;

	public Dependencia() {
	}

	public Integer getCodigoDependSuper() {
		return this.codigoDependSuper;
	}

	public void setCodigoDependSuper(Integer codigoDependSuper) {
		this.codigoDependSuper = codigoDependSuper;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomenclatura() {
		return this.nomenclatura;
	}

	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public Integer getPiso() {
		return this.piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public List<DocumentoInterno> getDocumentosInternos() {
		return documentosInternos;
	}

	public void setDocumentosInternos(List<DocumentoInterno> documentosInternos) {
		this.documentosInternos = documentosInternos;
	}

	public List<UsuarioDependencia> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<UsuarioDependencia> usuario) {
		this.usuario = usuario;
	}

	public List<DocumentoInternoDestino> getDocumentosInternosDestinos() {
		return documentosInternosDestinos;
	}

	public void setDocumentosInternosDestinos(
			List<DocumentoInternoDestino> documentosInternosDestinos) {
		this.documentosInternosDestinos = documentosInternosDestinos;
	}

	public List<ExpedienteMovimiento> getExpedienteMovimiento() {
		return expedienteMovimiento;
	}

	public void setExpedienteMovimiento(
			List<ExpedienteMovimiento> expedienteMovimiento) {
		this.expedienteMovimiento = expedienteMovimiento;
	}

	public List<AnexoSolicitado> getAnexoSolicitados() {
		return anexoSolicitados;
	}

	public void setAnexoSolicitados(List<AnexoSolicitado> anexoSolicitados) {
		this.anexoSolicitados = anexoSolicitados;
	}

	public List<AnexoPresentado> getAnexoPresentados() {
		return anexoPresentados;
	}

	public void setAnexoPresentados(List<AnexoPresentado> anexoPresentados) {
		this.anexoPresentados = anexoPresentados;
	}

	public List<DependenciaTipoTramite> getDependenciaTipoTramites() {
		return dependenciaTipoTramites;
	}

	public void setDependenciatipotramites(
			List<DependenciaTipoTramite> dependenciaTipoTramites) {
		this.dependenciaTipoTramites = dependenciaTipoTramites;
	}

	public List<ExpedienteDocumento> getExpedienteDocumentos() {
		return expedienteDocumentos;
	}

	public void setExpedienteDocumentos(
			List<ExpedienteDocumento> expedienteDocumentos) {
		this.expedienteDocumentos = expedienteDocumentos;
	}

	public List<ExpedienteEscaneado> getExpedientesEscaneados() {
		return expedientesEscaneados;
	}

	public void setExpedientesEscaneados(
			List<ExpedienteEscaneado> expedientesEscaneados) {
		this.expedientesEscaneados = expedientesEscaneados;
	}

	public void setDependenciaTipoTramites(
			List<DependenciaTipoTramite> dependenciaTipoTramites) {
		this.dependenciaTipoTramites = dependenciaTipoTramites;
	}

}