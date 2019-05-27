package com.sige.servicio;

import java.util.List;

import com.sige.entidad.UsuarioDependencia;

public interface UsuarioDependenciaServicio extends
		BaseServicio<UsuarioDependencia, Long> {

	public List<UsuarioDependencia> buscarPorCodigoUsuario(Long idUsuario,
			Boolean todos);

}
