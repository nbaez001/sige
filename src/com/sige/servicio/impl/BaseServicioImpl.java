package com.sige.servicio.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sige.repositorio.BaseRepositorio;
import com.sige.servicio.BaseServicio;
import com.sige.util.Criterio;

@Transactional
public abstract class BaseServicioImpl<Entidad, TipoLlave> implements
		BaseServicio<Entidad, TipoLlave> {

	protected static final Log logger = LogFactory
			.getLog(BaseServicioImpl.class);
	private BaseRepositorio<Entidad, TipoLlave> baseRepositorio;
	@SuppressWarnings("unused")
	private Class<Entidad> domainClass = null;

	@SuppressWarnings("unchecked")
	protected BaseServicioImpl(BaseRepositorio<Entidad, TipoLlave> baseHibernate) {
		this.baseRepositorio = baseHibernate;
		this.domainClass = (Class<Entidad>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Entidad obtener(TipoLlave id) {
		return this.baseRepositorio.obtener(id);
	}

	public void actualizar(Entidad object) {
		this.baseRepositorio.actualizar(object);
	}

	public void crear(Entidad object) {
		this.baseRepositorio.crear(object);
	}

	public void grabarTodos(List<Entidad> list) {
		this.baseRepositorio.grabarTodos(list);
	}

	public List<Entidad> obtenerTodos() {
		return this.baseRepositorio.obtenerTodos();
	}

	public List<Entidad> buscarPorCriteria(Criterio filtro) {
		return this.baseRepositorio.buscarPorCriteria(filtro);
	}

	// public Long cantidadPorCriteria(Criterio filtro){
	// return this.cantidadPorCriteria(filtro);
	// }
}