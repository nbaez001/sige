package com.sige.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the docinterno database table.
 * 
 */
@Entity
@Table(name = "docinterno")
public class DocumentoInterno extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "coddocumento")
	private String codigodocumento;

	@Column(name = "aniodocumento")
	private String añoDocumento;

	private String asunto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coddependencia")
	private Dependencia dependencia;

	@OneToMany(mappedBy = "dependencia")
	private List<DocumentoInterno> documentosInternos;

	@Column(name = "codtipodocumento")
	private String codigoTipoDocumento;

	@Column(name = "copiaarchivo")
	private boolean copiaArchivo;

	private Timestamp fechadocumento;

	private String numerodocumento;

	private Short numerofolios;

	private String siglasdocumento;

	private String tiponodo;

	@OneToMany(mappedBy = "documentoInterno")
	private List<ExpedienteMovimiento> expedienteMovimientos;

	// bi-directional many-to-one association to Docinternodestino
	@OneToMany(mappedBy = "documentoInterno", fetch = FetchType.EAGER)
	private List<DocumentoInternoDestino> documentosInternosDestinos;

	@OneToMany(mappedBy = "documentoInterno")
	private List<AnexoPresentado> anexosPresentados;

	public DocumentoInterno() {
	}

	public String getCodigodocumento() {
		return codigodocumento;
	}

	public void setCodigodocumento(String codigodocumento) {
		this.codigodocumento = codigodocumento;
	}

	public String getAñoDocumento() {
		return this.añoDocumento;
	}

	public void setAñoDocumento(String añoDocumento) {
		this.añoDocumento = añoDocumento;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCodigoTipoDocumento() {
		return this.codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	public boolean getCopiaArchivo() {
		return this.copiaArchivo;
	}

	public void setCopiaArchivo(boolean copiaArchivo) {
		this.copiaArchivo = copiaArchivo;
	}

	public Timestamp getFechadocumento() {
		return this.fechadocumento;
	}

	public void setFechadocumento(Timestamp fechadocumento) {
		this.fechadocumento = fechadocumento;
	}

	public String getNumerodocumento() {
		return this.numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public short getNumerofolios() {
		return this.numerofolios;
	}

	public void setNumerofolios(short numerofolios) {
		this.numerofolios = numerofolios;
	}

	public String getSiglasdocumento() {
		return this.siglasdocumento;
	}

	public void setSiglasdocumento(String siglasdocumento) {
		this.siglasdocumento = siglasdocumento;
	}

	public String getTiponodo() {
		return this.tiponodo;
	}

	public void setTiponodo(String tiponodo) {
		this.tiponodo = tiponodo;
	}

	public List<DocumentoInternoDestino> getDocumentosInternosDestinos() {
		return this.documentosInternosDestinos;
	}

	public void setDocumentosInternosDestinos(
			List<DocumentoInternoDestino> docinternodestinos) {
		this.documentosInternosDestinos = docinternodestinos;
	}

	public DocumentoInternoDestino addDocinternodestino(
			DocumentoInternoDestino docinternodestino) {
		getDocumentosInternosDestinos().add(docinternodestino);
		docinternodestino.setDocumentoInterno(this);

		return docinternodestino;
	}

	public DocumentoInternoDestino removeDocinternodestino(
			DocumentoInternoDestino docinternodestino) {
		getDocumentosInternosDestinos().remove(docinternodestino);
		docinternodestino.setDocumentoInterno(null);

		return docinternodestino;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public List<DocumentoInterno> getDocumentosInternos() {
		return documentosInternos;
	}

	public void setDocumentosInternos(List<DocumentoInterno> documentosInternos) {
		this.documentosInternos = documentosInternos;
	}

	public void setNumerofolios(Short numerofolios) {
		this.numerofolios = numerofolios;
	}

	public List<ExpedienteMovimiento> getExpedienteMovimientos() {
		return expedienteMovimientos;
	}

	public void setExpedienteMovimientos(
			List<ExpedienteMovimiento> expedienteMovimientos) {
		this.expedienteMovimientos = expedienteMovimientos;
	}

	public List<AnexoPresentado> getAnexosPresentados() {
		return anexosPresentados;
	}

	public void setAnexosPresentados(List<AnexoPresentado> anexosPresentados) {
		this.anexosPresentados = anexosPresentados;
	}

}