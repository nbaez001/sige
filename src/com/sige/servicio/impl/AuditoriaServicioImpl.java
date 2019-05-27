package com.sige.servicio.impl;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Auditoria;
import com.sige.repositorio.AuditoriaRepositorio;
import com.sige.servicio.AuditoriaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class AuditoriaServicioImpl extends BaseServicioImpl<Auditoria, Integer>
		implements AuditoriaServicio {

	@Autowired
	private AuditoriaRepositorio auditoriaRepositorio;

	@Autowired
	protected AuditoriaServicioImpl(AuditoriaRepositorio auditoriaRepositorio) {
		super(auditoriaRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Busqueda consultar(Date desde, Date hasta, String nombreTabla,
			String codigo, Long paginaActual, Boolean todos) {
		Busqueda busqueda = new Busqueda();

		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio.forClass(Auditoria.class);

		filtro.add(Restrictions.between("fechaActualizacion", desde, hasta));
		filtro.add(Restrictions.eq("nombreTabla", nombreTabla));

		if (!codigo.isEmpty()) {
			filtro.add(Restrictions.eq("valorClavePrimaria", codigo));
		}

		Long totalRegistros = auditoriaRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.AUDITORIA == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.AUDITORIA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.AUDITORIA + 1);
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
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.AUDITORIA.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.AUDITORIA.intValue());
		busqueda.setRegistos(auditoriaRepositorio.buscarPorCriteria(filtro));

		return busqueda;
	}

}
