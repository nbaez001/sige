package com.sige.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sige.interceptor.AuditarInterceptor;
import com.sige.interceptor.impl.AuditarInterceptorImpl;


@Aspect
public class NewAspect {

	private final static String METODO_CREAR = "crear";
	private final static String METODO_ACTUALIZAR = "actualizar";
	private final static String METODO_GRABAR_TODOS = "grabarTodos";
	
	@Before("execution(* com.sige.repositorio.impl.*.*(..))")
	public void before(JoinPoint jp) throws Throwable {
		if (validarInvocacion(jp)) {
			String usuario = SigeUtil.obtenerUsuarioSesion();
			Object parametro = jp.getArgs()[0];
			String name= jp.getSignature().getName();
			Date date = new Date();
			Timestamp fechaHora = new Timestamp(date.getTime());
			if (METODO_CREAR.equals(name) || METODO_ACTUALIZAR.equals(name)) {
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
			if (METODO_GRABAR_TODOS.equals(name)) {
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
	}

	private boolean validarInvocacion(JoinPoint jp) {
		if (jp.getArgs().length == 1) {
			Object parametro = jp.getArgs()[0];
			String name= jp.getSignature().getName();
			return (parametro instanceof AuditoriaBean || parametro instanceof List<?>) && METODO_CREAR.equals(name)|| METODO_ACTUALIZAR.equals(name)|| METODO_GRABAR_TODOS.equals(name);
		}
		return false;
	}
}
