package com.sige.servicio;

import java.util.List;

import com.sige.entidad.DependenciaTipoTramite;
import com.sige.entidad.DependenciaTipoTramitePlantilla;
import com.sige.util.Busqueda;

public interface DependenciaTipoTramitePlantillaService extends
		BaseServicio<DependenciaTipoTramitePlantilla, Long> {

	public List<DependenciaTipoTramitePlantilla> obtenerTodos(
			DependenciaTipoTramite dependenciaTipoTramite);

	public Boolean validarDuplicado(
			DependenciaTipoTramite dependenciaTipoTramite,
			String nombrePlantilla);

	public void eliminarDependenciaTipoTramitePlantilla(
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla);

	public Busqueda buscarDependenciaTipoTramitePlantilla(
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla,
			Long paginaActual);

	public Boolean crearTodos(
			List<DependenciaTipoTramitePlantilla> dependenciaTipoTramitePlantillas);

	public Boolean update(
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla);

}
