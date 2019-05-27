package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Dependencia;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteDocumento;
import com.sige.entidad.TipoTramite;

public interface ExpedienteDocumentoService extends
		BaseServicio<ExpedienteDocumento, Long> {

	public List<ExpedienteDocumento> obtenerTodos(Dependencia dependencia,
			Expediente expediente);
}
