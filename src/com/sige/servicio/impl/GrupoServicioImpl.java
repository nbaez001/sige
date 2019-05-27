package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Grupo;
import com.sige.entidad.GrupoMenu;
import com.sige.entidad.Menu;
import com.sige.entidad.UsuarioAcceso;
import com.sige.repositorio.GrupoMenuRepositorio;
import com.sige.repositorio.GrupoRepositorio;
import com.sige.repositorio.MenuRepositorio;
import com.sige.servicio.GrupoMenuServicio;
import com.sige.servicio.GrupoServicio;
import com.sige.servicio.UsuarioAccesoServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class GrupoServicioImpl extends BaseServicioImpl<Grupo, Long> implements
		GrupoServicio {

	@Autowired
	private GrupoRepositorio grupoRepositorio;

	@Autowired
	private MenuRepositorio menuRepositorio;

	@Autowired
	private GrupoMenuRepositorio grupoMenuRepositorio;

	@Autowired
	private GrupoMenuServicio grupoMenuServicio;

	@Autowired
	private UsuarioAccesoServicio usuarioAccesoServicio;

	@Autowired
	public GrupoServicioImpl(GrupoRepositorio grupoRepositorio) {
		super(grupoRepositorio);
		this.grupoRepositorio = grupoRepositorio;
	}

	@Override
	public Busqueda buscarPorGrupo(Grupo grupo, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio.forClass(Grupo.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("descripcion", grupo.getDescripcion(),
				MatchMode.ANYWHERE));

		Long totalRegistros = grupoRepositorio.cantidadPorCriteria(filtro);

		if (totalRegistros % Constantes.PAGINACION.GRUPO == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.GRUPO);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.GRUPO + 1);
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
		filtro.setMaxResults(Constantes.PAGINACION.GRUPO.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.GRUPO.intValue());
		filtro.addOrder(Order.asc("descripcion"));
		if (totalRegistros > 0) {
			busqueda.setRegistos(grupoRepositorio.buscarPorCriteria(filtro));
		} else {
			busqueda.setRegistos(new ArrayList<>());
		}
		return busqueda;
	}

	@Override
	public void eliminiarGrupo(Grupo grupo) {
		grupo.setEstado(Boolean.FALSE);
		grupoRepositorio.actualizar(grupo);
		List<GrupoMenu> grupoMenus = grupoMenuServicio.buscarPorCodGrupo(
				grupo.getId(), true);
		for (GrupoMenu grupoMenu : grupoMenus) {
			grupoMenu.setEstado(Boolean.FALSE);
			grupoMenu.setMotivoModificacion(grupo.getMotivoModificacion());
			grupoMenuRepositorio.actualizar(grupoMenu);
		}

		List<UsuarioAcceso> usuarioAccesos = usuarioAccesoServicio
				.buscarPorCodGrupo(grupo.getId());
		for (UsuarioAcceso usuarioAcceso : usuarioAccesos) {
			usuarioAcceso.setEstado(false);
			usuarioAcceso.setMotivoModificacion(grupo.getMotivoModificacion());
			usuarioAccesoServicio.actualizar(usuarioAcceso);
		}

	}

	@Override
	public void crear(Grupo grupo) {
		List<Menu> menus = menuRepositorio.obtenerTodos();
		GrupoMenu grupoMenu;
		grupoRepositorio.crear(grupo);
		for (Menu menu : menus) {
			grupoMenu = new GrupoMenu();
			grupoMenu.setAcceso(false);
			grupoMenu.setNuevo(false);
			grupoMenu.setModificar(false);
			grupoMenu.setConsultar(false);
			grupoMenu.setEliminar(false);
			grupoMenu.setImprimir(false);
			grupoMenu.setGrupo(grupo);
			grupoMenu.setMenu(menu);
			grupoMenuRepositorio.crear(grupoMenu);
		}
	}

	@Override
	public boolean validarDuplicado(Grupo grupo) {
		Criterio filtro = Criterio.forClass(Grupo.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("descripcion", grupo.getDescripcion()));
		if (grupo.getId() != null) {
			filtro.add(Restrictions.not(Restrictions.eq("id", grupo.getId())));
		}
		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

}