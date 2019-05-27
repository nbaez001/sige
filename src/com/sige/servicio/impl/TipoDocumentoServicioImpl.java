package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Grupo;
import com.sige.entidad.TipoDocumento;
import com.sige.repositorio.TipoDocumentoRepositorio;
import com.sige.servicio.TipoDocumentoServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class TipoDocumentoServicioImpl extends
		BaseServicioImpl<TipoDocumento, Long> implements TipoDocumentoServicio {
	@Autowired
	private TipoDocumentoRepositorio tipoDocumentoRepositorio;

	@Autowired
	public TipoDocumentoServicioImpl(
			TipoDocumentoRepositorio tipoDocumentoRepositorio) {
		super(tipoDocumentoRepositorio);
	}

	@Override
	public Busqueda buscarPorTipoDocumento(TipoDocumento documento,
			Long paginaActual) {
		// TODO Auto-generated method stub
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(TipoDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.and(Restrictions.not(Restrictions.eq(
						"codigoTipoDocumento", "33")), Restrictions
						.not(Restrictions.eq("codigoTipoDocumento", "34"))));
		filtro.add(Restrictions.ilike("nombre", documento.getNombre(),
				MatchMode.ANYWHERE));
		Long totalRegistros = tipoDocumentoRepositorio
				.cantidadPorCriteria(filtro);
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
		busqueda.setRegistos(tipoDocumentoRepositorio.buscarPorCriteria(filtro));
		}
		else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public void eliminarTipoDocumento(TipoDocumento tipoDocumento) {
		tipoDocumento.setEstado(Boolean.FALSE);
		tipoDocumentoRepositorio.actualizar(tipoDocumento);
	}

	@Override
	public List<TipoDocumento> buscarPorTipoDocumento(TipoDocumento documento) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(TipoDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("nombre", documento.getNombre(),
				MatchMode.ANYWHERE));
		filtro.addOrder(Order.asc("nombre"));
		List<TipoDocumento> tiposDocumentos = buscarPorCriteria(filtro);
		return tiposDocumentos;
	}

	@Override
	public String getTipoDocumento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCantidadRegistros() {

		Criterio filtro = Criterio.forClass(TipoDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		List<TipoDocumento> listado = buscarPorCriteria(filtro);
		return new Long(listado.size());
	}

	@Override
	public Boolean validarDuplicado(TipoDocumento tipoDocumento) {
		Criterio filtro = Criterio.forClass(TipoDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("nombre", tipoDocumento.getNombre()));
		if (tipoDocumento.getId() != null) {
			filtro.add(Restrictions.not(Restrictions.eq("id",
					tipoDocumento.getId())));
		}
		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public TipoDocumento obtenerTipoDocumento(String codTipoDocumento) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(TipoDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("codigoTipoDocumento", codTipoDocumento));
		List<TipoDocumento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public List<TipoDocumento> obtenerDocumentosExpediente() {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(TipoDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.and(Restrictions.not(Restrictions.eq(
						"codigoTipoDocumento", "33")), Restrictions
						.not(Restrictions.eq("codigoTipoDocumento", "34"))));
		return buscarPorCriteria(filtro);
	}
}
