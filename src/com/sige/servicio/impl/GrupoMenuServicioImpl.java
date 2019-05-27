package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.GrupoMenu;
import com.sige.repositorio.GrupoMenuRepositorio;
import com.sige.servicio.GrupoMenuServicio;
import com.sige.util.Criterio;

@Service
public class GrupoMenuServicioImpl extends BaseServicioImpl<GrupoMenu, Long>
		implements GrupoMenuServicio {

	@Autowired
	public GrupoMenuServicioImpl(GrupoMenuRepositorio grupoMenuRepositorio) {
		super(grupoMenuRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<GrupoMenu> buscarPorCodGrupo(Long codigoGrupo, Boolean todos) {
		Criterio filtro = Criterio.forClass(GrupoMenu.class);
		if (!todos) {
			filtro.add(Restrictions.eq("acceso", Boolean.TRUE));
		}
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("grupo.id", codigoGrupo));
		List<GrupoMenu> grupoMenu = buscarPorCriteria(filtro);
		return grupoMenu;

	}

	@Override
	public void actualizarTodos(List<GrupoMenu> grupoMenusActualizar) {
		for (GrupoMenu grupoMenu : grupoMenusActualizar) {
			super.actualizar(grupoMenu);
		}

	}

}
