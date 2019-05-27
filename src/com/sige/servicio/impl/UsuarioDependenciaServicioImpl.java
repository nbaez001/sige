package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.UsuarioDependencia;
import com.sige.repositorio.UsuarioDependenciaRepositorio;
import com.sige.servicio.UsuarioDependenciaServicio;
import com.sige.util.Criterio;

@Service
public class UsuarioDependenciaServicioImpl extends
		BaseServicioImpl<UsuarioDependencia, Long> implements
		UsuarioDependenciaServicio {

	@Autowired
	protected UsuarioDependenciaServicioImpl(
			UsuarioDependenciaRepositorio usuarioDependenciaRepositorio) {
		super(usuarioDependenciaRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UsuarioDependencia> buscarPorCodigoUsuario(Long idUsuario,
			Boolean todos) {
		Criterio filtro = Criterio.forClass(UsuarioDependencia.class);
		if (!todos) {
			filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		}
		filtro.add(Restrictions.eq("usuario.id", idUsuario));
		filtro.addOrder(Order.asc("usuario.id"));
		List<UsuarioDependencia> usuarioDependencia = buscarPorCriteria(filtro);
		return usuarioDependencia;
	}

}
