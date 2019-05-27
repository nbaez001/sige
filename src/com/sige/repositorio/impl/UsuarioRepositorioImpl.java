package com.sige.repositorio.impl;

import org.springframework.stereotype.Repository;

import com.sige.entidad.Usuario;
import com.sige.repositorio.UsuarioRepositorio;

@Repository
public class UsuarioRepositorioImpl extends BaseRepositorioImpl<Usuario, Long>
		implements UsuarioRepositorio {

}