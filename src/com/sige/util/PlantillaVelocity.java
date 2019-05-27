package com.sige.util;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class PlantillaVelocity implements Serializable {

	private static final long serialVersionUID = 1L;
	private VelocityEngine velocityEngine;

	public PlantillaVelocity() {
		velocityEngine = new VelocityEngine();
		//velocityEngine.setProperty("resource.loader", "class");
		//velocityEngine.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		/*velocityEngine.setProperty("resource.loader", "webapp");
		velocityEngine.setProperty("webapp.resource.loader.class","org.apache.velocity.tools.view.WebappResourceLoader");
		velocityEngine.setProperty("webapp.resource.loader.path","/WEB-INF/velocity/");*/
		
		//velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		//velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		Properties p = new Properties();
		p.setProperty("resource.loader","file");
		p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		p.setProperty("file.resource.loader.path", "C:/opt/aplicaciones/Tramite-Documentario/");           
		p.setProperty("file.resource.loader.cache", "false");
		p.setProperty("file.resource.loader.modificationCheckInterval", "0");
		
		velocityEngine.init(p);
	}

	public String enviarFormato(Map<String, Object> hashmap, String plantilla) {
		Template template = null;

		VelocityContext context = new VelocityContext();

		@SuppressWarnings("rawtypes")
		Iterator iterator = hashmap.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			context.put(mapEntry.getKey().toString(), mapEntry.getValue());
		}
		template = velocityEngine.getTemplate(plantilla);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}
}