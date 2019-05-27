package com.sige.repositorio.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

import com.sige.repositorio.BaseRepositorio;
import com.sige.util.Criterio;
import com.sige.util.HibernateUtil;

public class BaseRepositorioImpl<Entidad extends Serializable, TipoLlave extends Serializable>
		implements BaseRepositorio<Entidad, TipoLlave> {

	protected static final Log logger = LogFactory
			.getLog(HibernateTransactionManager.class);

	//@Autowired
	protected SessionFactory sessionFactory;

	protected Class<Entidad> domainClass;

	@SuppressWarnings("unchecked")
	public BaseRepositorioImpl() {
		super();
		this.domainClass = (Class<Entidad>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.sessionFactory=HibernateUtil.getSessionFactory();
	}

	/*public Session getCurrentSession() {
		//return sessionFactory.getCurrentSession();
		return sessionFactory.openSession();
	}*/

	@SuppressWarnings("unchecked")
	public Entidad obtener(TipoLlave id) {
		//return (Entidad) this.sessionFactory.getCurrentSession().get(domainClass, id);
		//return (Entidad) getCurrentSession().get(domainClass, id);
		//ADDED BY NERIO
		Session sesion = sessionFactory.openSession();
        sesion.beginTransaction().commit();

        Entidad e= (Entidad) sesion.get(domainClass, id);
        
        sesion.close();

        return e;
	}

	public void actualizar(Entidad object) {
		Session sesion = sessionFactory.openSession();
        Transaction tx = sesion.beginTransaction();

        try {
            sesion.clear();
            sesion.update(object);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            sesion.close();
        }
	}

	public void crear(Entidad object) {
		//getCurrentSession().save(object);
		Session sesion = sessionFactory.openSession();
        Transaction tx = sesion.beginTransaction();

        try {
            sesion.save(object);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            sesion.close();
        }
	}

	public void grabarTodos(List<Entidad> list) {
		Session sesion = sessionFactory.openSession();
        Transaction tx = sesion.beginTransaction();

        try {
        	for (Entidad entidad : list) {
        		sesion.save(entidad);
    		}
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            sesion.close();
        }
	}

	@SuppressWarnings("unchecked")
	public List<Entidad> buscarPorCriteria(Criterio filtro) {
		Session sesion = sessionFactory.openSession();
        sesion.beginTransaction().commit();

        Criteria busqueda = filtro.getExecutableCriteria(sesion);
		busqueda.setProjection(null);
		busqueda.setFirstResult(filtro.getFirstResult());
		
        List lista= busqueda.list();

        sesion.close();

        return lista;
	}

	@SuppressWarnings("unchecked")
	public Long cantidadPorCriteria(Criterio filtro) {
		Session sesion = sessionFactory.openSession();
        sesion.beginTransaction().commit();

        Criteria busqueda = filtro.getExecutableCriteria(sesion);
		busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
		busqueda.setProjection(Projections.rowCount());
		List<Long> a = (List<Long>) busqueda.list();

        sesion.close();

        return a.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Entidad> obtenerTodos() {
		Session sesion = sessionFactory.openSession();
        sesion.beginTransaction().commit();

        Criterio filtro = Criterio.forClass(domainClass);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Criteria busqueda = filtro.getExecutableCriteria(sesion);
		busqueda.setProjection(null);
		busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
		
		
        List lista = (List<Entidad>) busqueda.list();

        sesion.close();

        return lista;
	}

}