package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.sige.entidad.Dependencia;
import com.sige.entidad.TipoTramite;
import com.sige.util.AuditoriaBean;

/**
 * Entity implementation class for Entity: DependenciaTipoTramite
 * 
 */
@Entity
@Table(name = "dependenciatipotramite")
public class DependenciaTipoTramite extends AuditoriaBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigotipotramite", referencedColumnName = "codtipotramite")
	private TipoTramite tipoTramite;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigodependencia")
	private Dependencia dependencia;

	@OneToMany(mappedBy = "dependenciaTipoTramite")
	private List<DependenciaTipoTramitePlantilla> dependenciaTipoTramitePlantillas;

	public DependenciaTipoTramite() {

	}

	public TipoTramite getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(TipoTramite tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public List<DependenciaTipoTramitePlantilla> getDependenciaTipoTramitePlantillas() {
		return dependenciaTipoTramitePlantillas;
	}

	public void setDependenciaTipoTramitePlantillas(
			List<DependenciaTipoTramitePlantilla> dependenciaTipoTramitePlantillas) {
		this.dependenciaTipoTramitePlantillas = dependenciaTipoTramitePlantillas;
	}

}
