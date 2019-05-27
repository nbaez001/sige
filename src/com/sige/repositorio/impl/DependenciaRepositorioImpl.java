package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.Dependencia;
import com.sige.repositorio.DependenciaRepositorio;

@Repository
public class DependenciaRepositorioImpl extends
		BaseRepositorioImpl<Dependencia, Long> implements
		DependenciaRepositorio {

}
