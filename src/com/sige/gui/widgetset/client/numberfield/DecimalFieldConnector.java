package com.sige.gui.widgetset.client.numberfield;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sige.gui.ui.DecimalField;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(DecimalField.class)
public class DecimalFieldConnector extends TextFieldConnector {

	
	public DecimalFieldConnector() {


	}

	@Override
	protected Widget createWidget() {
		return GWT.create(DecimalFieldWidget.class);
	}

	@Override
	public DecimalFieldWidget getWidget() {
		return (DecimalFieldWidget) super.getWidget();
	}


	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		
	}

}

