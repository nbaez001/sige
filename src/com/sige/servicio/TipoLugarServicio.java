package com.sige.servicio;

import java.util.List;

import com.sige.entidad.TipoLugar;
import com.sige.util.Busqueda;

public interface TipoLugarServicio extends BaseServicio<TipoLugar, Long> {

	public List<TipoLugar> buscarPorTipoLugar(TipoLugar tipolugar);

	public Busqueda buscarPorTipoLugar(TipoLugar tipoLugar, Long paginaActual);

	public void eliminarTipoLugar(TipoLugar tipoLugar);

	public String getTipoLugar(Long id);

	public Long getCantidadRegistros();

	public Boolean validarDuplicado(TipoLugar tipoLugar);

}