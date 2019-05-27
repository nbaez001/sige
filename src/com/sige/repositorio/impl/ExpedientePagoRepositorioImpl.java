package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.ExpedientePago;
import com.sige.repositorio.ExpedientePagoRepositorio;

@Repository
public class ExpedientePagoRepositorioImpl extends
		BaseRepositorioImpl<ExpedientePago, Long> implements
		ExpedientePagoRepositorio {

}
