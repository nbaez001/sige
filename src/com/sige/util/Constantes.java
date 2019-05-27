package com.sige.util;

public class Constantes {
	public static String NOMBRE_EMPRESA = "";

	public static abstract class PAGINACION {
		public static Long USUARIO = 10L;
		public static Long SISTEMA = 10L;
		public static Long TIPOTRAMITE = 10L;
		public static Long GRUPO = 10L;
		public static Long DEPENDENCIA = 10L;
		public static Long TIPOVIA = 10L;
		public static Long VIA = 10L;
		public static Long PERSONA = 10L;
		public static Long LUGAR = 10L;
		public static Long TIPOPERSONAJURIDICA = 10L;
		public static Long EXPEDIENTE = 10L;
		public static Long DOCUMENTOINTERNO = 10L;
		public static Long EXPEDIENTE_CONSULTA = 10L;
		public static Long TIPODOCUMENTOPERSONA = 10L;
		public static Long DEPENDENCIA_TIPO_TRAMITE_PLANTILLA = 10L;
		public static Long AUDITORIA = 10L;

	}

	public static abstract class FILE_LOCATION {
		public static String FILE_LOCATION_PLANTILLAS = "/opt/aplicaciones/Tramite-Documentario/plantillas/";
		public static String FILE_LOCATION_DOCUMENTOS = "/opt/aplicaciones/Tramite-Documentario/documentos/";
		public static String FILE_LOCATION_EXPEDIENTES_ESCANEADOS = "/opt/aplicaciones/Tramite-Documentario/expedientesEscaneados/";
	}

	public static abstract class TIPO_OPERACION {
		public static Integer MODIFICACION = 1;
		public static Integer ELIMINACION = 2;
	}

	public static abstract class SESION {
		public final static String USUARIO = "SESION_USUARIO";
		public final static String USUARIO_CODIGO = "SESION_USUARIO_CODIGO";
	}

	public static abstract class OPCION {
		public final static String MANTENIMIENTO_PERMISOS = "frmPermisos";
		public final static String MANTENIMIENTO_USUARIOS = "frmUsuarios";
		public final static String MANTENIMIENTO_TUPA = "frmTupa";
		public final static String MANTENIMIENTO_GRUPOS = "frmGrupos";
		public final static String MANTENIMIENTO_TIPOVIA = "frmTipoVia";
		public final static String MANTENIMIENTO_DEPENDENCIAS = "frmDependencias";
		public final static String MANTENIMIENTO_TIPODOCUMENTO = "frmTipoDocumento";
		public final static String MANTENIMIENTO_TIPOLUGAR = "frmTipoLugar";
		public final static String MANTENIMIENTO_VIAS = "frmVia";
		public final static String MANTENIMIENTO_PERSONAS = "frmPersonas";
		public final static String MANTENIMIENTO_LUGARES = "frmLugares";
		public final static String PRCESOS_EXPEDIENTES = "frmExpediente";
		public final static String MANTENIMIENTO_DIANOHABIL = "frmDiaNoHabil";
		public final static String MANTENIMIENTO_TIPOPERSONAJURIDICA = "frmTipoPersonaJuridica";
		public final static String PROCESO_MOVIMIENTO_EXPEDIENTE = "frmMovimientoExpediente";
		public final static String GRAFICO_MOVIMIENTOS = "frmGrfMovimientos";
		public final static String MANTENIMIENTO_PLANTILLAS = "frmPlantillas";
		public final static String PROCESO_ANEXAR_EXPEDIENTE = "frmAnexarExpediente";
		public final static String CONSULTAR_EXPEDIENTES = "frmConsultarExpediente";
		public final static String CONSULTAR_EXPEDIENTES_ESTADISTICA = "frmConsultarExpedienteEstadistica";
		public final static String PROCESO_IMPRIMIR_CARGOS = "frmImpresionCargos";
		public final static String AYUDA_ACERCA_DE = "frmAcercaDe";
		public final static String MANTENIMIENTO_TIPODOCUMENTOPERSONA = "frmTipoDocumentoPersona";
		public final static String CONSULTAR_AUDITORIA = "frmConsultaAuditoria";
		public final static String CONSULTAR_TRAZABILIDAD = "frmConsultaTrazabilidad";
		public final static String CONSULTAR_TIEMPO_PROMEDIO_ATENCION_TRAMITE_X_OFICINA = "frmTiempoPromedioTramitePorOficina";
		public final static String CONSULTAR_TIEMPO_PROMEDIO_ATENCION_OFICINA_X_TRAMITE = "frmTiempoPromedioOficinaPorTramite";
		public final static String CONSULTAR_MOVIMIENTO_EXPEDIENTES = "frmConsultarMovimientoExpedientes";
		public final static String PROCESO_IMPRIMIR_CARGO_EXPEDIENTE = "frmImprimirCargoExpediente";
	}

	public static abstract class OPCION_MOSTRAR {
		public final static Integer MOSTRAR_TODOS = 1;
		public final static Integer MOSTRAR_FINALIZADOS = 2;
		public final static Integer MOSTRAR_EN_PROCESO = 3;
		public final static Integer MOSTRAR_VENCIDOS_EN_PROCESO = 4;
		public final static Integer MOSTRAR_VENCIDOS_EN_ESPERA = 5;

	}

	public static abstract class TIPO_BUSQUEDA {
		public final static Integer TIEMPO_ATENCION = 1;
		public final static Integer OTRAS = 2;
	}

	public static abstract class MENSAJE {
		public final static Integer TYPE_ERROR = 3;
		public final static Integer TYPE_WARNING = 2;
		public final static Integer TYPE_SUCCES = 1;
	}

	public static abstract class TIPO_ESTADISTICA {
		public final static Integer PROCEDIMIENTO_TUPA = 1;
	}
}
