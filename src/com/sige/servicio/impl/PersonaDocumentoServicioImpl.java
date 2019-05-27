package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.PersonaDocumento;
import com.sige.entidad.TipoDocumento;
import com.sige.repositorio.PersonaDocumentoRepositorio;
import com.sige.servicio.PersonaDocumentoServicio;
import com.sige.util.Criterio;

@Service
public class PersonaDocumentoServicioImpl extends
		BaseServicioImpl<PersonaDocumento, Long> implements
		PersonaDocumentoServicio {

	@Autowired
	protected PersonaDocumentoServicioImpl(
			PersonaDocumentoRepositorio personaDocumentoRepositorio) {
		super(personaDocumentoRepositorio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PersonaDocumento> obtenerPorCodigoPersona(String codigoPersona) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(PersonaDocumento.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.createCriteria("persona").add(
				Restrictions.eq("codigoPersona", codigoPersona));

		filtro.setFetchMode("tipoDocumentoPersona", FetchMode.EAGER);
		filtro.setFetchMode("persona", FetchMode.EAGER);
		List<PersonaDocumento> personaDocumentos = buscarPorCriteria(filtro);
		return personaDocumentos;
	}

}
