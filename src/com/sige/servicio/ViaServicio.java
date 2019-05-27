package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Via;
import com.sige.util.Busqueda;

public interface ViaServicio extends BaseServicio<Via, Long> {
	public List<Via> buscarPorVia(Via via);

	public Busqueda buscarPorVia(Via via, Long pagActual);

	public boolean validarDuplicidad(Via via);
}
