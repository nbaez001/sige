package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Grupo;
import com.sige.entidad.TipoVia;
import com.sige.repositorio.TipoViaRepositorio;
import com.sige.servicio.TipoViaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class TipoViaServicioImpl extends BaseServicioImpl<TipoVia, Long>
		implements TipoViaServicio {

	@Autowired
	private TipoViaRepositorio tipoViaRepositorio;

	@Autowired
	public TipoViaServicioImpl(TipoViaRepositorio tipoViaRepositorio) {
		super(tipoViaRepositorio);
		this.tipoViaRepositorio = tipoViaRepositorio;
	}

	public List<TipoVia> buscarPorTipoVia(TipoVia tipoVia) {
		Criterio filtro = Criterio.forClass(TipoVia.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.ilike("nombre", tipoVia.getNombre(),
						MatchMode.ANYWHERE));
		return buscarPorCriteria(filtro);
	}

	public Busqueda BuscarPorTipoVia(TipoVia tipoVia, Long pagActual) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(TipoVia.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.ilike("nombre", tipoVia.getNombre(),
						MatchMode.ANYWHERE));
		Long totalRegistros = tipoViaRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.TIPOVIA == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.TIPOVIA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.TIPOVIA + 1);
		}
		// validaciones
		if (pagActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(pagActual);
		}
		if (pagActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.TIPOVIA.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.TIPOVIA.intValue());
		filtro.addOrder(Order.asc("nombre"));
		if (totalRegistros > 0) {
			busqueda.setRegistos(buscarPorCriteria(filtro));
		} else {
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public Boolean validarDuplicado(TipoVia tipoVia) {
		Criterio filtro = Criterio.forClass(TipoVia.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("nombre", tipoVia.getNombre()));

		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

}
