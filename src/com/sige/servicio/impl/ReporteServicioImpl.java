package com.sige.servicio.impl;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.repositorio.ReporteRepositorio;
import com.sige.servicio.ReporteServicio;
import com.sige.util.Cargo;
import com.sige.util.ExpedientePDF;
import com.sige.util.JasperExport;
import com.sige.util.TipoFormatoExportacion;

@Service
public class ReporteServicioImpl implements ReporteServicio {

	@Autowired
	private ReporteRepositorio reporteRepositorio;

	public byte[] obtenerReporte(String reportName, String formatType,
			Map<String, Object> parameters, List<ExpedientePDF> expedientes,
			List<Cargo> cargos) {
		byte[] response = null;
		JasperPrint jasperPrint = reporteRepositorio.generarReporte(reportName,
				parameters, expedientes, cargos);
		if (TipoFormatoExportacion.PDF.getValue().equals(formatType)) {
			response = JasperExport.pdfReportToArray(jasperPrint);
		} else if (TipoFormatoExportacion.EXCEL.getValue().equals(formatType)) {
			response = JasperExport.xlsReportToArray(jasperPrint);
		} else if (TipoFormatoExportacion.WORD.getValue().equals(formatType)) {
			response = JasperExport.rtfReportToArray(jasperPrint);
		}
		return response;
	}

}