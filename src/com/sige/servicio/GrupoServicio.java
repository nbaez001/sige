package com.sige.servicio;

import com.sige.entidad.Grupo;
import com.sige.util.Busqueda;

public interface GrupoServicio extends BaseServicio<Grupo, Long> {

	public Busqueda buscarPorGrupo(Grupo grupo, Long paginaActual);

	public void eliminiarGrupo(Grupo grupo);

	public boolean validarDuplicado(Grupo grupo);

}