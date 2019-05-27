package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.PagosTupa;
import com.sige.entidad.RequisitoTupa;
import com.sige.entidad.TipoTramite;
import com.sige.entidad.Tupa;
import com.sige.repositorio.BaseRepositorio;
import com.sige.repositorio.PagosTupaRepositorio;
import com.sige.repositorio.RequisitoTupaRepositorio;
import com.sige.repositorio.TipoTramiteRepositorio;
import com.sige.repositorio.TupaRepositorio;
import com.sige.servicio.TupaService;
import com.sige.util.Criterio;

@Service
public class TupaServiceImpl extends BaseServicioImpl<Tupa, Long> implements
		TupaService {

	@Autowired
	private TupaRepositorio tupaRepositorio;

	@Autowired
	private RequisitoTupaRepositorio requisitoTupaRepositorio;

	@Autowired
	private TipoTramiteRepositorio tipoTramiteRepositorio;

	@Autowired
	private PagosTupaRepositorio pagoTupaRepositorio;

	@Autowired
	public TupaServiceImpl(TupaRepositorio tupaRepositorio) {
		super(tupaRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GuardarTupa(TipoTramite tipoTramite, Tupa tupa,
			List<RequisitoTupa> requisitoTupa, List<PagosTupa> lsPagosTupa) {
		tipoTramiteRepositorio.crear(tipoTramite);
		tupaRepositorio.crear(tupa);
		for (RequisitoTupa rt : requisitoTupa) {
			requisitoTupaRepositorio.crear(rt);
		}
		for (PagosTupa pt : lsPagosTupa) {
			pagoTupaRepositorio.crear(pt);
		}

	}

	@Override
	public Tupa obtenerTupaPorTramite(String codTramite) {
		Criterio filtro = Criterio.forClass(Tupa.class);
		filtro.add(Restrictions.eq("tipoTramite.codigoTipoTramite", codTramite));
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		List<Tupa> lsTupa = buscarPorCriteria(filtro);
		if (lsTupa.size() > 0) {
			return lsTupa.get(0);
		}
		return null;
	}

	@Override
	public void ActualizarTupa(TipoTramite tipoTramite, Tupa tupa,
			List<RequisitoTupa> requisitoTupa, List<PagosTupa> lsPagoTupa) {
		tipoTramiteRepositorio.actualizar(tipoTramite);
		tupaRepositorio.actualizar(tupa);
		for (RequisitoTupa rt : requisitoTupa) {
			requisitoTupaRepositorio.actualizar(rt);
		}
		for (PagosTupa pt : lsPagoTupa) {
			pagoTupaRepositorio.actualizar(pt);
		}
	}

}
