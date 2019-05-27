package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.TipoLugar;
import com.sige.repositorio.TipoLugarRepositorio;
import com.sige.servicio.TipoLugarServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class TipoLugarServicioImpl extends BaseServicioImpl<TipoLugar, Long>
		implements TipoLugarServicio {

	@Autowired
	private TipoLugarRepositorio tipoLugarRepositorio;

	@Autowired
	public TipoLugarServicioImpl(TipoLugarRepositorio tipoLugarRepositorio) {
		super(tipoLugarRepositorio);
		this.tipoLugarRepositorio = tipoLugarRepositorio;
	}

	@Override
	public List<TipoLugar> buscarPorTipoLugar(TipoLugar tipolugar) {
		Criterio filtro = Criterio.forClass(TipoLugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("nombre", tipolugar.getNombre(),
				MatchMode.ANYWHERE));
		filtro.add(Restrictions.ilike("abreviatura",
				tipolugar.getAbreviatura(), MatchMode.ANYWHERE));
		filtro.addOrder(Order.asc("nombre"));
		List<TipoLugar> tiposDocumentos = buscarPorCriteria(filtro);
		return tiposDocumentos;
	}

	@Override
	public Busqueda buscarPorTipoLugar(TipoLugar tipoLugar, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(TipoLugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("nombre", tipoLugar.getNombre(),
				MatchMode.ANYWHERE));
		filtro.add(Restrictions.ilike("abreviatura",
				tipoLugar.getAbreviatura(), MatchMode.ANYWHERE));

		Long totalRegistros = tipoLugarRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.USUARIO == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.USUARIO);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.USUARIO + 1);
		}
		// validaciones
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.USUARIO.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.USUARIO.intValue());
		filtro.addOrder(Order.asc("nombre"));
		
		if(totalRegistros>0){
		busqueda.setRegistos(tipoLugarRepositorio.buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public void eliminarTipoLugar(TipoLugar tipoLugar) {
		tipoLugar.setEstado(Boolean.FALSE);
		tipoLugarRepositorio.actualizar(tipoLugar);
	}

	@Override
	public String getTipoLugar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCantidadRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validarDuplicado(TipoLugar tipoLugar) {
		Criterio filtro = Criterio.forClass(TipoLugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("nombre", tipoLugar.getNombre()));

		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

}
