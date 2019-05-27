package com.sige.servicio;

import java.util.List;
import java.util.Map;

import com.sige.util.Cargo;
import com.sige.util.ExpedientePDF;

public interface ReporteServicio {

	public byte[] obtenerReporte(String reportName, String formatType,
			Map<String, Object> parameters, List<ExpedientePDF> expedientes,
			List<Cargo> cargos);

}