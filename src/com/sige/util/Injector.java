package com.sige.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public abstract class Injector {

	public static void inject(CustomComponent component) {
		inject(component, getApplicationContext());
	}

	public static void inject(UI application) {
		inject(application, getApplicationContext());
	}

	public static void inject(Window window) {
		inject(window, getApplicationContext());
	}

	private static ApplicationContext getApplicationContext() {
		/*ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		return WebApplicationContextUtils
				.getRequiredWebApplicationContext(session.getServletContext());*/
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan("com.sige");
        ctx.refresh();*/
        return SpringContextConfigur.ctx;
		
	}

	private static void inject(CustomComponent component,
			ApplicationContext applicationContext) {
		AutowireCapableBeanFactory beanFactory = applicationContext
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(component,
				AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}

	private static void inject(UI application,
			ApplicationContext applicationContext) {
		AutowireCapableBeanFactory beanFactory = applicationContext
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(application,
				AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}

	private static void inject(Window window,
			ApplicationContext applicationContext) {
		AutowireCapableBeanFactory beanFactory = applicationContext
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(window,
				AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}

	public static <T> T obtenerServicio(Class<T> clase) {
		return obtener(clase, getApplicationContext());
	}

	private static <T> T obtener(Class<T> clase,
			ApplicationContext applicationContext) {
		AutowireCapableBeanFactory beanFactory = applicationContext
				.getAutowireCapableBeanFactory();
		return beanFactory.getBean(clase);
	}
}