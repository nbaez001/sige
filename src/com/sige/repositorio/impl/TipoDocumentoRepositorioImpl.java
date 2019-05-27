package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.TipoDocumento;
import com.sige.repositorio.TipoDocumentoRepositorio;

@Repository
public class TipoDocumentoRepositorioImpl extends
		BaseRepositorioImpl<TipoDocumento, Long> implements
		TipoDocumentoRepositorio {

}
