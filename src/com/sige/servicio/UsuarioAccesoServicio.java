package com.sige.servicio;

import java.util.List;

import com.sige.entidad.UsuarioAcceso;

public interface UsuarioAccesoServicio extends
		BaseServicio<UsuarioAcceso, Long> {

	public List<UsuarioAcceso> buscarPorCodUsuario(Long codigoUsuario,
			Boolean todos);

	public Long getCodigoGrupo(Long codigoUsuarioAcceso);

	public List<UsuarioAcceso> buscarPorCodGrupo(Long idGrupo);
}
