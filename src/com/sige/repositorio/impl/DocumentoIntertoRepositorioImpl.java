package com.sige.repositorio.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sige.entidad.DocumentoInterno;
import com.sige.repositorio.DocumentoInternoRepositorio;
import com.sige.util.Criterio;

@Repository
public class DocumentoIntertoRepositorioImpl extends
		BaseRepositorioImpl<DocumentoInterno, Long> implements
		DocumentoInternoRepositorio {

}
