package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInterno;
import com.sige.entidad.DocumentoInternoDestino;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteMovimiento;
import com.sige.repositorio.DependenciaRepositorio;
import com.sige.repositorio.DocumentoInternoDestinoRepositorio;
import com.sige.repositorio.DocumentoInternoRepositorio;
import com.sige.repositorio.ExpedienteMovimientoRepositorio;
import com.sige.repositorio.ExpedienteRepositorio;
import com.sige.servicio.DocumentoInternoService;
import com.sige.servicio.ExpedienteMovimientoServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;
import com.sige.util.ExpedienteCanonico;

@Service
public class ExpedienteMovimientoServicioImpl extends
		BaseServicioImpl<ExpedienteMovimiento, Long> implements
		ExpedienteMovimientoServicio {

	@Autowired
	private ExpedienteMovimientoRepositorio expedienteMovimientoRepositorio;

	@Autowired
	private DependenciaRepositorio dependenciaRepositorio;

	@Autowired
	private DocumentoInternoRepositorio documentoInternoRepositorio;

	@Autowired
	private DocumentoInternoDestinoRepositorio docInternoDestinoRepositorio;

	@Autowired
	private ExpedienteRepositorio expedienteRepositorio;

	@Autowired
	private DocumentoInternoService docInternoService;

	@Autowired
	protected ExpedienteMovimientoServicioImpl(
			ExpedienteMovimientoRepositorio expedienteMovimientoRepositorio) {
		super(expedienteMovimientoRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer obtenerUltimoCorrelativo() {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.addOrder(Order.desc("correlativo"));
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0).getCorrelativo() + 1;
		}
		return 1;
	}

	@Override
	public String obtenerPrimerOficinaExpediente(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).addOrder(
				Order.asc("expediente.codigo"));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));

		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return dependenciaRepositorio.obtener(
					lista.get(0).getDependencia().getId()).getNombre();
		}
		return null;
	}

	@Override
	public ExpedienteMovimiento obtenerExpedienteMovimiento(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));
		filtro.addOrder(Order.desc("correlativo"));
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public List<ExpedienteMovimiento> obtenerExpedientesOficina(
			Dependencia dependencia, ExpedienteCanonico expedienteCanonico) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
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
				expediente.add(Restrictions.ilike("asunto",
						expedienteCanonico.getAsunto(), MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNumExpediente() != null) {
				// filtro.createCriteria("expediente")
				expediente.add(Restrictions.ilike("numero",
						expedienteCanonico.getNumExpediente(),
						MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNombreSolicitante() != null) {
				expediente
						.createCriteria("solicitante")
						.add(Restrictions.eq("estado", Boolean.TRUE))
						.add(Restrictions.ilike("nombreCompleto",
								expedienteCanonico.getNombreSolicitante(),
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
	public List<ExpedienteMovimiento> obtenerExpedientesOficina(
			Dependencia dependencia, Date fechaInicio, Date FechaFin,
			ExpedienteCanonico expedienteCanonico) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio dependenciadest = filtro.createCriteria("dependencia");
		Criterio documentoInterno = filtro.createCriteria("documentoInterno");
		documentoInterno.add(
				Restrictions.between("fechadocumento", fechaInicio, FechaFin))
				.addOrder(Order.desc("fechadocumento"));
		Criterio expediente = filtro.createCriteria("expediente");
		expediente.add(Restrictions.eq("archivoProvicional", Boolean.FALSE))
				.addOrder(Order.asc("codigo"));
		if (expedienteCanonico != null) {
			if (expedienteCanonico.getAsunto() != null) {
				expediente.add(Restrictions.ilike("asunto",
						expedienteCanonico.getAsunto(), MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNumExpediente() != null) {
				// filtro.createCriteria("expediente")
				expediente.add(Restrictions.ilike("numero",
						expedienteCanonico.getNumExpediente(),
						MatchMode.ANYWHERE));
			}
			if (expedienteCanonico.getNombreSolicitante() != null) {
				expediente
						.createCriteria("solicitante")
						.add(Restrictions.eq("estado", Boolean.TRUE))
						.add(Restrictions.ilike("nombreCompleto",
								expedienteCanonico.getNombreSolicitante(),
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
	public void eliminarExpediente(Expediente expediente,
			DocumentoInterno documentoInterno,
			List<DocumentoInternoDestino> docInternoDestino,
			ExpedienteMovimiento expedienteMovimiento) {
		// TODO Auto-generated method stub
		expedienteRepositorio.actualizar(expediente);
		actualizar(expedienteMovimiento);
		documentoInternoRepositorio.actualizar(documentoInterno);
		for (DocumentoInternoDestino docDestino : docInternoDestino) {
			docInternoDestinoRepositorio.actualizar(docDestino);
		}
	}

	@Override
	public Integer obtenerCorrelativoPorExpediente(Expediente expediente) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.createCriteria("expediente")
				.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("codigo", expediente.getCodigo()));
		Integer orden = expedienteMovimientoRepositorio.cantidadPorCriteria(
				filtro).intValue();
		if (orden == 0) {
			return 1;
		} else {
			return orden + 1;
		}
	}

	@Override
	public Integer obtenerCantidadMovimientosPorExpediente(Expediente expediente) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("expediente")
				.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("codigo", expediente.getCodigo()));
		Integer cantidad = expedienteMovimientoRepositorio.cantidadPorCriteria(
				filtro).intValue();
		return cantidad;
	}

	@Override
	public List<ExpedienteMovimiento> obtenerTodosMovimientosExpediente(
			String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.addOrder(Order.asc("id"));
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.createCriteria("expediente")
				.add(Restrictions.eq("codigo", codExpediente))
				.setFetchMode("solicitante", FetchMode.EAGER);
		return buscarPorCriteria(filtro);

	}

	@Override
	public ExpedienteMovimiento obtenerMovimientoPorCorrelativo(
			Expediente expediente, Integer Correlativo) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("correlativo", Correlativo));
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("id", expediente.getId()));
		List<ExpedienteMovimiento> movimientos = buscarPorCriteria(filtro);
		if (movimientos.size() > 0) {
			return movimientos.get(0);
		}

		return null;
	}

	@Override
	public Busqueda obtenerUltimosMovimientosdeExpediente(
			Expediente expediente, Long paginaActual) {
		// TODO Auto-generated method stub
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Criterio documentoCriterio = filtro.createCriteria("documentoInterno")
				.setFetchMode("documentosInternosDestinos", FetchMode.JOIN)
				.createCriteria("documentosInternosDestinos")
				.add(Restrictions.isNotNull("fechaRecepcion"));
		Criterio expedienteCriterio = filtro.createCriteria("expediente");
		expedienteCriterio
				.add(Restrictions.ilike("anio", expediente.getAnio(),
						MatchMode.ANYWHERE))
				.add(Restrictions.ilike("asunto", expediente.getAsunto(),
						MatchMode.ANYWHERE)).addOrder(Order.asc("codigo"));
		expedienteCriterio
				.createCriteria("solicitante")
				.add(Restrictions.ilike("codigoPersona", expediente
						.getSolicitante().getCodigoPersona(),
						MatchMode.ANYWHERE))
				.add(Restrictions.ilike("nombreCompleto", expediente
						.getSolicitante().getNombreCompleto(),
						MatchMode.ANYWHERE));

		Criterio expedienteMovCriterio = expedienteCriterio.setFetchMode(
				"expedientesMovimiento", FetchMode.JOIN).createCriteria(
				"expedientesMovimiento");
		Criterio docInternoCriterio = expedienteMovCriterio
				.createCriteria("documentoInterno")
				.setFetchMode("documentosInternosDestinos", FetchMode.JOIN)
				.createCriteria("documentosInternosDestinos")
				.add(Restrictions.not(Restrictions.eq("tipoMovimiento", '3')));

		List<ExpedienteMovimiento> listaMovimientos = buscarPorCriteria(filtro);
		List<ExpedienteMovimiento> ultimosMovimientosporExpediente = new ArrayList<ExpedienteMovimiento>();
		List<String> codExpedientes = new ArrayList<String>();
		// if(listaMovimientos.size() > 0){

		for (ExpedienteMovimiento movimiento : listaMovimientos) {
			codExpedientes.add(movimiento.getExpediente().getCodigo());
		}
		Set<String> linkedHashSet = new LinkedHashSet<String>();
		linkedHashSet.addAll(codExpedientes);
		codExpedientes.clear();
		codExpedientes.addAll(linkedHashSet);

		for (String codExpediente : codExpedientes) {
			ExpedienteMovimiento movUltimo = obtenerExpedienteMovimiento(codExpediente);
			ultimosMovimientosporExpediente.add(movUltimo);
		}
		Long totalRegistros = new Long(ultimosMovimientosporExpediente.size());
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
		busqueda.setRegistos(ultimosMovimientosporExpediente);
		return busqueda;

		// }
		// return null;

	}

	@Override
	public ExpedienteMovimiento obtenerPrimerMovimientoExpediente(
			Expediente expediente) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", expediente.getCodigo()));
		filtro.addOrder(Order.asc("correlativo"));
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public List<ExpedienteMovimiento> buscarPorCodigoDocumento(
			String codigoDocumento) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("documentoInterno").add(
				Restrictions.eq("codigodocumento", codigoDocumento));
		filtro.setFetchMode("documentoInterno", FetchMode.EAGER);
		filtro.setFetchMode("expediente", FetchMode.EAGER);
		return buscarPorCriteria(filtro);
	}

	@Override
	public Long getTotalExpedientesDependencia(Long idDependencia, Date desde,
			Date hasta) {

		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		Criterio dependenciadest = filtro.createCriteria("dependencia");
		dependenciadest.add(Restrictions.eq("id", idDependencia));
		Criterio documentoInterno = filtro.createCriteria("documentoInterno");
		documentoInterno.add(Restrictions.between("fechadocumento", desde,
				hasta));
		return expedienteRepositorio.cantidadPorCriteria(filtro);
	}

	@Override
	public Long getTotalExpedienteNoTupaPorCodigoDocumento(
			String codigoDocumento) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.createCriteria("documentoInterno").add(
				Restrictions.eq("codigodocumento", codigoDocumento));
		filtro.createCriteria("expediente").createCriteria("tipoTramite")
				.add(Restrictions.eq("tupa", "N"));
		return expedienteMovimientoRepositorio.cantidadPorCriteria(filtro);
	}

	@Override
	public Dependencia obtenerDependenciaEnvio(ExpedienteMovimiento movimiento) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("correlativo",
						movimiento.getCorrelativo() + 1))
				.createCriteria("expediente")
				.add(Restrictions.eq("codigo", movimiento.getExpediente()
						.getCodigo()));
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0).getDependencia();
		}
		return null;
	}

	@Override
	public Dependencia obtenerPrimerOficinaEnvio(String codigoExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("correlativo", 1))
				.createCriteria("expediente")
				.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("codigo", codigoExpediente));
		filtro.addOrder(Order.asc("correlativo"));
		filtro.setMaxResults(1);
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0).getDependencia();
		}
		return null;

	}

	@Override
	public Dependencia obtenerDependenciaActual(String codigoExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.createCriteria("expediente")
				.add(Restrictions.eq("codigo", codigoExpediente));
		filtro.addOrder(Order.desc("correlativo"));
		filtro.setMaxResults(1);
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0).getDependencia();
		}
		return null;
	}

	@Override
	public Boolean validarDependenciaMovimientos(Dependencia dependencia) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.createCriteria("dependencia")
				.add(Restrictions.eq("id", dependencia.getId()));
		List<ExpedienteMovimiento> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ExpedienteMovimiento> obtenerTodosPorRangoFechas(
			String codExpediente, Date inicio, Date hasta) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.createCriteria("expediente")
				.add(Restrictions.eq("codigo", codExpediente));

		filtro.add(Restrictions.between("fechaCreacion", inicio, hasta));
		filtro.addOrder(Order.asc("correlativo"));
		return buscarPorCriteria(filtro);

	}

	public List<ExpedienteMovimiento> obtenerTodosPorRangoRecepcionFechas(
			String codExpediente, Date inicio, Date hasta) {
		Criterio filtro = Criterio.forClass(ExpedienteMovimiento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.createCriteria("expediente")
				.add(Restrictions.eq("codigo", codExpediente));

		filtro.createCriteria("documentoInterno")
				.createCriteria("documentosInternosDestinos")
				.add(Restrictions.between("fechaRecepcion", inicio, hasta));
		filtro.addOrder(Order.asc("correlativo"));
		return buscarPorCriteria(filtro);

	}
}