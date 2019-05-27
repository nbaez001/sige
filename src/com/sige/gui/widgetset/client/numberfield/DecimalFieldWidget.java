package com.sige.gui.widgetset.client.numberfield;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.vaadin.client.ui.VTextField;

// TODO extend any GWT Widget
public class DecimalFieldWidget extends VTextField {

	public static final String CLASSNAME = "decimalfield";

	public DecimalFieldWidget() {
		super();
		addKeyPressHandler(keyPressHandler);
		// setText("DecimalField sets the text via DecimalFieldConnector using DecimalFieldState");
		setStyleName(CLASSNAME);

	}
	private KeyPressHandler keyPressHandler = new KeyPressHandler() {
		@Override
		public void onKeyPress(KeyPressEvent e) {
			if(e.getCharCode()==46){
		if(getValue().toString().indexOf(".")!=-1){
					e.preventDefault();
				}else{
					return;
				}
		}
			
			if(e.getCharCode() >= 31 && ( e.getCharCode()< 48 ||e.getCharCode() > 57 )){
				e.preventDefault();
			}
		}
	};
}