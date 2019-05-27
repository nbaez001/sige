package com.sige.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.ExpedienteAtendido;
import com.sige.repositorio.ExpedienteAtendidoRepositorio;
import com.sige.servicio.ExpedienteAtendidoService;

@Service
public class ExpedienteAtendidoServiceImpl extends
		BaseServicioImpl<ExpedienteAtendido, Long> implements
		ExpedienteAtendidoService {

	@Autowired
	private ExpedienteAtendidoRepositorio expedienteAtendidoRepositorio;

	@Autowired
	public ExpedienteAtendidoServiceImpl(
			ExpedienteAtendidoRepositorio expedienteAtendidoRepositorio) {
		super(expedienteAtendidoRepositorio);
		this.expedienteAtendidoRepositorio = expedienteAtendidoRepositorio;
	}

}
