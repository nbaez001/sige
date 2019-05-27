package com.sige.gui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sige.servicio.ReporteServicio;
import com.sige.util.Cargo;
import com.sige.util.ExpedientePDF;
import com.sige.util.Injector;
import com.sige.util.Permiso;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;

public class PDFVizualizador extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Embedded vizualizadorReporte;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	private ReporteServicio reporteServicio;
	private CustomComponent quienLlama;
	private Permiso permiso;
	private String reportName;
	private List<ExpedientePDF> expedientes;
	private Map<String, Object> parametros;
	private List<Cargo> cargos;

	public PDFVizualizador(CustomComponent quienLlama, Permiso permiso,
			Map<String, Object> parameters, List<ExpedientePDF> expedientes,
			String reportName, List<Cargo> cargos) {
		this.quienLlama = quienLlama;
		this.permiso = permiso;
		reporteServicio = Injector.obtenerServicio(ReporteServicio.class);
		this.reportName = reportName;
		this.expedientes = expedientes;
		this.cargos = cargos;
		buildMainLayout();
		this.parametros = parameters;
		setCompositionRoot(mainLayout);
		postBuild();
		// TODO add user code here
	}

	private void postBuild() {
		visualizarReporte();
	}

	public void visualizarReporte() {
		StreamResource resource = new StreamResource(
				new StreamResource.StreamSource() {
					private static final long serialVersionUID = 1L;

					public InputStream getStream() {
						byte[] DocContent = null;
						try {
							DocContent = reporteServicio.obtenerReporte(
									reportName, "PDF", parametros, expedientes,
									cargos);
							return new ByteArrayInputStream(DocContent);
						} catch (Exception e1) {
							return null;
						}
					}
				}, reportName + ".pdf");
		vizualizadorReporte.setSource(resource);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition",
				"attachment; filename=" + reportName + ".pdf");
		vizualizadorReporte.markAsDirty();// for force
		vizualizadorReporte.setType(Embedded.TYPE_BROWSER);
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// vizualizadorReporte
		vizualizadorReporte = new Embedded();
		vizualizadorReporte.setImmediate(false);
		vizualizadorReporte.setWidth("780px");
		vizualizadorReporte.setHeight("487px");
		vizualizadorReporte.setSource(new ThemeResource(
				"img/component/embedded_icon.png"));
		vizualizadorReporte.setType(1);
		vizualizadorReporte.setMimeType("image/png");
		mainLayout.addComponent(vizualizadorReporte, "top:13.0px;left:20.0px;");

		return mainLayout;
	}

}