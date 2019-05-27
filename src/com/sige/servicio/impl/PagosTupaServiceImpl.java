package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.PagosTupa;
import com.sige.repositorio.PagosTupaRepositorio;
import com.sige.servicio.PagosTupaService;
import com.sige.util.Criterio;

@Service
public class PagosTupaServiceImpl extends BaseServicioImpl<PagosTupa, Long>
		implements PagosTupaService {

	@Autowired
	private PagosTupaRepositorio pagoTupaRepositorio;

	@Autowired
	public PagosTupaServiceImpl(PagosTupaRepositorio pagoTupaRepositorio) {
		super(pagoTupaRepositorio);
	}

	public Long obtenerCantidadPagosPorTramite(String codTramite) {
		Criterio filtro = Criterio.forClass(PagosTupa.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE)).add(
				Restrictions.eq("tupaPago.tipoTramite.codigoTipoTramite",
						codTramite));
		return pagoTupaRepositorio.cantidadPorCriteria(filtro);

	}

	@Override
	public List<PagosTupa> obtenerPagosPorTramite(String codTramite) {
		Criterio filtro = Criterio.forClass(PagosTupa.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE))
				.add(Restrictions.eq("tupaPago.tipoTramite.codigoTipoTramite",
						codTramite)).addOrder(Order.asc("item"));
		return pagoTupaRepositorio.buscarPorCriteria(filtro);
	}
}
