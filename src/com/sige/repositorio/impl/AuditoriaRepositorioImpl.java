package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.Auditoria;
import com.sige.repositorio.AuditoriaRepositorio;

@Repository
public class AuditoriaRepositorioImpl extends
		BaseRepositorioImpl<Auditoria, Integer> implements AuditoriaRepositorio {

}
