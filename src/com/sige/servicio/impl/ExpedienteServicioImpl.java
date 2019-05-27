package com.sige.servicio.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DiaNoHabil;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.DocumentoInternoMapeo;
import com.sige.entidad.DocumentoInternoReferen;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteMovimiento;
import com.sige.entidad.ExpedientePago;
import com.sige.entidad.ExpedienteRequisito;
import com.sige.entidad.Persona;
import com.sige.entidad.TipoTramite;
import com.sige.entidad.Usuario;
import com.sige.repositorio.DiaNoHabilRepositorio;
import com.sige.repositorio.DocumentoInternoDestinoRepositorio;
import com.sige.repositorio.DocumentoInternoMapeoRepositorio;
import com.sige.repositorio.DocumentoInternoReferenRepositorio;
import com.sige.repositorio.DocumentoInternoRepositorio;
import com.sige.repositorio.ExpedienteMovimientoRepositorio;
import com.sige.repositorio.ExpedientePagoRepositorio;
import com.sige.repositorio.ExpedienteRepositorio;
import com.sige.repositorio.ExpedienteRequisitoRepositorio;
import com.sige.servicio.DiaNoHabilServicio;
import com.sige.servicio.DocumentoInternoMapeoServicio;
import com.sige.servicio.ExpedienteMovimientoServicio;
import com.sige.servicio.ExpedienteServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;
import com.sige.util.ExpedienteCanonico;
import com.sige.util.SigeUtil;
import com.sige.util.TramiteTupaEstadistica;

