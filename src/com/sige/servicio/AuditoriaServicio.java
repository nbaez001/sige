package com.sige.servicio;

import java.util.Date;

import com.sige.entidad.Auditoria;
import com.sige.util.Busqueda;

public interface AuditoriaServicio extends BaseServicio<Auditoria, Integer> {
	public Busqueda consultar(Date desde, Date hasta, String nombreTabla,
			String codigo, Long paginaActual, Boolean todos);
}
