package com.sige.servicio;

import java.util.List;

import com.sige.entidad.RequisitoTupa;

public interface RequisitoTupaService extends BaseServicio<RequisitoTupa, Long> {

	public Long obtenerCantidadRequisitosPorTramite(String codTipoTramite);

	public List<RequisitoTupa> obtenerRequisitosPorTramite(String codTipoTramite);
}
