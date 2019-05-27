package com.sige.servicio.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.repositorio.DocumentoInternoDestinoRepositorio;
import com.sige.servicio.DocumentoInternoDestinoService;
import com.sige.servicio.ExpedienteMovimientoServicio;
import com.sige.util.Busqueda;
import com.sige.util.Criterio;
import com.sige.util.ExpedienteCanonico;

@Service
public class DocumentoInternoDestinoServiceImpl extends
		BaseServicioImpl<DocumentoInternoDestino, Long> implements
		DocumentoInternoDestinoService {

	@Autowired
	private DocumentoInternoDestinoRepositorio documentoInternoDestinoRepositorio;

	@Autowired
	private ExpedienteMovimientoServicio expedienteMovimientoServicio;

	@Autowired
	public DocumentoInternoDestinoServiceImpl(
			DocumentoInternoDestinoRepositorio documentoInternoDestinoRepositorio) {
		super(documentoInternoDestinoRepositorio);
		this.documentoInternoDestinoRepositorio = documentoInternoDestinoRepositorio;
	}

	@Override
	public Long obtenerUltimoCorrelativo() {
		// TODO Auto-generated method stub
		Calendar calendario = Calendar.getInstance();
		Criterio filtro = Criterio.forClass(DocumentoInternoDestino.class);
		filtro.createCriteria("documentoInterno")
				.add(Restrictions.eq("añoDocumento",
						calendario.get(Calendar.YEAR) + ""))
				.addOrder(Order.desc("codigodocumento"));
		List<DocumentoInternoDestino> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return Long.parseLong(lista.get(0).getDocumentoInterno()
					.getCodigodocumento()) + 1L;
		}
		return 1L;
	}

	@Override
	public DocumentoInternoDestino obtenerDocInternoDestinoPorDocInterno(
			String codDocInterno) {
		Criterio filtro = Criterio.forClass(DocumentoInternoDestino.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.addOrder(Order.desc("id"));
		filtro.createCriteria("documentoInterno").add(
				Restrictions.eq("codigodocumento", codDocInterno));
		List<DocumentoInternoDestino> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Long getTotalPorRecepcionista(String usuario, Date desde, Date hasta) {
		Criterio filtro = Criterio.forClass(DocumentoInternoDestino.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("recepcionadoPor", usuario.trim()));
		filtro.createCriteria("documentoInterno")
				.createCriteria("expedienteMovimientos")
				.createCriteria("expediente")
				.add(Restrictions.between("fechaExpediente", desde, hasta));
		List<DocumentoInternoDestino> lista = buscarPorCriteria(filtro);
		filtro.setFetchMode("documentoInterno", FetchMode.EAGER);
		Long total = 0L;
		for (DocumentoInternoDestino doc : lista) {
			total += expedienteMovimientoServicio
					.getTotalExpedienteNoTupaPorCodigoDocumento(doc
							.getDocumentoInterno().getCodigodocumento());
		}
		return total;
	}

}
