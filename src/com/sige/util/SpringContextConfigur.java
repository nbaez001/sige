package com.sige.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextConfigur {

	static AnnotationConfigApplicationContext ctx;
	
	public SpringContextConfigur() {
		ctx= new AnnotationConfigApplicationContext();
		ctx.scan("com.sige");
		ctx.refresh();
	}
	
	public ApplicationContext getApplicationContext(){
		return ctx;
	}
	
}
