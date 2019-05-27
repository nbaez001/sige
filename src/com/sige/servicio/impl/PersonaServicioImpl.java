package com.sige.servicio.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Persona;
import com.sige.entidad.PersonaDocumento;
import com.sige.repositorio.PersonaDocumentoRepositorio;
import com.sige.repositorio.PersonaRepositorio;
import com.sige.servicio.PersonaServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;
import com.sige.util.SigeUtil;

@Service
public class PersonaServicioImpl extends BaseServicioImpl<Persona, Long>
		implements PersonaServicio {

	@Autowired
	PersonaRepositorio personaRepositorio;

	@Autowired
	PersonaDocumentoRepositorio personaDocumentoRespositorio;

	@Autowired
	protected PersonaServicioImpl(PersonaRepositorio personaRepositorio) {
		super(personaRepositorio);
		this.personaRepositorio = personaRepositorio;
	}

	@Override
	public void eliminarPersona(Persona persona) {
		persona.setEstado(Boolean.FALSE);
		personaRepositorio.actualizar(persona);
	}

	@Override
	public Busqueda buscarPorPersona(Persona persona, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio.forClass(Persona.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		if (persona.getLugar() != null) {
			filtro.add(Restrictions.eq("lugar.codigoLugar", persona.getLugar()
					.getCodigoLugar()));
		}
		if (persona.getVia() != null) {
			filtro.add(Restrictions.eq("via.id", persona.getVia().getId()));
		}
		filtro.add(Restrictions.ilike("nombreCompleto",
				persona.getNombreCompleto(), MatchMode.ANYWHERE));
		filtro.add(Restrictions.ilike("direccionNumero",
				persona.getDireccionNumero(), MatchMode.ANYWHERE));

		Long totalRegistros = personaRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.PERSONA == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.PERSONA);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.PERSONA + 1);
		}
		// validaciones
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.PERSONA.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.PERSONA.intValue());
		busqueda.setRegistos(personaRepositorio.buscarPorCriteria(filtro));

		return busqueda;
	}

	@Override
	public void crear(Persona persona) {
		personaRepositorio.crear(persona);
		persona.setCodigoPersona(SigeUtil.completarCeros(persona.getId() + "",
				7, 1));
		personaRepositorio.actualizar(persona);
	}

	@Override
	public Boolean validarDuplicado(Persona persona) {
		return false;
	}

	@Override
	public Persona obtenerPersona(String codPersona) {
		// TODO Auto-generated method stub
		Criterio filtro = Criterio.forClass(Persona.class);
		filtro.add(Restrictions.eq("codigoPersona", codPersona));
		List<Persona> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	private Long getUltimoId() {
		Criterio filtro = Criterio.forClass(Persona.class);
		filtro.addOrder(Order.desc("id"));
		filtro.setMaxResults(1);

		List<Persona> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return lista.get(0).getId();
		}
		return 0L;
	}

	@Override
	public void crear(Persona persona, List<PersonaDocumento> personaDocumentos) {

		persona.setCodigoPersona(SigeUtil.completarCeros((getUltimoId() + 1)
				+ "", 7, 1));
		personaRepositorio.crear(persona);
		for (PersonaDocumento pd : personaDocumentos) {
			pd.setPersona(persona);
			personaDocumentoRespositorio.crear(pd);
		}

	}

	@Override
	public void actualizar(Persona persona,
			List<PersonaDocumento> personaDocumentos) {

		for (PersonaDocumento pd : personaDocumentos) {
			pd.setPersona(persona);
			if (pd.getId() == null) {
				personaDocumentoRespositorio.crear(pd);
			} else {
				personaDocumentoRespositorio.actualizar(pd);
			}
		}
		actualizar(persona);

	}
}
