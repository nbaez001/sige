package com.sige.servicio;

import java.util.List;

import com.sige.entidad.TipoVia;
import com.sige.util.Busqueda;

public interface TipoViaServicio extends BaseServicio<TipoVia, Long> {
	public Busqueda BuscarPorTipoVia(TipoVia tipoVia, Long pagActual);

	public List<TipoVia> buscarPorTipoVia(TipoVia tipoVia);

	public Boolean validarDuplicado(TipoVia tipoVia);
}
