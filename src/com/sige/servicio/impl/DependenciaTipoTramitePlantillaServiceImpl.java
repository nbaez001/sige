package com.sige.servicio.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DependenciaTipoTramite;
import com.sige.entidad.DependenciaTipoTramitePlantilla;
import com.sige.entidad.TipoTramite;
import com.sige.repositorio.DependenciaTipoTramitePlantillaRepositorio;
import com.sige.servicio.DependenciaTipoTramitePlantillaService;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;
import com.sige.util.SigeUtil;

@Service
public class DependenciaTipoTramitePlantillaServiceImpl extends
		BaseServicioImpl<DependenciaTipoTramitePlantilla, Long> implements
		DependenciaTipoTramitePlantillaService {

	@Autowired
	DependenciaTipoTramitePlantillaRepositorio dependenciaTipoTramitePlantillaRepositorio;

	@Autowired
	protected DependenciaTipoTramitePlantillaServiceImpl(
			DependenciaTipoTramitePlantillaRepositorio dependenciaTipoTramitePlantillaRepositorio) {
		super(dependenciaTipoTramitePlantillaRepositorio);

	}

	@Override
	public void crear(DependenciaTipoTramitePlantilla object) {
		dependenciaTipoTramitePlantillaRepositorio.crear(object);
		object.setNombreArchivo(object.getNombreArchivo()
				+ SigeUtil.completarCeros(String.valueOf(object.getId()), 5, 1)
				+ ".doc");
		dependenciaTipoTramitePlantillaRepositorio.actualizar(object);
	}

	public Boolean crearTodos(List<DependenciaTipoTramitePlantilla> lista) {
		String fileName;
		for (DependenciaTipoTramitePlantilla dpttp : lista) {
			dependenciaTipoTramitePlantillaRepositorio.crear(dpttp);
			fileName = getFileName(dpttp.getDependenciaTipoTramite()
					.getDependencia(), dpttp.getDependenciaTipoTramite()
					.getTipoTramite(), dpttp);
			File origen = new File(dpttp.getRutaTemp());
			File destination = new File(
					Constantes.FILE_LOCATION.FILE_LOCATION_PLANTILLAS
							+ fileName);
			try {
				FileUtils.moveFile(origen, destination);
				dpttp.setNombreArchivo(fileName);
				dependenciaTipoTramitePlantillaRepositorio.actualizar(dpttp);
				FileUtils.deleteQuietly(origen);
			} catch (IOException e) {
				return false;
			}
		}
		return true;

	}

	@Override
	public List<DependenciaTipoTramitePlantilla> obtenerTodos(
			DependenciaTipoTramite dependenciaTipoTramite) {
		Criterio filtro = Criterio
				.forClass(DependenciaTipoTramitePlantilla.class);
		filtro.add(
				Restrictions.eq("dependenciaTipoTramite.id",
						dependenciaTipoTramite.getId())).add(
				Restrictions.eq("estado", Boolean.TRUE));
		List<DependenciaTipoTramitePlantilla> lista = buscarPorCriteria(filtro);
		return lista;
	}

	@Override
	public Boolean validarDuplicado(
			DependenciaTipoTramite dependenciaTipoTramite,
			String nombrePlantilla) {
		Criterio filtro = Criterio
				.forClass(DependenciaTipoTramitePlantilla.class);
		filtro.add(Restrictions.eq("nombrePlantilla", nombrePlantilla.trim()))
				.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("dependenciaTipoTramite.id",
				dependenciaTipoTramite.getId()));

		if (dependenciaTipoTramitePlantillaRepositorio
				.cantidadPorCriteria(filtro) > 0) {
			return true;
		}
		return false;
	}

	public Boolean update(DependenciaTipoTramitePlantilla object) {
		String fileName;
		if (object.getRutaTemp() != null && !object.getRutaTemp().isEmpty()) {
			fileName = getFileName(object.getDependenciaTipoTramite()
					.getDependencia(), object.getDependenciaTipoTramite()
					.getTipoTramite(), object);
			File origen = new File(object.getRutaTemp());
			File destination = new File(
					Constantes.FILE_LOCATION.FILE_LOCATION_PLANTILLAS
							+ fileName);
			try {
				FileUtils.moveFile(origen, destination);
				object.setNombreArchivo(fileName);
				FileUtils.deleteQuietly(origen);
			} catch (IOException e) {
				return false;
			}
		}
		dependenciaTipoTramitePlantillaRepositorio.actualizar(object);
		return true;
	}

	public void eliminarDependenciaTipoTramitePlantilla(
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla) {
		dependenciaTipoTramitePlantilla.setEstado(Boolean.FALSE);
		dependenciaTipoTramitePlantillaRepositorio
				.actualizar(dependenciaTipoTramitePlantilla);
	}

	private String getFileName(Dependencia dependencia,
			TipoTramite tipoTramite,
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla) {
		return "D"
				+ SigeUtil.completarCeros(String.valueOf(dependencia.getId()),
						5, 1)
				+ "-T"
				+ SigeUtil.completarCeros(String.valueOf(tipoTramite.getId()),
						5, 1)
				+ "-P"
				+ SigeUtil
						.completarCeros(String
								.valueOf(dependenciaTipoTramitePlantilla
										.getId()), 5, 1) + ".doc";
	}

	@Override
	public Busqueda buscarDependenciaTipoTramitePlantilla(
			DependenciaTipoTramitePlantilla dependenciaTipoTramitePlantilla,
			Long paginaActual) {
		Busqueda busqueda = new Busqueda();

		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio
				.forClass(DependenciaTipoTramitePlantilla.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("nombrePlantilla",
				dependenciaTipoTramitePlantilla.getNombrePlantilla(),
				MatchMode.ANYWHERE));
		filtro.add(Restrictions.eq("dependenciaTipoTramite.id",
				dependenciaTipoTramitePlantilla.getDependenciaTipoTramite()
						.getId()));

		Long totalRegistros = dependenciaTipoTramitePlantillaRepositorio
				.cantidadPorCriteria(filtro);
		if (totalRegistros
				% Constantes.PAGINACION.DEPENDENCIA_TIPO_TRAMITE_PLANTILLA == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.DEPENDENCIA_TIPO_TRAMITE_PLANTILLA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.DEPENDENCIA_TIPO_TRAMITE_PLANTILLA
					+ 1);
		}
		// validaciones
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.DEPENDENCIA_TIPO_TRAMITE_PLANTILLA
				.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.DEPENDENCIA_TIPO_TRAMITE_PLANTILLA
						.intValue());
		filtro.addOrder(Order.asc("nombrePlantilla"));
		busqueda.setRegistos(dependenciaTipoTramitePlantillaRepositorio
				.buscarPorCriteria(filtro));

		return busqueda;
	}

}
