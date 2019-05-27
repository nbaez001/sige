package com.sige.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: Expediente
 * 
 */
@Entity
@Table(name = "expediente")
public class Expediente extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "codexpediente")
	private String codigo;

	@OneToMany(mappedBy = "expediente")
	private List<ExpedienteMovimiento> expedientesMovimiento;

	@OneToMany(mappedBy = "expediente")
	private List<ExpedienteRequisito> expedientesResquisito;

	@OneToMany(mappedBy = "expediente")
	private List<ExpedientePago> expedientesPagos;

	@Column(name = "numeroexpediente")
	private String numero;

	@Column(name = "anioexpediente")
	private String anio;

	@Column(name = "numerofolios")
	private Short numeroFolios;

	@Column(name = "fechaexpediente")
	private Timestamp fechaExpediente;

	@Column(name = "asunto")
	private String asunto;

	@Column(name = "numerodoc")
	private String numeroDocumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codsolicitante", referencedColumnName = "codpersona")
	private Persona solicitante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codtipodocumento", referencedColumnName = "codtipodocumento")
	private TipoDocumento tipoDocumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codtipotramite", referencedColumnName = "codtipotramite")
	private TipoTramite tipoTramite;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codrepresentlegal", referencedColumnName = "codpersona")
	private Persona representanteLegal;

	@OneToMany(mappedBy = "expediente")
	private List<AnexoSolicitado> anexossolicitados;

	@OneToMany(mappedBy = "expediente")
	private List<AnexoPresentado> anexoPresentados;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "archivoProv")
	private Boolean archivoProvicional;

	@Column(name = "tramitefinalizado")
	private Boolean tramiteFinalizado;

	@Column(name = "fichafinalizado")
	private Timestamp fechaFinalizado;

	@Column(name = "codtipodocumfinaliza")
	private String codigoTipoDocumentoFinaliza;

	@Column(name = "numerodocumFinaliza")
	private String numeroDocumentoFinaliza;

	@Column(name = "textofinaliza")
	private String textoFinaliza;

	@Column(name = "finalizadopor")
	private String finalizadoPor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "administradoatendido")
	private ExpedienteAtendido expedienteatendido;

	@Column(name = "fechaatencionadminist")
	private Date fechaAtencionAdministrado;

	@Column(name = "archivocentral")
	private Boolean archivoCentral;

	@Column(name = "fechaarchivocentral")
	private Date fechaEnvioArchivoCentral;

	private Boolean atendido;

	@Transient
	private Integer diasTranscurridos;

	@Transient
	private Integer diasFaltantes;

	@OneToMany(mappedBy = "expediente")
	private List<ExpedienteDocumento> expedienteDocumentos;

	@OneToMany(mappedBy = "expediente")
	private List<ExpedienteEscaneado> expedientesEscaneados;

	@Transient
	private String atendidoTipo;

	@Transient
	private Long diasVencidos;

	public Expediente() {

	}

	public Integer getDiasTranscurridos() {
		return diasTranscurridos;
	}

	public void setDiasTranscurridos(Integer diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}

	public Integer getDiasFaltantes() {
		return diasFaltantes;
	}

	public void setDiasFaltantes(Integer diasFaltantes) {
		this.diasFaltantes = diasFaltantes;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<ExpedienteMovimiento> getExpedientesMovimiento() {
		return expedientesMovimiento;
	}

	public void setExpedientesMovimiento(
			List<ExpedienteMovimiento> expedientesMovimiento) {
		this.expedientesMovimiento = expedientesMovimiento;
	}

	public List<ExpedienteRequisito> getExpedientesResquisito() {
		return expedientesResquisito;
	}

	public void setExpedientesResquisito(
			List<ExpedienteRequisito> expedientesResquisito) {
		this.expedientesResquisito = expedientesResquisito;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Short getNumeroFolios() {
		return numeroFolios;
	}

	public void setNumeroFolios(Short numeroFolios) {
		this.numeroFolios = numeroFolios;
	}

	public Timestamp getFechaExpediente() {
		return fechaExpediente;
	}

	public void setFechaExpediente(Timestamp fechaExpediente) {
		this.fechaExpediente = fechaExpediente;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Persona getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoTramite getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(TipoTramite tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public Persona getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getArchivoProvicional() {
		return archivoProvicional;
	}

	public void setArchivoProvicional(Boolean archivoProvicional) {
		this.archivoProvicional = archivoProvicional;
	}

	public Boolean getTramiteFinalizado() {
		return tramiteFinalizado;
	}

	public void setTramiteFinalizado(Boolean tramiteFinalizado) {
		this.tramiteFinalizado = tramiteFinalizado;
	}

	public Timestamp getFechaFinalizado() {
		return fechaFinalizado;
	}

	public void setFechaFinalizado(Timestamp fechaFinalizado) {
		this.fechaFinalizado = fechaFinalizado;
	}

	public String getCodigoTipoDocumentoFinaliza() {
		return codigoTipoDocumentoFinaliza;
	}

	public void setCodigoTipoDocumentoFinaliza(
			String codigoTipoDocumentoFinaliza) {
		this.codigoTipoDocumentoFinaliza = codigoTipoDocumentoFinaliza;
	}

	public String getNumeroDocumentoFinaliza() {
		return numeroDocumentoFinaliza;
	}

	public void setNumeroDocumentoFinaliza(String numeroDocumentoFinaliza) {
		this.numeroDocumentoFinaliza = numeroDocumentoFinaliza;
	}

	public String getTextoFinaliza() {
		return textoFinaliza;
	}

	public void setTextoFinaliza(String textoFinaliza) {
		this.textoFinaliza = textoFinaliza;
	}

	public String getFinalizadoPor() {
		return finalizadoPor;
	}

	public void setFinalizadoPor(String finalizadoPor) {
		this.finalizadoPor = finalizadoPor;
	}

	public ExpedienteAtendido getExpedienteatendido() {
		return expedienteatendido;
	}

	public void setExpedienteatendido(ExpedienteAtendido expedienteatendido) {
		this.expedienteatendido = expedienteatendido;
	}

	public Date getFechaAtencionAdministrado() {
		return fechaAtencionAdministrado;
	}

	public void setFechaAtencionAdministrado(Date fechaAtencionAdministrado) {
		this.fechaAtencionAdministrado = fechaAtencionAdministrado;
	}

	public Boolean getArchivoCentral() {
		return archivoCentral;
	}

	public void setArchivoCentral(Boolean archivoCentral) {
		this.archivoCentral = archivoCentral;
	}

	public Date getFechaEnvioArchivoCentral() {
		return fechaEnvioArchivoCentral;
	}

	public void setFechaEnvioArchivoCentral(Date fechaEnvioArchivoCentral) {
		this.fechaEnvioArchivoCentral = fechaEnvioArchivoCentral;
	}

	public List<ExpedientePago> getExpedientesPagos() {
		return expedientesPagos;
	}

	public void setExpedientesPagos(List<ExpedientePago> expedientesPagos) {
		this.expedientesPagos = expedientesPagos;
	}

	public Boolean getAtendido() {
		return atendido;
	}

	public void setAtendido(Boolean atendido) {
		this.atendido = atendido;
	}

	public List<AnexoSolicitado> getAnexossolicitados() {
		return anexossolicitados;
	}

	public void setAnexossolicitados(List<AnexoSolicitado> anexossolicitados) {
		this.anexossolicitados = anexossolicitados;
	}

	public List<AnexoPresentado> getAnexoPresentados() {
		return anexoPresentados;
	}

	public void setAnexoPresentados(List<AnexoPresentado> anexoPresentados) {
		this.anexoPresentados = anexoPresentados;
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

	public String getAtendidoTipo() {
		return atendidoTipo;
	}

	public void setAtendidoTipo(String atendidoTipo) {
		this.atendidoTipo = atendidoTipo;
	}

	public Long getDiasVencidos() {
		return diasVencidos;
	}

	public void setDiasVencidos(Long diasVencidos) {
		this.diasVencidos = diasVencidos;
	}

}