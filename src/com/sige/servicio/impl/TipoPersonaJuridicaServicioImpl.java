package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.TipoPersonaJuridica;
import com.sige.entidad.TipoTramite;
import com.sige.repositorio.TipoPersonaJuridicaRepositorio;
import com.sige.servicio.TipoPersonaJuridicaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class TipoPersonaJuridicaServicioImpl extends
		BaseServicioImpl<TipoPersonaJuridica, Long> implements
		TipoPersonaJuridicaServicio {

	@Autowired
	private TipoPersonaJuridicaRepositorio tipoPersonaJuridicaRepositorio;

	@Autowired
	protected TipoPersonaJuridicaServicioImpl(
			TipoPersonaJuridicaRepositorio tipoPersonaJuridicaRepositorio) {
		super(tipoPersonaJuridicaRepositorio);
		this.tipoPersonaJuridicaRepositorio = tipoPersonaJuridicaRepositorio;
	}

	@Override
	public Long obtenerIdPersonaJuridica(Character codTipoPersona) {
		Criterio filtro = Criterio.forClass(TipoPersonaJuridica.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("codigoPersona", codTipoPersona));
		filtro.setMaxResults(1);
		List<TipoPersonaJuridica> personasJuridicas = buscarPorCriteria(filtro);
		return personasJuridicas.get(0).getId();
	}

	@Override
	public List<TipoPersonaJuridica> buscarPorTipoPersonaJuridica(
			TipoPersonaJuridica tipoPersonaJuridica) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Busqueda buscarPorTipoPersonaJuridica(
			TipoPersonaJuridica tipePersonaJuridica, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(TipoPersonaJuridica.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.ilike("nombre",
						tipePersonaJuridica.getNombre(), MatchMode.ANYWHERE))
				.add(Restrictions.eq("codigoPersona", new Character('2')));
		Long totalRegistros = tipoPersonaJuridicaRepositorio
				.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.TIPOPERSONAJURIDICA != 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.TIPOPERSONAJURIDICA + 1);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.TIPOPERSONAJURIDICA);
		}
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		filtro.setMaxResults(Constantes.PAGINACION.TIPOPERSONAJURIDICA
				.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.TIPOPERSONAJURIDICA.intValue());
		
		if(totalRegistros>0){
		busqueda.setRegistos(tipoPersonaJuridicaRepositorio.buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}
		return busqueda;
	}

	@Override
	public Boolean validarDuplicado(TipoPersonaJuridica tipoPersonaJuridica) {
		Criterio filtro = Criterio.forClass(TipoPersonaJuridica.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("nombre", tipoPersonaJuridica.getNombre()));
		if (tipoPersonaJuridica.getId() != null) {
			filtro.add(Restrictions.not(Restrictions.eq("id",
					tipoPersonaJuridica.getId())));
		}
		List<TipoPersonaJuridica> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return false;
		}
		return true;
	}

}