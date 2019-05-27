package com.sige.util;

public class TextField extends com.vaadin.ui.TextField {

	@Override
	public String getValue() {
		return super.getValue().trim().toUpperCase();
	}
}
