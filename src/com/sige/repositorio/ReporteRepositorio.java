package com.sige.repositorio;

import java.util.List;
import java.util.Map;

import com.sige.util.Cargo;
import com.sige.util.ExpedientePDF;

import net.sf.jasperreports.engine.JasperPrint;

public interface ReporteRepositorio {

	public JasperPrint generarReporte(String reportName,
			Map<String, Object> parameters, List<ExpedientePDF> expedientes,
			List<Cargo> cargos);

}