package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.Lugar;
import com.sige.repositorio.LugarRepositorio;

@Repository
public class LugarRepositorioImpl extends BaseRepositorioImpl<Lugar, Long>
		implements LugarRepositorio {

}
