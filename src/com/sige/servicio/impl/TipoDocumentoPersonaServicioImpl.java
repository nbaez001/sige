package com.sige.servicio.impl;

import java.util.ArrayList;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.TipoDocumentoPersona;
import com.sige.entidad.TipoVia;
import com.sige.repositorio.TipoDocumentoPersonaRepositorio;
import com.sige.servicio.TipoDocumentoPersonaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class TipoDocumentoPersonaServicioImpl extends
		BaseServicioImpl<TipoDocumentoPersona, Long> implements
		TipoDocumentoPersonaServicio {

	@Autowired
	TipoDocumentoPersonaRepositorio tipoDocumentoPersonaRepositorio;

	@Autowired
	protected TipoDocumentoPersonaServicioImpl(
			TipoDocumentoPersonaRepositorio tipoDocumentoPersonaRepositorio) {
		super(tipoDocumentoPersonaRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Busqueda BuscarPorTipoDocumentoPersona(
			TipoDocumentoPersona tipoDocumentoPersona, Long pagActual) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(TipoDocumentoPersona.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.ilike("nombre", tipoDocumentoPersona.getNombre(),
						MatchMode.ANYWHERE));
		Long totalRegistros = tipoDocumentoPersonaRepositorio
				.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.TIPODOCUMENTOPERSONA == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.TIPODOCUMENTOPERSONA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.TIPODOCUMENTOPERSONA + 1);
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
		filtro.setMaxResults(Constantes.PAGINACION.TIPODOCUMENTOPERSONA
				.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.TIPOVIA.intValue());
		filtro.addOrder(Order.asc("nombre"));
		if(totalRegistros>0){
			busqueda.setRegistos(buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public Boolean validarDuplicado(TipoDocumentoPersona tipoDocumentoPersona) {
		Criterio filtro = Criterio.forClass(TipoDocumentoPersona.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("nombre", tipoDocumentoPersona.getNombre()));

		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

}
