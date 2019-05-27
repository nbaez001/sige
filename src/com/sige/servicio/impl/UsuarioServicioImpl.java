package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Usuario;
import com.sige.repositorio.UsuarioRepositorio;
import com.sige.servicio.UsuarioServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class UsuarioServicioImpl extends BaseServicioImpl<Usuario, Long>
		implements UsuarioServicio {

	@Autowired
	// private UsuarioJdbcRepositorio usuarioJdbcRepositorio;
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super(usuarioRepositorio);
		this.usuarioRepositorio = usuarioRepositorio;
	}

	public List<Usuario> buscarPorUsuario(Usuario usuario) {
		Criterio filtro = Criterio.forClass(Usuario.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("usuario", usuario.getUsuario()));
		filtro.addOrder(Order.asc("usuario"));
		List<Usuario> usuarios = buscarPorCriteria(filtro);
		return usuarios;
	}

	public Busqueda buscarPorUsuario(Usuario usuario, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio.forClass(Usuario.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("usuario", usuario.getUsuario(),
				MatchMode.ANYWHERE));
		filtro.add(Restrictions.ilike("nombres", usuario.getNombres(),
				MatchMode.ANYWHERE));

		Long totalRegistros = usuarioRepositorio.cantidadPorCriteria(filtro);
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
		filtro.addOrder(Order.asc("usuario"));

		if (totalRegistros > 0) {
			busqueda.setRegistos(usuarioRepositorio.buscarPorCriteria(filtro));
		} else {
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	public void eliminiarUsuario(Usuario usuario) {
		usuario.setEstado(Boolean.FALSE);
		usuarioRepositorio.actualizar(usuario);
	}

	public Usuario iniciarSession(Usuario usuario) {
		Criterio filtro = Criterio.forClass(Usuario.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("usuario", usuario.getUsuario()));
		filtro.add(Restrictions.eq("contrasenha",
				DigestUtils.sha512Hex(usuario.getContrasenha())
						.substring(0, 16)));
		List<Usuario> usuarios = buscarPorCriteria(filtro);
		if (usuarios.size() == 1) {
			return usuarios.get(0);
		}
		return null;
	}

	@Override
	public void crear(Usuario usuario) {
		usuario.setContrasenha(DigestUtils.sha512Hex(usuario.getContrasenha())
				.substring(0, 16));
		usuarioRepositorio.crear(usuario);
	}

	@Override
	public String getUsuario(Long usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validarDuplicado(Usuario usuario) {
		Criterio filtro = Criterio.forClass(Usuario.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("usuario", usuario.getUsuario()));

		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

}