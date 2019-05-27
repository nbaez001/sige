package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Dependencia;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteEscaneado;

public interface ExpedienteEscaneadoServicio extends
		BaseServicio<ExpedienteEscaneado, Long> {

	public Boolean validarDuplicado(String descripcion);

	public List<ExpedienteEscaneado> obtenerExpedientesEscaneados(
			Dependencia dependencia, Expediente expediente);

	public Long obtenerCorrelativo(Dependencia dependencia,
			Expediente expediente);

	public void eliminar(ExpedienteEscaneado expedienteEscaneado);

}
