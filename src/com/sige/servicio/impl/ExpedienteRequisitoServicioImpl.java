package com.sige.servicio.impl;

import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.ExpedienteRequisito;
import com.sige.repositorio.ExpedienteRequisitoRepositorio;
import com.sige.servicio.ExpedienteRequisitoServicio;
import com.sige.util.Criterio;

@Service
public class ExpedienteRequisitoServicioImpl extends
		BaseServicioImpl<ExpedienteRequisito, Long> implements
		ExpedienteRequisitoServicio {

	@Autowired
	private ExpedienteRequisitoRepositorio expedienteRequisitoRepositorio;

	@Autowired
	protected ExpedienteRequisitoServicioImpl(
			ExpedienteRequisitoRepositorio expedienteRequisitoRepositorio) {
		super(expedienteRequisitoRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer obtenerUltimoCorrelativo() {
		Criterio filtro = Criterio.forClass(ExpedienteRequisito.class);
		filtro.addOrder(Order.desc("correlativo"));
		List<ExpedienteRequisito> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0).getCorrelativo() + 1;
		}
		return new Integer(1);
	}

	@Override
	public Long obtenerCantidadRequisitosPresentados(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteRequisito.class);
		filtro.add(Restrictions.eq("presenta", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));
		return expedienteRequisitoRepositorio.cantidadPorCriteria(filtro);
	}

	@Override
	public Long obtenerCantidadRequisitosNoPresentados(String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteRequisito.class);
		filtro.add(Restrictions.eq("presenta", Boolean.FALSE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));
		return expedienteRequisitoRepositorio.cantidadPorCriteria(filtro);
	}

	@Override
	public List<ExpedienteRequisito> obtenerExpedienteRequisito(
			String codExpediente) {
		Criterio filtro = Criterio.forClass(ExpedienteRequisito.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("expediente").add(
				Restrictions.eq("codigo", codExpediente));
		return buscarPorCriteria(filtro);
	}

}
