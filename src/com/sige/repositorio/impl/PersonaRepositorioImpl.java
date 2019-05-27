package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.Persona;
import com.sige.repositorio.PersonaRepositorio;

@Repository
public class PersonaRepositorioImpl extends BaseRepositorioImpl<Persona, Long>
		implements PersonaRepositorio {

}
