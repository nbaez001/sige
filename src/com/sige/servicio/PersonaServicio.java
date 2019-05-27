package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Persona;
import com.sige.entidad.PersonaDocumento;
import com.sige.util.Busqueda;

public interface PersonaServicio extends BaseServicio<Persona, Long> {

	public void eliminarPersona(Persona persona);

	public Busqueda buscarPorPersona(Persona persona, Long PaginaActual);

	public Boolean validarDuplicado(Persona persona);

	public Persona obtenerPersona(String codPersona);

	public void crear(Persona persona, List<PersonaDocumento> personaDocumentos);

	public void actualizar(Persona persona,
			List<PersonaDocumento> personaDocumentos);

}