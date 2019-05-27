package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.ExpedientePago;
import com.sige.repositorio.ExpedientePagoRepositorio;
import com.sige.servicio.ExpedientePagoServicio;
import com.sige.util.Criterio;

@Service
public class ExpedientePagoServicioImpl extends
		BaseServicioImpl<ExpedientePago, Long> implements
		ExpedientePagoServicio {

	@Autowired
	private ExpedientePagoRepositorio expedientePagoRepostorio;

	@Autowired
	protected ExpedientePagoServicioImpl(
			ExpedientePagoRepositorio expedientePagoRepositorio) {
		super(expedientePagoRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long obtenerCantidadPagosPresentados(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedientePago.class);
		filtro.add(Restrictions.eq("presenta", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));

		return expedientePagoRepostorio.cantidadPorCriteria(filtro);
	}

	@Override
	public Long obtenerCantidadPagosNoPresentados(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedientePago.class);
		filtro.add(Restrictions.eq("presenta", Boolean.FALSE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));
		return expedientePagoRepostorio.cantidadPorCriteria(filtro);
	}

	@Override
	public List<ExpedientePago> obtenerExpedientesPago(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedientePago.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));
		return buscarPorCriteria(filtro);
	}

}
