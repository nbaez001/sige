package com.sige.servicio.impl;

import java.util.List;

import javax.persistence.criteria.Join;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.RequisitoTupa;
import com.sige.repositorio.RequisitoTupaRepositorio;
import com.sige.servicio.RequisitoTupaService;
import com.sige.util.Criterio;

@Service
public class RequisitoTupaServiceImpl extends
		BaseServicioImpl<RequisitoTupa, Long> implements RequisitoTupaService {

	@Autowired
	private RequisitoTupaRepositorio requisitoTupaRepositorio;

	@Autowired
	public RequisitoTupaServiceImpl(
			RequisitoTupaRepositorio requisitoTupaReposotiroio) {
		super(requisitoTupaReposotiroio);
	}

	public Long obtenerCantidadRequisitosPorTramite(String codTipoTramite) {
		Criterio filtro = Criterio.forClass(RequisitoTupa.class).add(
				Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("tupa").createCriteria("tipoTramite")
				.add(Restrictions.eq("codigoTipoTramite", codTipoTramite));
		// .add(Restrictions.eq("tupa.tipoTramite.codigoTipoTramite",
		// codTipoTramite))
		// .add(Restrictions.eq("estado", Boolean.TRUE));
		// .createCriteria("tupa").createCriteria("tipoTramite")
		// .add(Restrictions.eq("codigoTipoTramite", codTipoTramite));
		return requisitoTupaRepositorio.cantidadPorCriteria(filtro);

	}

	@Override
	public List<RequisitoTupa> obtenerRequisitosPorTramite(String codTipoTramite) {
		Criterio filtro = Criterio.forClass(RequisitoTupa.class);
		filtro.add(
				Restrictions.eq("tupa.tipoTramite.codigoTipoTramite",
						codTipoTramite)).add(
				Restrictions.eq("estado", Boolean.TRUE));
		return buscarPorCriteria(filtro);
	}

}
