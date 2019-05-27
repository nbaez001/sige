package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.TipoTramite;
import com.sige.repositorio.TipoTramiteRepositorio;
import com.sige.servicio.TipoTramiteService;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;
import com.sige.util.TipoTramiteCanonico;

@Service
public class TipoTramiteServiceImpl extends BaseServicioImpl<TipoTramite, Long>
		implements TipoTramiteService {

	@Autowired
	private TipoTramiteRepositorio tipoTramiteRepositorio;

	@Autowired
	public TipoTramiteServiceImpl(TipoTramiteRepositorio tipoTramiteRepositorio) {
		super(tipoTramiteRepositorio);
	}

	public List<TipoTramite> BuscarPorTipoTramite(TipoTramite tipoTramite) {
		Criterio filtro = Criterio.forClass(TipoTramite.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("codigoTipoTramite",
						tipoTramite.getCodigoTipoTramite()));
		List<TipoTramite> tipoTramites = buscarPorCriteria(filtro);
		return tipoTramites;
	}

	public Busqueda BuscarPorTipoTramite(TipoTramite tipoTramite,Long paginaActual, TipoTramiteCanonico canonico) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(TipoTramite.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(Restrictions.ilike("nombre", tipoTramite.getNombre(),MatchMode.ANYWHERE)).add(Restrictions.ilike("anio", tipoTramite.getAnio(),MatchMode.ANYWHERE));
		if (canonico != null) {
			//QUITAMOS LA RESTRICCION AÑO PORQUE SOLO LISTA DEL 2018 BY NERIO
			//filtro.add(Restrictions.eq("anio", canonico.getAnio())).add(Restrictions.eq("tupa", canonico.getTupa()));
			filtro.add(Restrictions.eq("tupa", canonico.getTupa()));
		}

		Long totalRegistros = tipoTramiteRepositorio.cantidadPorCriteria(filtro);
		//INTERRUMPIMOS PARA BUSCAR SOLO CUANDO HAY REGISTROS
		if (totalRegistros > 0) {
			// SETEA EL NUMERO DE PAGINAS A MOSTRAR
			if (totalRegistros % Constantes.PAGINACION.TIPOTRAMITE != 0) {
				busqueda.setNumeroPaginas(totalRegistros/ Constantes.PAGINACION.TIPOTRAMITE + 1);
			} else {
				busqueda.setNumeroPaginas(totalRegistros/ Constantes.PAGINACION.TIPOTRAMITE);
			}

			if (paginaActual > busqueda.getNumeroPaginas()) {
				busqueda.setPaginaActual(busqueda.getNumeroPaginas());
			} else {
				busqueda.setPaginaActual(paginaActual);
			}
			if (paginaActual <= 0) {
				busqueda.setPaginaActual(1L);
			}

			filtro.setMaxResults(Constantes.PAGINACION.TIPOTRAMITE.intValue());
			filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
					* Constantes.PAGINACION.TIPOTRAMITE.intValue());
			filtro.addOrder(Order.asc("codigoTipoTramite"));
			busqueda.setRegistos(tipoTramiteRepositorio.buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList());
		}
		return busqueda;

	}

	public List<TipoTramite> obtenerUltimoRegistro() {
		Criterio filtro = Criterio.forClass(TipoTramite.class);

		filtro.addOrder(Order.desc("codigocorrel"));
		return buscarPorCriteria(filtro);
	}

	@Override
	public TipoTramite obtenerTipoTramitePorCod(String codTramite) {
		Criterio filtro = Criterio.forClass(TipoTramite.class);
		filtro.add(Restrictions.eq("codigoTipoTramite", codTramite)).add(
				Restrictions.eq("estado", Boolean.TRUE));
		List<TipoTramite> lsTipoTramites = buscarPorCriteria(filtro);
		if (lsTipoTramites.size() == 1) {
			return lsTipoTramites.get(0);
		}
		return null;
	}

	@Override
	public List<TipoTramite> obtenerTipoTramiteNoTupa() {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(TipoTramite.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("tupa", "N"));
		return buscarPorCriteria(filtro);
	}

	@Override
	public List<TipoTramite> obtenerTipoTramiteTupa() {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(TipoTramite.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("tupa", "T"));
		return buscarPorCriteria(filtro);
	}
}
