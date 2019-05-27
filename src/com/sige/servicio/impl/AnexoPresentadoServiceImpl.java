package com.sige.servicio.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.AnexoPresentado;
import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.DocumentoInternoMapeo;
import com.sige.entidad.DocumentoInternoReferen;
import com.sige.entidad.Expediente;
import com.sige.repositorio.AnexosPresentadosRepositorio;
import com.sige.repositorio.DocumentoInternoDestinoRepositorio;
import com.sige.repositorio.DocumentoInternoMapeoRepositorio;
import com.sige.repositorio.DocumentoInternoReferenRepositorio;
import com.sige.repositorio.DocumentoInternoRepositorio;
import com.sige.servicio.AnexoPresentadoService;
import com.sige.util.Criterio;
import com.sige.util.ExpedienteCanonico;

@Service
public class AnexoPresentadoServiceImpl extends
		BaseServicioImpl<AnexoPresentado, Long> implements
		AnexoPresentadoService {

	@Autowired
	private AnexosPresentadosRepositorio anexoPresentadoRepositorio;

	@Autowired
	private DocumentoInternoRepositorio docInternoRepositorio;

	@Autowired
	private DocumentoInternoDestinoRepositorio docInternoDestinoRepositorio;

	@Autowired
	private DocumentoInternoReferenRepositorio referenciaRepositorio;

	@Autowired
	private DocumentoInternoMapeoRepositorio documentoInternoMapeoRepositorio;

	@Autowired
	public AnexoPresentadoServiceImpl(
			AnexosPresentadosRepositorio anexoPresentadoRepositorio) {
		super(anexoPresentadoRepositorio);
		this.anexoPresentadoRepositorio = anexoPresentadoRepositorio;
	}

	@Override
	public Long obtenerCorrelativo(Expediente expediente) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(AnexoPresentado.class);

		Calendar calendario = Calendar.getInstance();
		filtro.createCriteria("expediente")
				.add(Restrictions.eq("codigo", expediente.getCodigo()))
				.add(Restrictions.eq("anio", calendario.get(Calendar.YEAR) + ""));
		filtro.addOrder(Order.desc("correlativo"));
		List<AnexoPresentado> anexos = buscarPorCriteria(filtro);
		if (anexos.size() > 0) {
			return Long.parseLong(anexos.get(0).getCorrelativo()) + 1L;
		}
		return 1L;
	}

	@Override
	public void AnexarExpediente(AnexoPresentado anexoPresentado,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino docInternoDestino,
			DocumentoInternoReferen referencias) {
		// TODO Auto-generated method stub
		docInternoRepositorio.crear(documentoInterno);
		docInternoDestinoRepositorio.crear(docInternoDestino);
		crear(anexoPresentado);
		referenciaRepositorio.crear(referencias);
		DocumentoInternoMapeo dim = new DocumentoInternoMapeo();
		dim.setCodigodependencia(docInternoDestino.getDependencia().getId());
		dim.setCodigoDocumento(documentoInterno.getCodigodocumento());
		dim.setCodigoDocumentoReferencia(documentoInterno.getCodigodocumento());
		dim.setCodigoDocumentoPadre(documentoInterno.getCodigodocumento());
		dim.setCodigotipodocumento(documentoInterno.getCodigoTipoDocumento());
		dim.setNivel((short) 1);
		documentoInternoMapeoRepositorio.crear(dim);
	}

	@Override
	public List<AnexoPresentado> getAllAnexos(Dependencia dependencia,
			ExpedienteCanonico expedienteCanonico) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(AnexoPresentado.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio documentoInterno = filtro.createCriteria("documentoInterno")
				.addOrder(Order.desc("fechadocumento"));
		Criterio expediente = filtro.createCriteria("expediente");
		Criterio dependenciadest = filtro.createCriteria("dependencia");
		expediente.add(Restrictions.eq("archivoProvicional", Boolean.FALSE))
				.addOrder(Order.asc("codigo"));
		if (expedienteCanonico != null) {
			if (expedienteCanonico.getAsunto() != null) {
				// filtro.createCriteria("expediente")
				documentoInterno.add(Restrictions.ilike("asunto",
						expedienteCanonico.getAsunto(), MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNumExpediente() != null) {
				// filtro.createCriteria("expediente")
				expediente.add(Restrictions.ilike("numero",
						expedienteCanonico.getNumExpediente(),
						MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNumDocumentoInterno() != null) {
				documentoInterno.add(Restrictions.ilike("numerodocumento",
						expedienteCanonico.getNumDocumentoInterno(),
						MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getRecibido()) {
				dependenciadest.add(Restrictions.eq("id", dependencia.getId()));
				if (expedienteCanonico.getDependencia() != null) {
					documentoInterno.createCriteria("dependencia").add(
							Restrictions.ilike("nombre",
									expedienteCanonico.getDependencia(),
									MatchMode.ANYWHERE));
				}
			} else if (!expedienteCanonico.getRecibido()) {
				documentoInterno.createCriteria("dependencia").add(
						Restrictions.eq("id", dependencia.getId()));
				if (expedienteCanonico.getDependencia() != null) {
					dependenciadest.add(Restrictions.ilike("nombre",
							expedienteCanonico.getDependencia(),
							MatchMode.ANYWHERE));
				}
			}
		}
		return buscarPorCriteria(filtro);

	}

	@Override
	public List<AnexoPresentado> getAllAnexos(Dependencia dependencia,
			Date fechaInicio, Date FechaFin,
			ExpedienteCanonico expedienteCanonico) {
		Criterio filtro = Criterio.forClass(AnexoPresentado.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio documentoInterno = filtro.createCriteria("documentoInterno");
				//.addOrder(Order.desc("fechadocumento")); BY NERIO
		documentoInterno.add(
				Restrictions.between("fechadocumento", fechaInicio, FechaFin))
				.addOrder(Order.desc("fechadocumento"));
		Criterio expediente = filtro.createCriteria("expediente");
		Criterio dependenciadest = filtro.createCriteria("dependencia");
		expediente.add(Restrictions.eq("archivoProvicional", Boolean.FALSE))
				.addOrder(Order.asc("codigo"));
		if (expedienteCanonico != null) {
			if (expedienteCanonico.getAsunto() != null) {
				documentoInterno.add(Restrictions.ilike("asunto",
						expedienteCanonico.getAsunto(), MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNumExpediente() != null) {
				// filtro.createCriteria("expediente")
				expediente.add(Restrictions.ilike("numero",
						expedienteCanonico.getNumExpediente(),
						MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNumDocumentoInterno() != null) {
				// filtro.createCriteria("documentoInterno")
				documentoInterno.add(Restrictions.ilike("numerodocumento",
						expedienteCanonico.getNumDocumentoInterno(),
						MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getRecibido()) {
				dependenciadest.add(Restrictions.eq("id", dependencia.getId()));

				if (expedienteCanonico.getDependencia() != null) {
					documentoInterno.createCriteria("dependencia").add(
							Restrictions.ilike("nombre",
									expedienteCanonico.getDependencia(),
									MatchMode.ANYWHERE));
				}
			} else if (!expedienteCanonico.getRecibido()) {
				// filtro.createCriteria("documentoInterno");
				documentoInterno.createCriteria("dependencia").add(
						Restrictions.eq("id", dependencia.getId()));
				if (expedienteCanonico.getDependencia() != null) {
					dependenciadest.add(Restrictions.ilike("nombre",
							expedienteCanonico.getDependencia(),
							MatchMode.ANYWHERE));
				}
			}
		}
		return buscarPorCriteria(filtro);
	}

	@Override
	public List<AnexoPresentado> obtenerAnexosDeExpediente(Expediente expediente) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(AnexoPresentado.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).addOrder(
				Order.desc("fechaIngreso"));
		Criterio expedienteCriterio = filtro.createCriteria("expediente");
		expedienteCriterio.add(Restrictions.eq("estado", Boolean.TRUE));
		expedienteCriterio.add(Restrictions.eq("id", expediente.getId()));

		return buscarPorCriteria(filtro);

	}

	@Override
	public Integer obtenerCantidadAnexosDeExpediente(Expediente expediente) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(AnexoPresentado.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio expedienteCriterio = filtro.createCriteria("expediente")
				.add(Restrictions.eq("codigo", expediente.getCodigo()))
				.add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio docInterno = filtro.createCriteria("documentoInterno")
				.setFetchMode("documentosInternosDestinos", FetchMode.JOIN)
				.createCriteria("documentosInternosDestinos")
				.add(Restrictions.isNotNull("recepcionadoPor"));

		return anexoPresentadoRepositorio.cantidadPorCriteria(filtro)
				.intValue();
	}

}
