package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Usuario;
import com.sige.util.Busqueda;

public interface UsuarioServicio extends BaseServicio<Usuario, Long> {

	public List<Usuario> buscarPorUsuario(Usuario usuario);

	public Busqueda buscarPorUsuario(Usuario usuario, Long paginaActual);

	public void eliminiarUsuario(Usuario usuario);

	public Usuario iniciarSession(Usuario usuario);

	public String getUsuario(Long usuarioId);

	public Boolean validarDuplicado(Usuario usuario);
}