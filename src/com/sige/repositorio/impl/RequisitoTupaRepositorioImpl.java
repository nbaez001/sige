package com.sige.repositorio.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sige.entidad.RequisitoTupa;
import com.sige.repositorio.RequisitoTupaRepositorio;
import com.sige.util.HibernateUtil;

@Repository
public class RequisitoTupaRepositorioImpl extends
		BaseRepositorioImpl<RequisitoTupa, Long> implements
		RequisitoTupaRepositorio {

	//@Autowired
	protected SessionFactory sessionFactory;
	
	
	
	public RequisitoTupaRepositorioImpl() {
		super();
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
