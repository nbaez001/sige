package com.sige.util;

import java.util.List;

public class Busqueda {

	private Long numeroPaginas;
	private Long paginaActual;
	private List<?> registos;

	public Long getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Long numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Long getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(Long paginaActual) {
		this.paginaActual = paginaActual;
	}

	public List<?> getRegistos() {
		return registos;
	}

	public void setRegistos(List<?> registos) {
		this.registos = registos;
	}

	@Override
	public String toString() {
		return "Busqueda [numeroPaginas=" + numeroPaginas + ", paginaActual="
				+ paginaActual + ", registos=" + registos.toString() + "]";
	}
}