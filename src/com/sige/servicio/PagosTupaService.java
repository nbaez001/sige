package com.sige.servicio;

import java.util.List;

import com.sige.entidad.PagosTupa;

public interface PagosTupaService extends BaseServicio<PagosTupa, Long> {
	public Long obtenerCantidadPagosPorTramite(String codTramite);

	public List<PagosTupa> obtenerPagosPorTramite(String codTramite);
}
