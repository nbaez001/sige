package com.sige.gui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.sige.servicio.ReporteServicio;
import com.sige.util.Injector;
import com.sige.util.Permiso;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;

public class TiempoPromedioAtencionOficinaPorTramite extends CustomComponent
		implements ClickListener {

	/*- VaadinEditorProperties={"grid":"RegularGrid,10","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Embedded embReporte;
	@AutoGenerated
	private Button btnVisualizar;
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */

	private ReporteServicio reporteServicio;
	private Window quienLlama;

	public TiempoPromedioAtencionOficinaPorTramite(Window quienLlama,
			Permiso permiso) {
		this.quienLlama = quienLlama;
		this.reporteServicio = Injector.obtenerServicio(ReporteServicio.class);
		buildMainLayout();
		setCompositionRoot(mainLayout);
		postBuid();
	}

	private void postBuid() {
		this.btnVisualizar.addClickListener((ClickListener) this);
		this.embReporte.setVisible(false);
	}

	private void visualizarReporte() {
		this.embReporte.setVisible(true);
		StreamResource resource = new StreamResource(
				new StreamResource.StreamSource() {
					private static final long serialVersionUID = 1L;

					public InputStream getStream() {
						byte[] DocContent = null;
						Map<String, Object> parametros = new HashMap<String, Object>();
						try {
							DocContent = reporteServicio.obtenerReporte(
									"TiempoPromedioAtencionOficinaPorTramite",
									"PDF", parametros, null, null);
							return new ByteArrayInputStream(DocContent);
						} catch (Exception e1) {
							return null;
						}
					}
				}, "TiempoPromedioAtencionOficinaPorTramite.pdf");
		this.embReporte.setSource(resource);
		resource.setMIMEType("application/pdf");
		resource.getStream()
				.setParameter("Content-Disposition",
						"attachment; filename=TiempoPromedioAtencionOficinaPorTramite.pdf");
		this.embReporte.markAsDirty();
		this.embReporte.setType(Embedded.TYPE_BROWSER);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getSource() == this.btnVisualizar) {
			visualizarReporte();
		}
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

		// btnVisualizar
		btnVisualizar = new Button();
		btnVisualizar.setCaption("Visualizar");
		btnVisualizar.setImmediate(true);
		btnVisualizar.setWidth("111px");
		btnVisualizar.setHeight("-1px");
		mainLayout.addComponent(btnVisualizar, "top:8.0px;left:562.0px;");

		// embReporte
		embReporte = new Embedded();
		embReporte.setImmediate(false);
		embReporte.setWidth("660px");
		embReporte.setHeight("400px");
		embReporte.setSource(new ThemeResource(
				"img/component/embedded_icon.png"));
		embReporte.setType(1);
		embReporte.setMimeType("application/pdf");
		mainLayout.addComponent(embReporte, "top:40.0px;left:20.0px;");

		return mainLayout;
	}

}