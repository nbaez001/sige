package com.sige.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	
	static {
		try {
			Properties prop = new Properties();

			prop.setProperty("hibernate.connection.url",
					"jdbc:sqlserver://localhost:1433;databaseName=sige");

			// You can use any database you want, I had it configured for
			// Postgres
			prop.setProperty("hibernate.dialect",
					"org.hibernate.dialect.SQLServerDialect");
			prop.setProperty("hibernate.connection.username", "sa");
			prop.setProperty("hibernate.connection.password", "1234");
			prop.setProperty("hibernate.connection.driver_class",
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// prop.setProperty("show_sql", true); If you wish to see the
			// generated sql query
			// Create the SessionFactory from standard (hibernate.cfg.xml)
			// config file.
			// sessionFactory = new
			// Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Configuration cfg=new Configuration(); 
			cfg.addAnnotatedClass(com.sige.entidad.Persona.class);
			cfg.addAnnotatedClass(com.sige.entidad.AnexoPresentado.class);
			cfg.addAnnotatedClass(com.sige.entidad.AnexoSolicitado.class);
			cfg.addAnnotatedClass(com.sige.entidad.Auditoria.class);
			cfg.addAnnotatedClass(com.sige.entidad.Dependencia.class);
			cfg.addAnnotatedClass(com.sige.entidad.DependenciaTipoTramite.class);
			cfg.addAnnotatedClass(com.sige.entidad.DependenciaTipoTramitePlantilla.class);
			cfg.addAnnotatedClass(com.sige.entidad.DiaNoHabil.class);
			cfg.addAnnotatedClass(com.sige.entidad.DocumentoInterno.class);
			cfg.addAnnotatedClass(com.sige.entidad.DocumentoInternoDestino.class);
			cfg.addAnnotatedClass(com.sige.entidad.DocumentoInternoMapeo.class);
			cfg.addAnnotatedClass(com.sige.entidad.DocumentoInternoReferen.class);
			cfg.addAnnotatedClass(com.sige.entidad.Expediente.class);
			cfg.addAnnotatedClass(com.sige.entidad.ExpedienteAtendido.class);
			cfg.addAnnotatedClass(com.sige.entidad.ExpedienteDocumento.class);
			cfg.addAnnotatedClass(com.sige.entidad.ExpedienteEscaneado.class);
			cfg.addAnnotatedClass(com.sige.entidad.ExpedienteMovimiento.class);
			cfg.addAnnotatedClass(com.sige.entidad.ExpedientePago.class);
			cfg.addAnnotatedClass(com.sige.entidad.ExpedienteRequisito.class);
			cfg.addAnnotatedClass(com.sige.entidad.Grupo.class);
			cfg.addAnnotatedClass(com.sige.entidad.GrupoMenu.class);
			cfg.addAnnotatedClass(com.sige.entidad.Lugar.class);
			cfg.addAnnotatedClass(com.sige.entidad.Menu.class);
			cfg.addAnnotatedClass(com.sige.entidad.PagosTupa.class);
			cfg.addAnnotatedClass(com.sige.entidad.PersonaDocumento.class);
			cfg.addAnnotatedClass(com.sige.entidad.RequisitoTupa.class);
			cfg.addAnnotatedClass(com.sige.entidad.TipoDocumento.class);
			cfg.addAnnotatedClass(com.sige.entidad.TipoDocumentoPersona.class);
			cfg.addAnnotatedClass(com.sige.entidad.TipoLugar.class);
			cfg.addAnnotatedClass(com.sige.entidad.TipoPersonaJuridica.class);
			cfg.addAnnotatedClass(com.sige.entidad.TipoTramite.class);
			cfg.addAnnotatedClass(com.sige.entidad.TipoVia.class);
			cfg.addAnnotatedClass(com.sige.entidad.Tupa.class);
			cfg.addAnnotatedClass(com.sige.entidad.Usuario.class);
			cfg.addAnnotatedClass(com.sige.entidad.UsuarioAcceso.class);
			cfg.addAnnotatedClass(com.sige.entidad.UsuarioDependencia.class);
			cfg.addAnnotatedClass(com.sige.entidad.Via.class);
			
			sessionFactory = cfg.addProperties(prop).buildSessionFactory();
		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
