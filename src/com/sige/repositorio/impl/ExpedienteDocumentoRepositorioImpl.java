package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.ExpedienteDocumento;
import com.sige.repositorio.ExpedienteDocumentoRepositorio;

@Repository
public class ExpedienteDocumentoRepositorioImpl extends
		BaseRepositorioImpl<ExpedienteDocumento, Long> implements
		ExpedienteDocumentoRepositorio {

}
