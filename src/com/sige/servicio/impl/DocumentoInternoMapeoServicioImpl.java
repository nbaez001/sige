package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.DocumentoInternoMapeo;
import com.sige.repositorio.DocumentoInternoMapeoRepositorio;
import com.sige.servicio.DocumentoInternoMapeoServicio;
import com.sige.util.Criterio;

@Service
public class DocumentoInternoMapeoServicioImpl extends
		BaseServicioImpl<DocumentoInternoMapeo, Long> implements
		DocumentoInternoMapeoServicio {

	@Autowired
	protected DocumentoInternoMapeoServicioImpl(
			DocumentoInternoMapeoRepositorio documentoInternoMapeoRepositorio) {
		super(documentoInternoMapeoRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DocumentoInternoMapeo> buscarPorCodidoDoc(String codigo) {
		Criterio filtro = Criterio.forClass(DocumentoInternoMapeo.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("codigoDocumento", codigo));
		List<DocumentoInternoMapeo> documentosInternoMapeos = buscarPorCriteria(filtro);
		for (int i = 0; i < documentosInternoMapeos.size(); i++) {
			if (!documentosInternoMapeos
					.get(i)
					.getCodigoDocumento()
					.equals(documentosInternoMapeos.get(i)
							.getCodigoDocumentoPadre())) {
				documentosInternoMapeos.remove(i);
				--i;
			}
		}
		return documentosInternoMapeos;
	}

	@Override
	public List<DocumentoInternoMapeo> getAllrecorrido(String codigo) {
		Criterio filtro = Criterio.forClass(DocumentoInternoMapeo.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("codigoDocumento", codigo));
		List<DocumentoInternoMapeo> documentosInternoMapeos = buscarPorCriteria(filtro);
		return documentosInternoMapeos;
	}

	@Override
	public String obtenerUltimoMovimiento(String codigo) {
		Criterio filtro = Criterio.forClass(DocumentoInternoMapeo.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("codigoDocumentoPadre", codigo));
		filtro.addOrder(Order.desc("codigoDocumento"));
		filtro.setMaxResults(1);
		return buscarPorCriteria(filtro).get(0).getCodigoDocumento();

	}

}
