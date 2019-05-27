package com.sige.servicio;

import com.sige.entidad.TipoDocumentoPersona;
import com.sige.util.Busqueda;

public interface TipoDocumentoPersonaServicio extends
		BaseServicio<TipoDocumentoPersona, Long> {
	public Busqueda BuscarPorTipoDocumentoPersona(
			TipoDocumentoPersona tipoDocumentoPersona, Long pagActual);

	public Boolean validarDuplicado(TipoDocumentoPersona tipoDocumentoPersona);
}
