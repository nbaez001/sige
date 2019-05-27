package com.sige.interceptor.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sige.interceptor.AuditarInterceptor;
import com.sige.util.AuditoriaBean;
import com.sige.util.SigeUtil;


public class AuditarInterceptorImpl implements AuditarInterceptor, Serializable {

	private static final long serialVersionUID = -2269463476088223892L;
	private final static String METODO_CREAR = "crear";
	private final static String METODO_ACTUALIZAR = "actualizar";
	private final static String METODO_GRABAR_TODOS = "grabarTodos";
	
	@SuppressWarnings({ "rawtypes" })
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("LLEGUE NERIO");
		if (validarInvocacion(invocation)) {
			String usuario = SigeUtil.obtenerUsuarioSesion();
			Object parametro = invocation.getArguments()[0];
			Date date = new Date();
			Timestamp fechaHora = new Timestamp(date.getTime());
			if (METODO_CREAR.equals(invocation.getMethod().getName()) || METODO_ACTUALIZAR.equals(invocation.getMethod().getName())) {
				AuditoriaBean auditoriaBean = ((AuditoriaBean) parametro);
				if (auditoriaBean.esNuevo()) {
					auditoriaBean.setCreadoPor(usuario);
					auditoriaBean.setFechaCreacion(fechaHora);
					auditoriaBean.setCreadoPorip(SigeUtil.obtenerIpUsuairo());
					auditoriaBean.setCreadoPorhostName(SigeUtil.obtenerHostNameUsuario());
					auditoriaBean.setEstado(Boolean.TRUE);
				}
				auditoriaBean.setModificadoPor(usuario);
				auditoriaBean.setFechaModificacion(fechaHora);
				auditoriaBean.setModificadoPorip(SigeUtil.obtenerIpUsuairo());
				auditoriaBean.setModificadoPorhostName(SigeUtil.obtenerHostNameUsuario());
			}
			if (METODO_GRABAR_TODOS.equals(invocation.getMethod().getName())) {
				List auditoriaBeans = (List) parametro;
				for (Object obj : auditoriaBeans) {
					AuditoriaBean auditoriaBean;
					if (obj instanceof AuditoriaBean) {
						auditoriaBean = (AuditoriaBean) obj;
					} else {
						continue;
					}
					if (auditoriaBean.esNuevo()) {
						auditoriaBean.setCreadoPor(usuario);
						auditoriaBean.setFechaCreacion(fechaHora);
						auditoriaBean.setEstado(Boolean.TRUE);
						auditoriaBean.setCreadoPorip(SigeUtil
								.obtenerIpUsuairo());
						auditoriaBean.setCreadoPorhostName(SigeUtil
								.obtenerHostNameUsuario());

					}
					auditoriaBean.setModificadoPor(usuario);
					auditoriaBean.setFechaModificacion(fechaHora);
					auditoriaBean.setModificadoPorip(SigeUtil
							.obtenerIpUsuairo());
					auditoriaBean.setModificadoPorhostName(SigeUtil
							.obtenerHostNameUsuario());
				}
			}
		}
		return invocation.proceed();
	}

	private boolean validarInvocacion(MethodInvocation invocation) {
		if (invocation.getArguments().length == 1) {
			Object parametro = invocation.getArguments()[0];
			return (parametro instanceof AuditoriaBean || parametro instanceof List<?>)
					&& METODO_CREAR.equals(invocation.getMethod().getName())
					|| METODO_ACTUALIZAR.equals(invocation.getMethod()
							.getName())
					|| METODO_GRABAR_TODOS.equals(invocation.getMethod()
							.getName());
		}
		return false;
	}
}