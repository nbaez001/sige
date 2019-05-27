package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Dependencia;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteDocumento;
import com.sige.repositorio.ExpedienteDocumentoRepositorio;
import com.sige.servicio.ExpedienteDocumentoService;
import com.sige.util.Criterio;

@Service
public class ExpedienteDocumentoServiceImpl extends
		BaseServicioImpl<ExpedienteDocumento, Long> implements
		ExpedienteDocumentoService {

	@Autowired
	protected ExpedienteDocumentoServiceImpl(
			ExpedienteDocumentoRepositorio expedienteDocumentoRepositorio) {
		super(expedienteDocumentoRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ExpedienteDocumento> obtenerTodos(Dependencia dependencia,
			Expediente expediente) {
		Criterio filtro = Criterio.forClass(ExpedienteDocumento.class);

		if (dependencia == null) {
			filtro.setFetchMode("dependencia", FetchMode.EAGER);
		} else {
			filtro.add(Restrictions.eq("dependencia.id", dependencia.getId()));
		}
		filtro.createCriteria("expediente").add(
				Restrictions.eq("id", expediente.getId()));
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));

		return buscarPorCriteria(filtro);
	}

}
