package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoReferen;
import com.sige.repositorio.DocumentoInternoReferenRepositorio;
import com.sige.servicio.DocumenoInternoReferenService;
import com.sige.servicio.DocumentoInternoService;
import com.sige.util.Criterio;

@Service
public class DocumentoInternoReferenServiceImpl extends
		BaseServicioImpl<DocumentoInternoReferen, Long> implements
		DocumenoInternoReferenService {

	@Autowired
	private DocumentoInternoReferenRepositorio docInternoReferenRepositorio;

	@Autowired
	private DocumentoInternoService docInternoService;

	@Autowired
	public DocumentoInternoReferenServiceImpl(
			DocumentoInternoReferenRepositorio docInternoReferenRepositorio) {
		super(docInternoReferenRepositorio);
		this.docInternoReferenRepositorio = docInternoReferenRepositorio;

	}

	@Override
	public List<DocumentoInterno> obtenerReferecias(DocumentoInterno docInterno) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(DocumentoInternoReferen.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("codigoDocumento",
						docInterno.getCodigodocumento()));
		List<DocumentoInternoReferen> referencias = buscarPorCriteria(filtro);
		DocumentoInterno documentoInterno = null;
		List<DocumentoInterno> documentoReferencias = new ArrayList<DocumentoInterno>();
		if (referencias.size() > 0) {
			for (DocumentoInternoReferen referencia : referencias) {
				documentoInterno = new DocumentoInterno();
				documentoInterno = docInternoService
						.obtenerDocumentoInternoPorCodigo(referencia
								.getCodigoDocumentoReferencia());
				documentoReferencias.add(documentoInterno);
				//
			}
		}
		return documentoReferencias;
	}
}
