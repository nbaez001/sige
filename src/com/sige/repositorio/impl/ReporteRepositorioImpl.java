package com.sige.repositorio.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sige.repositorio.ReporteRepositorio;
import com.sige.util.Cargo;
import com.sige.util.ConnectionUtil;
import com.sige.util.ExpedientePDF;

@Repository
public class ReporteRepositorioImpl implements ReporteRepositorio {

	//@Autowired
	//private DataSource dataSource;
	private Logger logger = Logger.getLogger(this.getClass());

	public JasperPrint generarReporte(String reportName,
			Map<String, Object> parameters, List<ExpedientePDF> expedientes,
			List<Cargo> cargos) {
		logger.info("ReporteRepositorioImpl.generarReporte Start");
		logger.info("reportName=" + reportName + ",parameters=" + parameters);
		JasperPrint response = null;
		JRBeanCollectionDataSource collectionDataSource = null;
		JasperReport jasperReport = null;
		try {
			jasperReport = (JasperReport) JRLoader
					.loadObject(this.getClass().getResource(
							"/com/sige/reportes/" + reportName + ".jasper"));
			if (cargos == null && expedientes != null) {
				collectionDataSource = new JRBeanCollectionDataSource(
						expedientes);
				response = JasperFillManager.fillReport(jasperReport,
						parameters, collectionDataSource);
			} else if (cargos != null && expedientes == null) {
				collectionDataSource = new JRBeanCollectionDataSource(cargos);
				response = JasperFillManager.fillReport(jasperReport,
						parameters, collectionDataSource);
			} else {
				Connection connection = new ConnectionUtil().getConexion();
				response = JasperFillManager.fillReport(jasperReport,
						parameters, connection);
				connection.close();
			}
			logger.info("response=" + response);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		logger.info("::: ReporteRepositorioImpl.generarReporte End :::");
		return response;
	}

}