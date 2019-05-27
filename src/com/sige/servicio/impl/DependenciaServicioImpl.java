package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.repositorio.DependenciaRepositorio;
import com.sige.servicio.DependenciaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class DependenciaServicioImpl extends
		BaseServicioImpl<Dependencia, Long> implements DependenciaServicio {

	@Autowired
	private DependenciaRepositorio dependenciaRepositorio;

	//@Value("${mesaPartes.codigo}")
	private Long codigoMesaPartes=SistemaProperties.mesaPartesCodigo;

	@Autowired
	public DependenciaServicioImpl(DependenciaRepositorio dependenciaRepositorio) {
		super(dependenciaRepositorio);
		this.dependenciaRepositorio = dependenciaRepositorio;
	}

	@Override
	public Busqueda buscarPorDependencia(Dependencia dependencia,Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio.forClass(Dependencia.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("nomenclatura",dependencia.getNomenclatura(), MatchMode.ANYWHERE));
		filtro.add(Restrictions.ilike("nombre", dependencia.getNombre(),MatchMode.ANYWHERE));
		if (dependencia.getPiso() > -1) {
			filtro.add(Restrictions.eq("piso", dependencia.getPiso()));
		}

		Long totalRegistros = dependenciaRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.DEPENDENCIA == 0) {
			busqueda.setNumeroPaginas(totalRegistros/ Constantes.PAGINACION.DEPENDENCIA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros/ Constantes.PAGINACION.DEPENDENCIA + 1);
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
		filtro.setMaxResults(Constantes.PAGINACION.DEPENDENCIA.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)* Constantes.PAGINACION.DEPENDENCIA.intValue());
		filtro.addOrder(Order.asc("nombre"));
		if (totalRegistros > 0) {
			busqueda.setRegistos(dependenciaRepositorio.buscarPorCriteria(filtro));
		} else {
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public void eliminiarDependencia(Dependencia dependencia) {
		dependencia.setEstado(Boolean.FALSE);
		dependenciaRepositorio.actualizar(dependencia);
	}

	@Override
	public Dependencia ObtenerDependenciaPorCodigo(Integer id) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(Dependencia.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("codigoDependSuper", id));
		List<Dependencia> resultado = dependenciaRepositorio
				.buscarPorCriteria(filtro);
		if (resultado.size() == 1) {
			return resultado.get(0);
		}
		return null;
	}

	@Override
	public Boolean validarDuplicado(Dependencia dependencia) {
		Criterio filtro = Criterio.forClass(Dependencia.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("nombre", dependencia.getNombre()));

		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Long getCodigoMesaPartes() {
		return codigoMesaPartes;
	}

}
