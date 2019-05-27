package com.sige.repositorio;

import java.util.List;

import com.sige.util.Criterio;

public interface BaseRepositorio<Entidad, TipoLlave> {
	Entidad obtener(TipoLlave id);

	void actualizar(Entidad object);

	void crear(Entidad object);

	void grabarTodos(List<Entidad> list);

	List<Entidad> obtenerTodos();

	List<Entidad> buscarPorCriteria(Criterio filtro);

	Long cantidadPorCriteria(Criterio filtro);

}