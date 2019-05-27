package com.sige.servicio;

import java.util.List;

import com.sige.entidad.GrupoMenu;

public interface GrupoMenuServicio extends BaseServicio<GrupoMenu, Long> {

	public List<GrupoMenu> buscarPorCodGrupo(Long codigoGrupo, Boolean todos);

	public void actualizarTodos(List<GrupoMenu> grupoMenusActualizar);

}
