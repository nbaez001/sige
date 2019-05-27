package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.UsuarioAcceso;
import com.sige.repositorio.UsuarioAccesoJdbcRepositorio;
import com.sige.repositorio.UsuarioAccesoRepositorio;
import com.sige.servicio.UsuarioAccesoServicio;
import com.sige.util.Criterio;

@Service
public class UsuarioAccesoServicioImpl extends
		BaseServicioImpl<UsuarioAcceso, Long> implements UsuarioAccesoServicio {

	@Autowired
	private UsuarioAccesoJdbcRepositorio usuarioAccesoJdbcRepositorio;

	@Autowired
	public UsuarioAccesoServicioImpl(
			UsuarioAccesoRepositorio usuarioAccesoRepositorio) {
		super(usuarioAccesoRepositorio);
	}

	@Override
	public List<UsuarioAcceso> buscarPorCodUsuario(Long codigoUsuario,
			Boolean todos) {
		Criterio filtro = Criterio.forClass(UsuarioAcceso.class);
		if (!todos) {
			filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		}
		filtro.add(Restrictions.eq("usuario.id", codigoUsuario));
		filtro.addOrder(Order.asc("usuario.id"));
		List<UsuarioAcceso> usuarioAcceso = buscarPorCriteria(filtro);
		return usuarioAcceso;
	}

	@Override
	public Long getCodigoGrupo(Long codigoUsuarioAcceso) {
		return usuarioAccesoJdbcRepositorio.getCodigoGrupo(codigoUsuarioAcceso);
	}

	@Override
	public List<UsuarioAcceso> buscarPorCodGrupo(Long idGrupo) {
		Criterio filtro = Criterio.forClass(UsuarioAcceso.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("grupo.id", idGrupo));
		List<UsuarioAcceso> usuarioAcceso = buscarPorCriteria(filtro);
		return usuarioAcceso;
	}

}
