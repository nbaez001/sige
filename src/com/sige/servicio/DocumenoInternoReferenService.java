package com.sige.servicio;

import java.util.List;

import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoReferen;

public interface DocumenoInternoReferenService extends
		BaseServicio<DocumentoInternoReferen, Long> {

	public List<DocumentoInterno> obtenerReferecias(DocumentoInterno docInterno);

}
