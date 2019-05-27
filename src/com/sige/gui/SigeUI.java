package com.sige.gui;

import javax.servlet.annotation.WebServlet;

import com.sige.util.Injector;
import com.sige.util.SigeUtil;
import com.sige.util.SpringContextConfigur;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ConnectorTracker;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("sige")
public class SigeUI extends UI {

	private ConnectorTracker tracker;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = true, ui = SigeUI.class, closeIdleSessions = true, widgetset = "com.sige.gui.widgetset.SigeWidgetset")
	public static class Servlet extends VaadinServlet {
		
	}

	@Override
	protected void init(VaadinRequest request) {
		SpringContextConfigur scc= new SpringContextConfigur();
		String usuario = SigeUtil.obtenerUsuarioSesion();
		if (usuario == null) {
			PanelLogin panelLogin = new PanelLogin();
			setContent(panelLogin);
		} else {
			cargarPanelPrincipal();
		}
		this.getPage().setTitle("Sistema Integrado de Gestion de Expedientes");
	}

	@Override
	public ConnectorTracker getConnectorTracker() {
		if (this.tracker == null) {
			this.tracker = new ConnectorTracker(this) {

				@Override
				public void registerConnector(ClientConnector connector) {
					try {
						super.registerConnector(connector);
					} catch (RuntimeException e) {

					}
				}

			};
		}
		return tracker;
	}

	public void cargarPanelPrincipal() {
		PanelPrincipal panelPrincipal = new PanelPrincipal();
		panelPrincipal.armarMenu();
		panelPrincipal.setWidth("100%");
		panelPrincipal.setHeight("100%");
		setContent(panelPrincipal);
	}
}