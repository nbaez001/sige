package com.sige.servicio.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteEscaneado;
import com.sige.repositorio.ExpedienteEscaneadoRepositorio;
import com.sige.servicio.ExpedienteEscaneadoServicio;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class ExpedienteEscaneadoServicioImpl extends
		BaseServicioImpl<ExpedienteEscaneado, Long> implements
		ExpedienteEscaneadoServicio {

	@Autowired
	private ExpedienteEscaneadoRepositorio expedienteEscaneadoRepositorio;

	@Autowired
	protected ExpedienteEscaneadoServicioImpl(
			ExpedienteEscaneadoRepositorio expedienteEscaneadoRepositorio) {
		super(expedienteEscaneadoRepositorio);
	}

	@Override
	public Boolean validarDuplicado(String descripcion) {
		Criterio filtro = Criterio.forClass(ExpedienteEscaneado.class);
		filtro.add(Restrictions.eq("descripcion", descripcion.trim())).add(
				Restrictions.eq("estado", Boolean.TRUE));

		if (expedienteEscaneadoRepositorio.cantidadPorCriteria(filtro) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ExpedienteEscaneado> obtenerExpedientesEscaneados(
			Dependencia dependencia, Expediente expediente) {

		Criterio filtro = Criterio.forClass(ExpedienteEscaneado.class);

		if (dependencia != null) {
			filtro.add(Restrictions.eq("dependencia.id", dependencia.getId()));
		} else {
			filtro.setFetchMode("dependencia", FetchMode.EAGER);
		}

		filtro.createCriteria("expediente").add(
				Restrictions.eq("id", expediente.getId()));

		filtro.add(Restrictions.eq("estado", Boolean.TRUE));

		return expedienteEscaneadoRepositorio.buscarPorCriteria(filtro);
	}

	@Override
	public Long obtenerCorrelativo(Dependencia dependencia,
			Expediente expediente) {
		Criterio filtro = Criterio.forClass(ExpedienteEscaneado.class);
		filtro.add(Restrictions.eq("dependencia.id", dependencia.getId()));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("id", expediente.getId()));

		return expedienteEscaneadoRepositorio.cantidadPorCriteria(filtro);
	}

	@Override
	public void crear(ExpedienteEscaneado expedienteEscaneado) {
		moverArchivo(expedienteEscaneado);
		expedienteEscaneadoRepositorio.crear(expedienteEscaneado);
	}

	@Override
	public void actualizar(ExpedienteEscaneado expedienteEscaneado) {
		moverArchivo(expedienteEscaneado);
		expedienteEscaneadoRepositorio.actualizar(expedienteEscaneado);
	}

	private void moverArchivo(ExpedienteEscaneado expedienteEscaneado) {
		if (expedienteEscaneado.getNombreArchivoTemporal() != null
				&& !expedienteEscaneado.getNombreArchivoTemporal().isEmpty()) {
			File origen = new File(
					expedienteEscaneado.getNombreArchivoTemporal());
			File destination = new File(
					Constantes.FILE_LOCATION.FILE_LOCATION_EXPEDIENTES_ESCANEADOS
							+ expedienteEscaneado.getNombreArchivo());
			try {
				FileUtils.moveFile(origen, destination);
				FileUtils.deleteQuietly(origen);
			} catch (IOException e) {

			}
		}
	}

	@Override
	public void eliminar(ExpedienteEscaneado expedienteEscaneado) {
		expedienteEscaneado.setEstado(Boolean.FALSE);
		expedienteEscaneadoRepositorio.actualizar(expedienteEscaneado);

	}

}
