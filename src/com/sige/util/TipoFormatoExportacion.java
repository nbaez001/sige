package com.sige.util;

public enum TipoFormatoExportacion {

	PDF("PDF"), EXCEL("XLS"), WORD("RTF");

	private String value;

	private TipoFormatoExportacion(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}