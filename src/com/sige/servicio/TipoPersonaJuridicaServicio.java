package com.sige.servicio;

import java.util.List;

import com.sige.entidad.TipoPersonaJuridica;
import com.sige.util.Busqueda;

public interface TipoPersonaJuridicaServicio extends
		BaseServicio<TipoPersonaJuridica, Long> {

	public Long obtenerIdPersonaJuridica(Character codTipoPersona);

	public List<TipoPersonaJuridica> buscarPorTipoPersonaJuridica(
			TipoPersonaJuridica tipoPersonaJuridica);

	public Busqueda buscarPorTipoPersonaJuridica(
			TipoPersonaJuridica tipePersonaJuridica, Long paginaActual);

	public Boolean validarDuplicado(TipoPersonaJuridica tipoPersonaJuridica);
}