@Service
public class ExpedienteServicioImpl extends BaseServicioImpl<Expediente, Long>
		implements ExpedienteServicio {

	@Autowired
	private ExpedienteRepositorio expedienteRepositorio;

	@Autowired
	private ExpedientePagoRepositorio expedientePagoRepositorio;

	@Autowired
	private ExpedienteRequisitoRepositorio expedienteRequisitoRepositorio;

	@Autowired
	private ExpedienteMovimientoRepositorio expedienteMovimientoRepositorio;

	@Autowired
	private DocumentoInternoRepositorio documentoInternoRepositorio;

	@Autowired
	private DocumentoInternoDestinoRepositorio documentoInternoDestinoRepositorio;

	@Autowired
	private DocumentoInternoReferenRepositorio docInternoReferenRepositorio;

	@Autowired
	private DocumentoInternoMapeoRepositorio documentoInternoMapeoRepositorio;

	@Autowired
	private DocumentoInternoMapeoServicio documentoInternoMapeoServicio;

	@Autowired
	private DiaNoHabilRepositorio diaNoHabilRepositorio;

	@Autowired
	private ExpedienteMovimientoServicio expedienteMovimientoServicio;

	@Autowired
	private DiaNoHabilServicio diaNoHabilServicio;

	private DocumentoInternoMapeo documentoInternoMapeo;
	private List<DocumentoInternoMapeo> documentosInternosMapeo;
	private Short maxnivel;

	@Autowired
	public ExpedienteServicioImpl(ExpedienteRepositorio expedienteRepositorio) {
		super(expedienteRepositorio);
		this.expedienteRepositorio = expedienteRepositorio;
	}

	@Override
	public List<Expediente> obtenerUltimoRegistro() {
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.addOrder(Order.desc("codigo"));
		return buscarPorCriteria(filtro);
	}

	public void registrarExpediente(Expediente expediente,ExpedienteMovimiento expedienteMovimiento,DocumentoInterno documentoInterno,DocumentoInternoDestino documentoInternoDestino,DocumentoInternoReferen referencia) {
		crear(expediente);
		documentoInternoRepositorio.crear(documentoInterno);
		expedienteMovimientoRepositorio.crear(expedienteMovimiento);

		documentoInternoDestinoRepositorio.crear(documentoInternoDestino);
		docInternoReferenRepositorio.crear(referencia);
		DocumentoInternoMapeo dim = new DocumentoInternoMapeo();
		dim.setCodigodependencia(expedienteMovimiento.getDependencia().getId());
		dim.setCodigoDocumento(documentoInterno.getCodigodocumento());
		dim.setCodigoDocumentoReferencia(documentoInterno.getCodigodocumento());
		dim.setCodigoDocumentoPadre(documentoInterno.getCodigodocumento());
		dim.setCodigotipodocumento(documentoInterno.getCodigoTipoDocumento());
		dim.setNivel((short) 1);
		documentoInternoMapeoRepositorio.crear(dim);
	}

	@Override
	public void registrarExpediente(Expediente expediente,ExpedienteMovimiento expedienteMovimiento,List<ExpedienteRequisito> expedienteRequisitos,List<ExpedientePago> expedientePagos,DocumentoInterno documentoInterno,DocumentoInternoDestino documentoInternoDestino,DocumentoInternoReferen referencia) {
		// TODO Auto-generated method stub
		crear(expediente);
		documentoInternoRepositorio.crear(documentoInterno);
		expedienteMovimientoRepositorio.crear(expedienteMovimiento);
		documentoInternoDestinoRepositorio.crear(documentoInternoDestino);
		for (ExpedienteRequisito er : expedienteRequisitos) {
			expedienteRequisitoRepositorio.crear(er);
		}
		for (ExpedientePago pago : expedientePagos) {
			expedientePagoRepositorio.crear(pago);
		}
		docInternoReferenRepositorio.crear(referencia);
		DocumentoInternoMapeo dim = new DocumentoInternoMapeo();
		dim.setCodigodependencia(expedienteMovimiento.getDependencia().getId());
		dim.setCodigoDocumento(documentoInterno.getCodigodocumento());
		dim.setCodigoDocumentoReferencia(documentoInterno.getCodigodocumento());
		dim.setCodigoDocumentoPadre(documentoInterno.getCodigodocumento());
		dim.setCodigotipodocumento(documentoInterno.getCodigoTipoDocumento());
		dim.setNivel((short) 1);
		documentoInternoMapeoRepositorio.crear(dim);

	}

	@Override
	public Long obtenerUltimoCorrelativo() {
		Calendar calendario = Calendar.getInstance();
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("anio", calendario.get(Calendar.YEAR) + ""))
				.addOrder(Order.desc("numero"));
		List<Expediente> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return Long.parseLong(lista.get(0).getNumero()) + 1L;
		} else {
			return 1L;
		}

	}

	@Override
	public Busqueda buscarExpediente(Expediente expediente, Long paginaActual,
			ExpedienteCanonico expedienteCanonico) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(Expediente.class);
		Criterio filtroExpedienteMov = filtro.setFetchMode(
				"expedientesMovimiento", FetchMode.JOIN).createCriteria(
				"expedientesMovimiento");

		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.ilike("codigo", expediente.getCodigo(),
						MatchMode.ANYWHERE))
				.add(Restrictions.ilike("asunto", expediente.getAsunto(),
						MatchMode.ANYWHERE))
				.add(Restrictions.ilike("anio", expediente.getAnio(),
						MatchMode.ANYWHERE));

		filtroExpedienteMov.createCriteria("documentoInterno")
				.setFetchMode("documentosInternosDestinos", FetchMode.JOIN)
				.createCriteria("documentosInternosDestinos")
				.add(Restrictions.not(Restrictions.eq("tipoMovimiento", '3')));

		if (expedienteCanonico.getTipoBusqueda() == 0) {
			filtro.add(Restrictions.eq("archivoProvicional", Boolean.TRUE));
			filtro.createCriteria("solicitante")
					.add(Restrictions.ilike("nombreCompleto", expediente
							.getSolicitante().getNombreCompleto(),
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("codigoPersona", expediente
							.getSolicitante().getCodigoPersona(),
							MatchMode.ANYWHERE));
			filtro.createCriteria("tipoTramite").add(
					Restrictions.eq("tupa", "T"));
		} else if (expedienteCanonico.getTipoBusqueda() == 1) {
			filtro.add(Restrictions.ilike("numero", expediente.getNumero(),
					MatchMode.ANYWHERE));
			filtro.add(Restrictions.eq("tramiteFinalizado", Boolean.FALSE));
			Criterion restriccionExpediente = Restrictions.not(Restrictions.in(
					"codigo", expedienteCanonico.getExpedientesRepetidos()));
			filtro.add(restriccionExpediente);
			filtroExpedienteMov.createCriteria("dependencia").add(
					Restrictions.eq("id", expedienteCanonico
							.getDependenciaEntidad().getId()));
		} else if (expedienteCanonico.getTipoBusqueda() == 2) {
			filtro.add(Restrictions.eq("archivoProvicional", Boolean.FALSE));
			filtro.createCriteria("solicitante")
					.add(Restrictions.ilike("nombreCompleto", expediente
							.getSolicitante().getNombreCompleto(),
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("codigoPersona", expediente
							.getSolicitante().getCodigoPersona(),
							MatchMode.ANYWHERE));
		} else if (expedienteCanonico.getTipoBusqueda() == 3) {
			filtro.createCriteria("solicitante")
					.add(Restrictions.ilike("nombreCompleto", expediente
							.getSolicitante().getNombreCompleto(),
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("codigoPersona", expediente
							.getSolicitante().getCodigoPersona(),
							MatchMode.ANYWHERE));
		}

		Long totalRegistros = expedienteRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.EXPEDIENTE != 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.EXPEDIENTE + 1);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.EXPEDIENTE);
		}
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		filtro.setMaxResults(Constantes.PAGINACION.EXPEDIENTE.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.EXPEDIENTE.intValue());
		busqueda.setRegistos(expedienteRepositorio.buscarPorCriteria(filtro));
		return busqueda;
	}

	@Override
	public Expediente obtenerExpediente(String codExpediente) {
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("codigo", codExpediente));
		List<Expediente> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public List<Expediente> obtenerExpedientesArchivadosProvicionalmente() {
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("archivoProvicional", Boolean.TRUE));
		return buscarPorCriteria(filtro);
	}

	@Override
	public List<Expediente> obtenerExpedientesPendientesAtencion() {
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("tramiteFinalizado", Boolean.FALSE))
				.add(Restrictions.eq("archivoProvicional", Boolean.FALSE))
				.setFetchMode("tipoTramite", FetchMode.EAGER);
		return buscarPorCriteria(filtro);
	}

	@Override
	public List<Expediente> obtenerExpedientes(List<String> codigosExpediente) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.in("codigo", codigosExpediente));
		return buscarPorCriteria(filtro);
	}

	@Override
	public void derivarExpediente(
			List<ExpedienteMovimiento> expedientesMovimiento,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino,
			List<DocumentoInternoReferen> referencias) {
		// TODO Auto-generated method stub
		documentoInternoRepositorio.crear(documentoInterno);
		documentoInternoDestinoRepositorio.crear(documentoInternoDestino);
		for (ExpedienteMovimiento expedientemov : expedientesMovimiento) {
			expedienteMovimientoRepositorio.crear(expedientemov);
		}

		for (DocumentoInternoReferen referencia : referencias) {
			docInternoReferenRepositorio.crear(referencia);
			agregarMapeo(referencia.getCodigoDocumentoReferencia(),
					documentoInterno, documentoInternoDestino);

		}

	}

	private void agregarMapeo(String codigoDocumento,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino) {
		maxnivel = 0;
		documentosInternosMapeo = documentoInternoMapeoServicio
				.getAllrecorrido(codigoDocumento);
		for (DocumentoInternoMapeo dim : documentosInternosMapeo) {
			if (maxnivel < dim.getNivel()) {
				maxnivel = dim.getNivel();
			}
			documentoInternoMapeo = new DocumentoInternoMapeo();
			documentoInternoMapeo.setCodigoDocumento(documentoInterno
					.getCodigodocumento());
			documentoInternoMapeo.setCodigodependencia(dim
					.getCodigodependencia());
			documentoInternoMapeo.setCodigoDocumentoPadre(dim
					.getCodigoDocumentoPadre());
			documentoInternoMapeo.setCodigoDocumentoReferencia(dim
					.getCodigoDocumento());
			documentoInternoMapeo.setCodigotipodocumento(dim
					.getCodigotipodocumento());
			documentoInternoMapeo.setNivel(dim.getNivel());
			documentoInternoMapeoRepositorio.crear(documentoInternoMapeo);
		}
		documentoInternoMapeo = new DocumentoInternoMapeo();
		documentoInternoMapeo.setCodigoDocumento(documentoInterno
				.getCodigodocumento());
		documentoInternoMapeo.setCodigodependencia(documentoInternoDestino
				.getDependencia().getId());
		documentoInternoMapeo.setCodigoDocumentoPadre(documentoInterno
				.getCodigodocumento());
		documentoInternoMapeo.setCodigoDocumentoReferencia(codigoDocumento);
		documentoInternoMapeo.setCodigotipodocumento(documentoInterno
				.getCodigoTipoDocumento());
		documentoInternoMapeo.setNivel((short) (maxnivel + 1));
		documentoInternoMapeoRepositorio.crear(documentoInternoMapeo);
	}

	@Override
	public void responderExpediente(
			List<ExpedienteMovimiento> expedientesMovimiento,
			DocumentoInterno documentoInterno,
			DocumentoInternoDestino documentoInternoDestino,
			List<DocumentoInternoReferen> referencias) {
		// TODO Auto-generated method stub
		documentoInternoRepositorio.crear(documentoInterno);
		documentoInternoDestinoRepositorio.crear(documentoInternoDestino);
		for (ExpedienteMovimiento expedientemov : expedientesMovimiento) {
			expedienteMovimientoRepositorio.crear(expedientemov);
		}
		for (DocumentoInternoReferen referencia : referencias) {
			docInternoReferenRepositorio.crear(referencia);
			agregarMapeo(referencia.getCodigoDocumentoReferencia(),
					documentoInterno, documentoInternoDestino);
		}
	}

	@Override
	public List<Expediente> consultar(Date desde, Date hasta,
			Dependencia inicial, Dependencia actual, Persona contribuyente,
			Usuario terminalista, TipoTramite procedimiento, Integer mostrar) {

		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(Restrictions.between("fechaExpediente", desde, hasta));
		Criterio movimientoCriterio = filtro.setFetchMode("expedientesMovimiento", FetchMode.EAGER).add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio tipoTramiteCriterio = filtro.setFetchMode("tipoTramite", FetchMode.EAGER);

		filtro.setFetchMode("solicitante", FetchMode.EAGER);
		if (mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_FINALIZADOS) {
			filtro.add(Restrictions.eq("tramiteFinalizado", Boolean.TRUE));
			filtro.setFetchMode("expedienteatendido", FetchMode.EAGER);

		} else if (mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_EN_PROCESO || mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_VENCIDOS_EN_PROCESO) {
			filtro.add(Restrictions.eq("tramiteFinalizado", Boolean.FALSE));
		}

		if (mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_VENCIDOS_EN_ESPERA) {
			filtro.add(Restrictions.eq("archivoProvicional", Boolean.TRUE));
		} else {
			filtro.add(Restrictions.eq("archivoProvicional", Boolean.FALSE));
		}
		if (contribuyente != null) {
			filtro.createCriteria("solicitante").add(Restrictions.eq("id", contribuyente.getId()));
		}
		if (inicial != null || actual != null) {
			if (inicial != null) {
				filtro.createCriteria("expedientesMovimiento").add(Restrictions.eq("estado", Boolean.TRUE)).add(Restrictions.eq("correlativo", 1)).createCriteria("dependencia").add(Restrictions.eq("id", inicial.getId()));

			} else if (actual != null) {
				filtro.createCriteria("expedientesMovimiento").add(Restrictions.eq("estado", Boolean.TRUE)).createCriteria("dependencia").add(Restrictions.eq("id", actual.getId()));
			}

		}
		if (terminalista != null) {
			filtro.createCriteria("expedientesMovimiento").createCriteria("documentoInterno").createCriteria("documentosInternosDestinos").add(Restrictions.eq("recepcionadoPor", terminalista.getUsuario().trim()));
		}
		if (procedimiento != null) {
			filtro.createCriteria("tipoTramite").add(Restrictions.eq("estado", Boolean.TRUE)).add(Restrictions.eq("codigoTipoTramite",procedimiento.getCodigoTipoTramite()));
		}
		List<Expediente> lista = buscarPorCriteria(filtro);
		Set<Expediente> linkedHashSet = new LinkedHashSet<Expediente>();
		linkedHashSet.addAll(lista);
		lista.clear();
		lista.addAll(linkedHashSet);
		Date fechaFinalizado;
		Date fechaExpediente;
		Date fechaVencimiento;
		// Calculamos los dias transcuridos y los faltantes
		boolean eliminado = false;
		for (Expediente expediente : lista) {

			if (mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_VENCIDOS_EN_PROCESO) {
				fechaFinalizado = new Date();
				fechaExpediente = new Date(expediente.getFechaExpediente().getTime());
				fechaVencimiento = SigeUtil.sumarDiasFecha(fechaExpediente,diaNoHabilServicio.getCantidadDiasNoHabiles(fechaExpediente, fechaFinalizado).intValue() + expediente.getTipoTramite().getTipoAten());

				if (SigeUtil.obtenerFechaFormato(fechaVencimiento.toString(), 0).compareTo(SigeUtil.obtenerFechaFormato(fechaFinalizado.toString(), 0)) == 0 || fechaFinalizado.compareTo(fechaVencimiento) > 0) {
					expediente.setDiasVencidos((SigeUtil.getDiasTranscurridos(fechaVencimiento, fechaFinalizado) - diaNoHabilServicio.getCantidadDiasNoHabiles(fechaVencimiento,fechaFinalizado)));
				} else {
					lista.remove(expediente);
					eliminado = true;

				}
			} else if (mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_VENCIDOS_EN_ESPERA) {

				fechaFinalizado = new Date();
				fechaExpediente = new Date(expediente.getFechaExpediente().getTime());
				fechaVencimiento = SigeUtil.sumarDiasFecha(fechaExpediente,diaNoHabilServicio.getCantidadDiasNoHabiles(fechaExpediente, fechaFinalizado).intValue()+ diaNoHabilServicio.getDiasPlazoVencimiento());

				if (SigeUtil.obtenerFechaFormato(fechaVencimiento.toString(), 0).compareTo(SigeUtil.obtenerFechaFormato(fechaFinalizado.toString(), 0)) == 0
						|| fechaFinalizado.compareTo(fechaVencimiento) > 0) {

					expediente
							.setDiasVencidos((SigeUtil.getDiasTranscurridos(
									fechaVencimiento, fechaFinalizado) - diaNoHabilServicio
									.getCantidadDiasNoHabiles(fechaVencimiento,
											fechaFinalizado)));
				} else {
					lista.remove(expediente);
					eliminado = true;
				}
				// agregamos el tipo de atencion a los documentos finalizados

			} else if (mostrar == Constantes.OPCION_MOSTRAR.MOSTRAR_FINALIZADOS) {

				fechaFinalizado = new Date(expediente.getFechaFinalizado()
						.getTime());
				fechaExpediente = new Date(expediente.getFechaExpediente()
						.getTime());
				fechaVencimiento = SigeUtil.sumarDiasFecha(
						fechaExpediente,
						diaNoHabilServicio.getCantidadDiasNoHabiles(
								fechaExpediente, fechaFinalizado).intValue()
								+ expediente.getTipoTramite().getTipoAten());

				if (SigeUtil
						.obtenerFechaFormato(fechaVencimiento.toString(), 0)
						.compareTo(
								SigeUtil.obtenerFechaFormato(
										fechaFinalizado.toString(), 0)) == 0
						|| fechaVencimiento.compareTo(fechaFinalizado) > 0) {

					expediente.setAtendidoTipo("Dentro de Plazo");
				} else {
					expediente
							.setAtendidoTipo((SigeUtil.getDiasTranscurridos(
									fechaVencimiento, fechaFinalizado) - diaNoHabilServicio
									.getCantidadDiasNoHabiles(fechaVencimiento,
											fechaFinalizado))
									+ " días Fuera de plazo");
				}

			}
			if (!eliminado) {
				Criterio filtroDiasNoHabiles = Criterio
						.forClass(DiaNoHabil.class);
				filtroDiasNoHabiles
						.add(Restrictions.eq("estado", Boolean.TRUE));
				Date dateInicio = (Date) expediente.getFechaExpediente();
				Date dateFinal = new Date();
				filtroDiasNoHabiles.add(Restrictions.between("fecha",
						dateInicio, dateFinal));
				Long cantidadDiasNOHabiles = diaNoHabilRepositorio
						.cantidadPorCriteria(filtroDiasNoHabiles);
				Integer diasTranscurridos = new Integer(
						SigeUtil.getDiasTranscurridos(expediente
								.getFechaExpediente()));
				diasTranscurridos = diasTranscurridos.intValue()
						- cantidadDiasNOHabiles.intValue();
				expediente.setDiasTranscurridos(diasTranscurridos);
				expediente.setDiasFaltantes(expediente.getTipoTramite()
						.getTipoAten().intValue()
						- expediente.getDiasTranscurridos().intValue());
			}
			eliminado = false;

		}
		return lista;
	}

	@Override
	public TramiteTupaEstadistica consultarEstadistica(Integer tipoEstadistica,
			String idTipoTramite, Date desde, Date hasta, Boolean finalizados) {

		TramiteTupaEstadistica tte = new TramiteTupaEstadistica();
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.between("fechaExpediente", desde, hasta));

		if (Constantes.TIPO_ESTADISTICA.PROCEDIMIENTO_TUPA == tipoEstadistica) {
			filtro.createCriteria("tipoTramite").add(
					Restrictions.eq("tupa", "T"));
		}
		if (idTipoTramite != null) {
			filtro.add(Restrictions.eq("tipoTramite.codigoTipoTramite",
					idTipoTramite));
		}
		if (finalizados) {
			filtro.add(Restrictions.eq("tramiteFinalizado", Boolean.TRUE));
			if (idTipoTramite != null) {
				List<Expediente> lista = buscarPorCriteria(filtro);
				Long diasTotal = 0L;
				for (Expediente expediente : lista) {
					diasTotal += (expediente.getFechaFinalizado().getTime() - expediente
							.getFechaExpediente().getTime())
							/ (24 * 60 * 60 * 1000);
				}
				tte.setDiasTotal(diasTotal);
			}
		}
		tte.setTotal(expedienteRepositorio.cantidadPorCriteria(filtro));

		return tte;
	}

	@Override
	public Map<String, Integer[]> consultaTrazabilidad(Date desde, Date hasta,
			String idTipoTramite, String expedienteId) {

		Map<String, Integer[]> trazabilidad = null;

		if (idTipoTramite != null) {
			List<Expediente> expedientes = obtenerPorTipoTramite(idTipoTramite,
					desde, hasta);

			for (Expediente expediente : expedientes) {
				List<ExpedienteMovimiento> expedienteMovimientos = expedienteMovimientoServicio
						.obtenerTodosPorRangoRecepcionFechas(
								expediente.getCodigo(), desde, hasta);
				if (trazabilidad == null) {
					trazabilidad = obtenerTrazabilidad(expedienteMovimientos,
							hasta, null);
				} else {
					trazabilidad = obtenerTrazabilidad(expedienteMovimientos,
							hasta, trazabilidad);
				}

			}

		} else {
			List<ExpedienteMovimiento> expedienteMovimientos = expedienteMovimientoServicio
					.obtenerTodosPorRangoRecepcionFechas(expedienteId, desde,
							hasta);

			trazabilidad = obtenerTrazabilidad(expedienteMovimientos, hasta,
					null);

		}
		return trazabilidad;

	}

	private Map<String, Integer[]> obtenerTrazabilidad(
			List<ExpedienteMovimiento> expedienteMovimientos, Date hasta,
			Map<String, Integer[]> trazabilidadAnterior) {
		Map<String, Integer[]> trazabilidad;
		if (trazabilidadAnterior == null) {
			trazabilidad = new HashMap<String, Integer[]>();
		} else {
			trazabilidad = trazabilidadAnterior;
		}
		String nombreDependencia;
		for (int i = 0; i < expedienteMovimientos.size(); i++) {
			nombreDependencia = expedienteMovimientos.get(i).getDependencia()
					.getNombre();
			if (i + 1 < expedienteMovimientos.size()) {

				if (trazabilidad.get(nombreDependencia) == null) {
					trazabilidad
							.put(nombreDependencia,
									new Integer[] {
											Integer.valueOf(SigeUtil
													.getDiasTranscurridos(
															expedienteMovimientos
																	.get(i)
																	.getDocumentoInterno()
																	.getDocumentosInternosDestinos()
																	.get(0)
																	.getFechaRecepcion(),
															expedienteMovimientos
																	.get(i + 1)
																	.getDocumentoInterno()
																	.getFechadocumento())),
											1 });
				} else {
					trazabilidad.get(nombreDependencia)[0] = trazabilidad
							.get(nombreDependencia)[0]
							+ Integer.valueOf(SigeUtil.getDiasTranscurridos(
									expedienteMovimientos.get(i)
											.getDocumentoInterno()
											.getDocumentosInternosDestinos()
											.get(0).getFechaRecepcion(),
									expedienteMovimientos.get(i + 1)
											.getDocumentoInterno()
											.getFechadocumento()));

					trazabilidad.get(nombreDependencia)[1]++;

				}
			} else {
				if (trazabilidad.get(nombreDependencia) == null) {
					trazabilidad
							.put(nombreDependencia,
									new Integer[] {
											Integer.valueOf(SigeUtil
													.getDiasTranscurridos(
															expedienteMovimientos
																	.get(i)
																	.getDocumentoInterno()
																	.getDocumentosInternosDestinos()
																	.get(0)
																	.getFechaRecepcion(),

															new Timestamp(hasta
																	.getTime()))),
											1 });
				} else {
					trazabilidad.get(nombreDependencia)[0] = trazabilidad
							.get(nombreDependencia)[0]
							+ Integer.valueOf(SigeUtil.getDiasTranscurridos(
									expedienteMovimientos.get(i)
											.getDocumentoInterno()
											.getDocumentosInternosDestinos()
											.get(0).getFechaRecepcion(),
									new Timestamp(hasta.getTime())));
					trazabilidad.get(nombreDependencia)[1]++;

				}

			}
		}
		return trazabilidad;
	}

	@Override
	public List<Expediente> obtenerPorTipoTramite(String idTipoTramite,
			Date desde, Date hasta) {
		Criterio filtro = Criterio.forClass(Expediente.class);
		filtro.add(
				Restrictions.eq("tipoTramite.codigoTipoTramite", idTipoTramite))
				.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("archivoProvicional", Boolean.FALSE));

		if (desde != null && hasta != null) {
			filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
					Restrictions.between("fechaExpediente", desde, hasta));
		}

		return expedienteRepositorio.buscarPorCriteria(filtro);
	}

}