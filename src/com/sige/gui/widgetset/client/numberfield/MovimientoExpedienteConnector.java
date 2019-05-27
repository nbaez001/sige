package com.sige.gui.widgetset.client.numberfield;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.sige.gui.ui.MovimientoExpedienteGrafico;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;
@Connect(MovimientoExpedienteGrafico.class)
public class MovimientoExpedienteConnector extends AbstractComponentConnector {

	MovimientoExpedienteServerRpc rpc = RpcProxy
			.create(MovimientoExpedienteServerRpc.class, this);
	
	public MovimientoExpedienteConnector() {
	

		// TODO ServerRpc usage example, do something useful instead
	getWidget().canvas.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
					.buildMouseEventDetails(event.getNativeEvent(),
								getWidget().canvas.getElement());
				rpc.clicked(mouseDetails,getWidget().getCodigo());
				getWidget().setCodigo("");
			}
		});
	

	}

	@Override
	protected Widget createWidget() {
		return GWT.create(MovimientoExpedienteWidget.class);
	}

	@Override
	public MovimientoExpedienteWidget getWidget() {
		return (MovimientoExpedienteWidget) super.getWidget();
	}

	@Override
	public MovimientoExpedienteState getState() {
		return (MovimientoExpedienteState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		// TODO do something useful
	if(getState().graficar){
		if(getWidget().dibujar){
		getWidget().idTipoDocumento=getState().idTipoDocumento;
		getWidget().idDependencias=getState().idDependencias;
		getWidget().codigoDocumento=getState().codigoDocumento;
		getWidget().codigoDocumentoReferencia=getState().codigoDocumentoReferencia;
		
		getWidget().codigoDocumentoPadre=getState().codigoDocumentoPadre;
		getWidget().nombreDependenciasTotal=getState().nombreDependenciasTotal;
		
		getWidget().idDependenciasTotal=getState().idDependenciasTotal;
		getWidget().idTipoDocumentos=getState().idTipoDocumentos;
		getWidget().nombreTipoDocumentos=getState().nombreTipoDocumentos;
		getWidget().nivel=getState().nivel;
		getWidget().idMesaPartes=getState().idMesaPartes;
		
		getWidget().construirGrafico();
		getWidget().dibujar=false;
		}
	}
	}

}

