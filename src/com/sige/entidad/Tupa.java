package com.sige.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tupa")
public class Tupa extends AuditoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CodDependencia")
	private Integer CodigoDependencia;

	private BigDecimal costo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodTipoTramite", referencedColumnName = "CodTipoTramite")
	private TipoTramite tipoTramite;

	@OneToMany(mappedBy = "tupa")
	private List<RequisitoTupa> requisitoTupas;

	@OneToMany(mappedBy = "tupaPago")
	private List<PagosTupa> pagosTupas;

	public Integer getCodigoDependencia() {
		return CodigoDependencia;
	}

	public void setCodigoDependencia(Integer codigoDependencia) {
		CodigoDependencia = codigoDependencia;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public TipoTramite getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(TipoTramite tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public List<RequisitoTupa> getRequisitoTupas() {
		return requisitoTupas;
	}

	public void setRequisitoTupas(List<RequisitoTupa> requisitoTupas) {
		this.requisitoTupas = requisitoTupas;
	}

	public List<PagosTupa> getPagosTupas() {
		return pagosTupas;
	}

	public void setPagosTupas(List<PagosTupa> pagosTupas) {
		this.pagosTupas = pagosTupas;
	}

}
