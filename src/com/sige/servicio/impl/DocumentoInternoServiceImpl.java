package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteMovimiento;
import com.sige.repositorio.DocumentoInternoRepositorio;
import com.sige.repositorio.ExpedienteMovimientoRepositorio;
import com.sige.servicio.DocumentoInternoService;
import com.sige.servicio.ExpedienteServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;
import com.sige.util.ExpedienteCanonico;

@Service
public class DocumentoInternoServiceImpl extends
		BaseServicioImpl<DocumentoInterno, Long> implements
		DocumentoInternoService {

	@Autowired
	private DocumentoInternoRepositorio documentoInternoRepositorio;

	@Autowired
	private ExpedienteMovimientoRepositorio expedienteMovimentoRepositorio;

	@Autowired
	private ExpedienteServicio expedienteService;

	@Autowired
	public DocumentoInternoServiceImpl(
			DocumentoInternoRepositorio documentoInternoRepositorio) {
		super(documentoInternoRepositorio);
		this.documentoInternoRepositorio = documentoInternoRepositorio;
	}

	@Override
	public Long obtenerUltimoCorrelativo() {
		Calendar calendario = Calendar.getInstance();
		Criterio filtro = Criterio.forClass(DocumentoInterno.class);
		//OBTIENE DE UN AÑO DETERMINADO
		//filtro.add(Restrictions.eq("añoDocumento",calendario.get(Calendar.YEAR) + ""));
		filtro.addOrder(Order.desc("codigodocumento"));
		List<DocumentoInterno> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return Long.parseLong(lista.get(0).getCodigodocumento()) + 1L;
		}
		return 1L;
	}

	@Override
	public DocumentoInterno obtenerDocumentoInternoPorCodigo(String codDocumento) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(DocumentoInterno.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("codigodocumento", codDocumento));
		List<DocumentoInterno> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Busqueda buscarDocumentoInterno(DocumentoInterno docInterno,
			Long paginaActual, ExpedienteCanonico expedienteCanonico) {
		// TODO Auto-generated method stub
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(DocumentoInterno.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.ilike("codigodocumento",
						docInterno.getCodigodocumento(), MatchMode.ANYWHERE))
				.add(Restrictions.ilike("numerodocumento",
						docInterno.getNumerodocumento(), MatchMode.ANYWHERE))
				.add(Restrictions.ilike("añoDocumento",
						docInterno.getAñoDocumento(), MatchMode.ANYWHERE))
				// .add(Restrictions.eq("estaArchivado", Boolean.FALSE))
				.add(Restrictions.ilike("asunto", docInterno.getAsunto(),
						MatchMode.ANYWHERE))
				.createCriteria("dependencia")
				.add(Restrictions.ilike("nombre", docInterno.getDependencia()
						.getNombre(), MatchMode.ANYWHERE));
		Criterio docDestino = filtro.setFetchMode("documentosInternosDestinos",
				FetchMode.JOIN).createCriteria("documentosInternosDestinos");
		docDestino
				.add(Restrictions.not(Restrictions.eq("tipoMovimiento", '3')));
		if (expedienteCanonico != null) {
			docDestino.add(Restrictions.not(Restrictions.in("id",
					expedienteCanonico.getDocumentosRepetidos())));
			docDestino.createCriteria("dependencia").add(
					Restrictions.eq("id", expedienteCanonico
							.getDependenciaEntidad().getId()));

		}
		Long totalRegistros = documentoInternoRepositorio
				.cantidadPorCriteria(filtro);
		System.out.print("Total Registros" + totalRegistros);
		if (totalRegistros % Constantes.PAGINACION.DOCUMENTOINTERNO != 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.DOCUMENTOINTERNO + 1);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.DOCUMENTOINTERNO);
		}
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		filtro.setMaxResults(Constantes.PAGINACION.DOCUMENTOINTERNO.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.DOCUMENTOINTERNO.intValue());
		busqueda.setRegistos(documentoInternoRepositorio
				.buscarPorCriteria(filtro));
		return busqueda;
	}

	@Override
	public List<Expediente> obtenerExpedientesPorDocumentoInterno(
			String codDocumento) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.createCriteria("documentoInterno").add(
				Restrictions.eq("codigodocumento", codDocumento));
		List<ExpedienteMovimiento> expedientesMov = expedienteMovimentoRepositorio
				.buscarPorCriteria(filtro);
		List<String> codExpedientes = null;
		for (ExpedienteMovimiento em : expedientesMov) {
			codExpedientes = new ArrayList<String>();
			codExpedientes.add(em.getExpediente().getCodigo());
		}
		List<Expediente> expedientes = expedienteService
				.obtenerExpedientes(codExpedientes);
		return expedientes;

	}

	@Override
	public DocumentoInterno obtenerUltimoDocInternoPorExpediente(
			Expediente expediente) {
		DocumentoInterno docInterno = new DocumentoInterno();
		Long codDocInterno;
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).addOrder(
				Order.desc("correlativo"));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", expediente.getCodigo()));

		List<ExpedienteMovimiento> lsExpeidneteMovimiento = expedienteMovimentoRepositorio
				.buscarPorCriteria(filtro);
		if (lsExpeidneteMovimiento.size() > 0) {
			docInterno = obtenerDocumentoInternoPorCodigo(lsExpeidneteMovimiento
					.get(0).getDocumentoInterno().getCodigodocumento());

		}
		return docInterno;
	}

	@Override
	public Long obtenerNumDocumento(Dependencia dependencia) {
		Calendar calendario = Calendar.getInstance();
		Criterio filtro = Criterio.forClass(DocumentoInterno.class);
		filtro.add(Restrictions.eq("añoDocumento",
				calendario.get(Calendar.YEAR) + ""));
		filtro.addOrder(Order.desc("numerodocumento"));
		filtro.createCriteria("dependencia").add(
				Restrictions.eq("id", dependencia.getId()));
		List<DocumentoInterno> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return Long.parseLong(lista.get(0).getNumerodocumento()) + 1L;
		}
		return 1L;
	}
}
