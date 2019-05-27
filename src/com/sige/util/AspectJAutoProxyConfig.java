package com.sige.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJAutoProxyConfig {
		
	  @Bean	
	  public NewAspect newAspect(){
		 return new NewAspect();
	  }
}
