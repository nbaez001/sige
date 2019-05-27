package com.sige.util;

public class TipoTramiteCanonico {
	private String Tupa;
	private String anio;
	private String mensaje;

	public TipoTramiteCanonico() {
	}

	public TipoTramiteCanonico(String tupa, String anio, String mensaje) {
		Tupa = tupa;
		this.anio = anio;
		this.mensaje = mensaje;
	}

	public String getTupa() {
		return Tupa;
	}

	public void setTupa(String tupa) {
		Tupa = tupa;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
