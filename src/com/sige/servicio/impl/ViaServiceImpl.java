package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Via;
import com.sige.repositorio.ViaRepositorio;
import com.sige.servicio.ViaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class ViaServiceImpl extends BaseServicioImpl<Via, Long> implements
		ViaServicio {

	@Autowired
	private ViaRepositorio viaRepositorio;

	@Autowired
	public ViaServiceImpl(ViaRepositorio viaRepositorio) {
		super(viaRepositorio);
		this.viaRepositorio = viaRepositorio;
	}

	public List<Via> buscarPorVia(Via via) {
		Criterio filtro = Criterio.forClass(Via.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.ilike("nombre", via.getNombre(),
						MatchMode.ANYWHERE));
		if (via.getTipoVia() != null) {
			filtro.createCriteria("tipoVia").add(
					Restrictions.ilike("nombre", via.getTipoVia().getNombre(),
							MatchMode.ANYWHERE));
		}

		return buscarPorCriteria(filtro);
	}

	public Busqueda buscarPorVia(Via via, Long pagActual) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(Via.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.ilike("nombre", via.getNombre(),
						MatchMode.ANYWHERE));

		if (via.getTipoVia() != null) {
			filtro.createCriteria("tipoVia").add(
					Restrictions.ilike("nombre", via.getTipoVia().getNombre(),
							MatchMode.ANYWHERE));
		}

		Long totalRegistros = viaRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.VIA == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.VIA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.VIA + 1);
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
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.VIA.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.VIA.intValue());
		filtro.addOrder(Order.asc("nombre"));
		if(totalRegistros>0){
		busqueda.setRegistos(buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public boolean validarDuplicidad(Via via) {
		Criterio filtro = Criterio.forClass(Via.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("nombre", via.getNombre()));
		filtro.add(Restrictions.eq("tipoVia.id", via.getTipoVia().getId()));
		if (via.getId() != null) {
			filtro.add(Restrictions.not(Restrictions.eq("id", via.getId())));
		}
		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}
}
