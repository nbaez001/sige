package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.Expediente;
import com.sige.repositorio.ExpedienteRepositorio;

@Repository
public class ExpedienteRepositorioImpl extends
		BaseRepositorioImpl<Expediente, Long> implements ExpedienteRepositorio {

}
