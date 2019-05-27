package com.sige.gui.widgetset.client.numberfield;



import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sige.gui.ui.NumberField;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(NumberField.class)
public class NumberFieldConnector extends TextFieldConnector {

	
	public NumberFieldConnector() {

	}

	@Override
	protected Widget createWidget() {
		return GWT.create(NumberFieldWidget.class);
	}

	@Override
	public NumberFieldWidget getWidget() {
		return (NumberFieldWidget) super.getWidget();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// TODO do something useful
	}

}

