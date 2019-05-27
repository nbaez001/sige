package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.PersonaDocumento;
import com.sige.repositorio.PersonaDocumentoRepositorio;

@Repository
public class PersonaDocumentoRepositorioImpl extends
		BaseRepositorioImpl<PersonaDocumento, Long> implements
		PersonaDocumentoRepositorio {

}
