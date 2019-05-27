package com.sige.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.entidad.Menu;
import com.sige.repositorio.MenuRepositorio;
import com.sige.servicio.MenuServicio;

@Service
public class MenuServicioImpl extends BaseServicioImpl<Menu, Long> implements
		MenuServicio {

	@Autowired
	public MenuServicioImpl(MenuRepositorio menuRepositorio) {
		super(menuRepositorio);
	}

}
