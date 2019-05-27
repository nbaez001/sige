package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

@Entity
@Table(name = "tipotramite")
public class TipoTramite extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "Anio")
	private String anio;

	@Column(name = "Codcorrel")
	private String codigocorrel;

	@Column(name = "CodTipoTramite", unique = true)
	private String codigoTipoTramite;

	@OneToMany(mappedBy = "tipoTramite")
	private List<Tupa> tupas;

	@OneToMany(mappedBy = "tipoTramite")
	private List<Expediente> expedientes;

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "tipoatencion")
	private Integer tipoAten;

	@Column(name = "Tupa")
	private String tupa;

	@OneToMany(mappedBy = "tipoTramite")
	private List<DependenciaTipoTramite> dependenciaTipoTramites;

	public TipoTramite() {
	}

	public List<Tupa> getTupas() {
		return tupas;
	}

	public void setTupas(List<Tupa> tupas) {
		this.tupas = tupas;
	}

	public String getAnio() {
		return this.anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getCodigocorrel() {
		return this.codigocorrel;
	}

	public void setCodigocorrel(String codigocorrel) {
		this.codigocorrel = codigocorrel;
	}

	public String getCodigoTipoTramite() {
		return this.codigoTipoTramite;
	}

	public void setCodigoTipoTramite(String codigoTipoTramite) {
		this.codigoTipoTramite = codigoTipoTramite;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTipoAten() {
		return this.tipoAten;
	}

	public void setTipoAten(Integer tipoAten) {
		this.tipoAten = tipoAten;
	}

	public String getTupa() {
		return this.tupa;
	}

	public void setTupa(String tupa) {
		this.tupa = tupa;
	}

	public List<Expediente> getExpedientes() {
		return this.expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}

	public Expediente addExpediente(Expediente expediente) {
		getExpedientes().add(expediente);
		expediente.setTipoTramite(this);

		return expediente;
	}

	public Expediente removeExpediente(Expediente expediente) {
		getExpedientes().remove(expediente);
		expediente.setTipoTramite(null);

		return expediente;
	}

	public List<DependenciaTipoTramite> getDependenciaTipoTramites() {
		return dependenciaTipoTramites;
	}

	public void setDependenciatipotramites(
			List<DependenciaTipoTramite> dependenciaTipoTramites) {
		this.dependenciaTipoTramites = dependenciaTipoTramites;
	}

}