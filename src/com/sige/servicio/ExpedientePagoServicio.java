package com.sige.servicio;

import java.util.List;

import com.sige.entidad.ExpedientePago;

public interface ExpedientePagoServicio extends
		BaseServicio<ExpedientePago, Long> {
	public Long obtenerCantidadPagosPresentados(String codExpediente);

	public Long obtenerCantidadPagosNoPresentados(String codExpediente);

	public List<ExpedientePago> obtenerExpedientesPago(String codExpediente);
}
