package com.sige.util;

import java.io.ByteArrayOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JRXmlExporterParameter;

@SuppressWarnings("deprecation")
final public class JasperExport {

	/**
	 * exporta un JasperPrint a formato XML en un array de bytes
	 * 
	 * @param jasperPrint
	 * @return byte[] array con los bytes del resultado de la conversion
	 */
	static public byte[] xmlReportToArray(JasperPrint jasperPrint) {
		byte[] bytes = null;
		try {
			JRXmlExporter jasperXmlExportMgr = new JRXmlExporter();

			ByteArrayOutputStream xmlReport = new ByteArrayOutputStream();
			jasperXmlExportMgr.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			jasperXmlExportMgr.setParameter(
					JRXmlExporterParameter.IS_EMBEDDING_IMAGES,
					java.lang.Boolean.TRUE);
			jasperXmlExportMgr.setParameter(JRExporterParameter.OUTPUT_STREAM,
					xmlReport);
			jasperXmlExportMgr.exportReport();
			bytes = xmlReport.toByteArray();
		} catch (JRException jex) {
			jex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * exporta un JasperPrint a formato CSV en un array de bytes
	 * 
	 * @param jasperPrint
	 * @return byte[] array con los bytes del resultado de la conversion
	 */
	static public byte[] csvReportToArray(JasperPrint jasperPrint) {
		byte[] bytes = null;
		try {
			JRCsvExporter jasperCsvExportMgr = new JRCsvExporter();

			ByteArrayOutputStream csvReport = new ByteArrayOutputStream();
			jasperCsvExportMgr.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			// jasperCsvExportMgr.setParameter(
			// JRCsvExporterParameter.FIELD_DELIMITER, "," );
			jasperCsvExportMgr.setParameter(JRExporterParameter.OUTPUT_STREAM,
					csvReport);
			jasperCsvExportMgr.exportReport();
			bytes = csvReport.toByteArray();
		} catch (JRException jex) {
			jex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * exporta un JasperPrint a formato HTML en un array de bytes
	 * 
	 * @param jasperPrint
	 * @return byte[] array con los bytes del resultado de la conversion
	 */
	static public byte[] htmlReportToArray(JasperPrint jasperPrint) {
		byte[] bytes = null;
		try {
			JRHtmlExporter jasperHtmlExportMgr = new JRHtmlExporter();

			ByteArrayOutputStream htmlReport = new ByteArrayOutputStream();
			jasperHtmlExportMgr.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			jasperHtmlExportMgr.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					java.lang.Boolean.FALSE);
			// jasperHtmlExportMgr.setParameter(
			// JRHtmlExporterParameter.IMAGES_DIR, ( String ) );
			jasperHtmlExportMgr.setParameter(JRExporterParameter.OUTPUT_STREAM,
					htmlReport);
			jasperHtmlExportMgr.exportReport();
			bytes = htmlReport.toByteArray();
		} catch (JRException jex) {
			jex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * exporta un JasperPrint a formato PDF en un array de bytes
	 * 
	 * @param jasperPrint
	 * @return byte[] array con los bytes del resultado de la conversion
	 */
	static public byte[] pdfReportToArray(JasperPrint jasperPrint) {
		byte[] bytes = null;
		try {
			JRPdfExporter jasperPdfExportMgr = new JRPdfExporter();

			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			jasperPdfExportMgr.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			// jasperHtmlExportMgr.setParameter( JRPdfExporterParameter., );
			jasperPdfExportMgr.setParameter(JRExporterParameter.OUTPUT_STREAM,
					pdfReport);
			jasperPdfExportMgr.exportReport();
			bytes = pdfReport.toByteArray();
		} catch (JRException jex) {
			jex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * exporta un JasperPrint a formato XLS en un array de bytes
	 * 
	 * @param jasperPrint
	 * @return byte[] array con los bytes del resultado de la conversion
	 */
	static public byte[] xlsReportToArray(JasperPrint jasperPrint) {
		byte[] bytes = null;
		try {
			JRXlsExporter jasperXlsExportMgr = new JRXlsExporter();

			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			jasperXlsExportMgr.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			jasperXlsExportMgr.setParameter(
					JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			jasperXlsExportMgr.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			jasperXlsExportMgr.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					java.lang.Boolean.TRUE);
			jasperXlsExportMgr.setParameter(JRExporterParameter.OUTPUT_STREAM,
					xlsReport);
			jasperXlsExportMgr.exportReport();
			bytes = xlsReport.toByteArray();
		} catch (JRException jex) {
			// throw new Exception ("Unable to create the XLS file" + jex);
			jex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * exporta un JasperPrint a formato RTF en un array de bytes
	 * 
	 * @param jasperPrint
	 * @return byte[] array con los bytes del resultado de la conversion
	 */
	static public byte[] rtfReportToArray(JasperPrint jasperPrint) {
		byte[] bytes = null;
		try {
			JRRtfExporter jasperRtfExportMgr = new JRRtfExporter();

			ByteArrayOutputStream rtfReport = new ByteArrayOutputStream();
			jasperRtfExportMgr.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			// jasperHtmlExportMgr.setParameter( JRPdfExporterParameter., );
			jasperRtfExportMgr.setParameter(JRExporterParameter.OUTPUT_STREAM,
					rtfReport);
			jasperRtfExportMgr.exportReport();
			bytes = rtfReport.toByteArray();
		} catch (JRException jex) {
			jex.printStackTrace();
		}
		return bytes;
	}

}