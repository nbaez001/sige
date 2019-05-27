package com.sige.servicio;

import com.sige.entidad.Dependencia;
import com.sige.util.Busqueda;

public interface DependenciaServicio extends BaseServicio<Dependencia, Long> {

	public Busqueda buscarPorDependencia(Dependencia dependencia,
			Long paginaActual);

	public void eliminiarDependencia(Dependencia dependencia);

	public Dependencia ObtenerDependenciaPorCodigo(Integer id);

	public Boolean validarDuplicado(Dependencia dependencia);

	public Long getCodigoMesaPartes();

}