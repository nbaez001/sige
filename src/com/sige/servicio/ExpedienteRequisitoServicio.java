package com.sige.servicio;

import java.util.List;

import com.sige.entidad.ExpedienteRequisito;

public interface ExpedienteRequisitoServicio extends
		BaseServicio<ExpedienteRequisito, Long> {
	public Integer obtenerUltimoCorrelativo();

	public Long obtenerCantidadRequisitosPresentados(String codExpediente);

	public Long obtenerCantidadRequisitosNoPresentados(String codExpediente);

	public List<ExpedienteRequisito> obtenerExpedienteRequisito(
			String codExpediente);
}
