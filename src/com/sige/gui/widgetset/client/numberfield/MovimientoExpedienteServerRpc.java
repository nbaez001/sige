package com.sige.gui.widgetset.client.numberfield;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

public interface MovimientoExpedienteServerRpc extends ServerRpc {

	// TODO example API
	public void clicked(MouseEventDetails mouseDetails,String codigo);

}
