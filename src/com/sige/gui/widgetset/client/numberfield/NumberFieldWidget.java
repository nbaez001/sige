package com.sige.gui.widgetset.client.numberfield;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.vaadin.client.ui.VTextField;

// TODO extend any GWT Widget
public class NumberFieldWidget extends VTextField {

	public static final String CLASSNAME = "numberfield";

	public NumberFieldWidget() {

		// setText("NumberField sets the text via NumberFieldConnector using NumberFieldState");
	
		super();
		setStyleName(CLASSNAME);
		addKeyPressHandler(keyPressHandler);
		addKeyDownHandler(keyDownHandler);
		addFocusHandler(focusHandler);
		addBlurHandler(blurHandler);
	}
	private KeyPressHandler keyPressHandler = new KeyPressHandler() {
		@Override
		public void onKeyPress(KeyPressEvent e) {
			if(e.getCharCode() >= 31 && ( e.getCharCode()< 48 ||e.getCharCode() > 57 )){
				e.preventDefault();
			}
		}
	};
	
	private KeyDownHandler keyDownHandler = new KeyDownHandler() {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
				
			} else if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
				
			} else if (event.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
				
			} else if (event.getNativeKeyCode() == KeyCodes.KEY_LEFT) {
				
			} else if (event.getNativeKeyCode() == KeyCodes.KEY_HOME || event.getNativeKeyCode() == KeyCodes.KEY_UP) {
				
			} else if (event.getNativeKeyCode() == KeyCodes.KEY_END || event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
				
			} else if(event.getNativeKeyCode() == KeyCodes.KEY_SHIFT){
				event.preventDefault();
			}
		}
	};

	
	private FocusHandler focusHandler = new FocusHandler() {
		@Override
		public void onFocus(FocusEvent event) {
			
		}
	};
	
	private BlurHandler blurHandler = new BlurHandler() {
		@Override
		public void onBlur(BlurEvent event) {
		
				}
	};
	

}