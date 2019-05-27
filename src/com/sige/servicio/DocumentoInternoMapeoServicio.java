package com.sige.servicio;

import java.util.List;

import com.sige.entidad.DocumentoInternoMapeo;

public interface DocumentoInternoMapeoServicio extends
		BaseServicio<DocumentoInternoMapeo, Long> {

	public List<DocumentoInternoMapeo> buscarPorCodidoDoc(String codigo);

	public List<DocumentoInternoMapeo> getAllrecorrido(String codigo);

	public String obtenerUltimoMovimiento(String codigo);
}
