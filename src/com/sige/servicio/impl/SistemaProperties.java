package com.sige.servicio.impl;

public class SistemaProperties {
	static String systemDataSource = "java:jboss/datasources/sistradoDS";
	static String Dialect = "org.hibernate.dialect.SQLServer2012Dialect";
	static String distrito = "081301000";
	static String departamento = "080000000";
	static String provincia = "081300000";
	static Long mesaPartesCodigo = 1l;
	static Integer vencimientoExpedienteDiasPlazo = 2;
	static String mailExpedientesVencidosTo = "angelswg1@gmail.com";
	static String mailExpedientesPlazoAtencionVencidoTo = "angelswg1@gmail.com";
	static String mailHost = "smtp.gmail.com";
	static int mailPort = 25;
	static String mailUsername = "angel.gutierrez.swgoodidea@gmail.com";
	static String mailPassword = "";
}
