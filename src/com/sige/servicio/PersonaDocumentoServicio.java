package com.sige.servicio;

import java.util.List;

import com.sige.entidad.PersonaDocumento;

public interface PersonaDocumentoServicio extends
		BaseServicio<PersonaDocumento, Long> {

	public List<PersonaDocumento> obtenerPorCodigoPersona(String codigoPersona);

}
