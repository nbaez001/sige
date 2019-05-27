package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DependenciaTipoTramite;
import com.sige.entidad.DependenciaTipoTramitePlantilla;
import com.sige.entidad.Persona;
import com.sige.entidad.TipoTramite;
import com.sige.entidad.Usuario;
import com.sige.repositorio.DependenciaTipoTramiteRepositorio;
import com.sige.servicio.DependenciaTipoTramiteService;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class DependenciaTipoTramiteServiceImpl extends
		BaseServicioImpl<DependenciaTipoTramite, Long> implements
		DependenciaTipoTramiteService {

	@Autowired
	private DependenciaTipoTramiteRepositorio dependenciaTipoTramiteRepositorio;

	@Autowired
	protected DependenciaTipoTramiteServiceImpl(
			DependenciaTipoTramiteRepositorio dependenciaTipoTramiteRepositorio) {
		super(dependenciaTipoTramiteRepositorio);

	}

	@Override
	public DependenciaTipoTramite getDependenciaTipoTramite(
			Dependencia dependencia, TipoTramite tipoTramite) {
		Criterio filtro = Criterio.forClass(DependenciaTipoTramite.class);
		filtro.add(Restrictions.eq("dependencia.id", dependencia.getId()))
				.add(Restrictions.eq("tipoTramite.codigoTipoTramite",
						tipoTramite.getCodigoTipoTramite()))
				.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.setFetchMode("tipoTramite", FetchMode.EAGER);
		filtro.setFetchMode("dependencia", FetchMode.EAGER);
		List<DependenciaTipoTramite> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		} else {
			DependenciaTipoTramite dptt = new DependenciaTipoTramite();
			dptt.setDependencia(dependencia);
			dptt.setTipoTramite(tipoTramite);

			dependenciaTipoTramiteRepositorio.crear(dptt);
			return dptt;
		}
	}

}
