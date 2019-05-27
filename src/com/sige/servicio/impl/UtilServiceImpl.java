package com.sige.servicio.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sige.entidad.Expediente;
import com.sige.servicio.DiaNoHabilServicio;
import com.sige.servicio.ExpedienteMovimientoServicio;
import com.sige.servicio.ExpedienteServicio;
import com.sige.servicio.MailService;
import com.sige.servicio.UtilService;
import com.sige.util.ConnectionUtil;
import com.sige.util.HibernateUtil;
import com.sige.util.SigeUtil;

@Service
public class UtilServiceImpl implements UtilService {

	//@Autowired
	private DataSource dataSource;

	@Autowired
	private ExpedienteServicio expedienteServicio;

	@Autowired
	private DiaNoHabilServicio diaNoHabilServicio;

	@Autowired
	private MailService mailService;

	@Autowired
	private ExpedienteMovimientoServicio expedienteMovimientoServicio;

	@Value("${mail.expedientesPlazoAtencionVencido.to}")
	private String mailExpedientesPlazoAtencionVencidoTo;

	@Value("${mail.expedientesVencidos.to}")
	private String mailExpedientesVencidosTo;

	//@Autowired
	private SessionFactory sessionFactory;

	public UtilServiceImpl() {
		super();
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public Map<String, Integer> getLengthColumns(String tableName) {
		Connection connection = null;
		Map<String, Integer> columnLengthsMap = new HashMap<String, Integer>();
		try {
			connection = new ConnectionUtil().getConexion();

			DatabaseMetaData metadata = connection.getMetaData();
			ResultSet resultSet = metadata.getColumns(null, null, tableName,
					null);
			while (resultSet.next()) {
				columnLengthsMap.put(resultSet.getString("COLUMN_NAME"),
						resultSet.getInt("COLUMN_SIZE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return columnLengthsMap;
	}

	@Override
	public List<String> getAllTables() {
		List<String> tableNames = new ArrayList<>();
		Map<String, ClassMetadata> map = (Map<String, ClassMetadata>) sessionFactory
				.getAllClassMetadata();
		for (String entityName : map.keySet()) {
			SessionFactoryImpl sfImpl = (SessionFactoryImpl) sessionFactory;
			String tableName = ((AbstractEntityPersister) sfImpl
					.getEntityPersister(entityName)).getTableName();
			tableNames.add(tableName);
		}
		return tableNames;
	}

	@Scheduled(cron = "0 55 23 * * ?")
	public void archivarExpedientesVencidos() {
		List<Expediente> expedientesArchivadosProvicionalmente = expedienteServicio
				.obtenerExpedientesArchivadosProvicionalmente();
		ArrayList<Expediente> documentosVencidos = new ArrayList<Expediente>();
		Date fechaActual = new Date();
		Date fechaExpediente;
		Date fechaVencimiento;
		for (Expediente expediente : expedientesArchivadosProvicionalmente) {
			fechaExpediente = new Date(expediente.getFechaExpediente()
					.getTime());
			fechaVencimiento = SigeUtil.sumarDiasFecha(
					fechaExpediente,
					diaNoHabilServicio.getCantidadDiasNoHabiles(
							fechaExpediente, fechaActual).intValue()
							+ diaNoHabilServicio.getDiasPlazoVencimiento());
			if (SigeUtil.obtenerFechaFormato(fechaVencimiento.toString(), 0)
					.compareTo(
							SigeUtil.obtenerFechaFormato(
									fechaActual.toString(), 0)) == 0
					|| fechaActual.compareTo(fechaVencimiento) > 0) {
				expediente.setTextoFinaliza(SigeUtil
						.obtenerFormatoFecha(expediente.getFechaCreacion()));
				documentosVencidos.add(expediente);
			}
		}
		if (documentosVencidos.size() > 0) {
			mailService
					.sendMail(
							mailExpedientesVencidosTo,
							"Sistema Integrado de Gestión de Expedientes-Expedientes en Archivo Provicional Vencidos-"
									+ SigeUtil
											.obtenerFormatoFecha(new Timestamp(
													fechaActual.getTime())),
							documentosVencidos, Boolean.TRUE);
		}

		expedientesArchivadosProvicionalmente
				.removeAll(expedientesArchivadosProvicionalmente);
		documentosVencidos.removeAll(documentosVencidos);
		expedientesArchivadosProvicionalmente = expedienteServicio
				.obtenerExpedientesPendientesAtencion();
		for (Expediente expediente : expedientesArchivadosProvicionalmente) {
			fechaExpediente = new Date(expediente.getFechaExpediente()
					.getTime());
			fechaVencimiento = SigeUtil.sumarDiasFecha(
					fechaExpediente,
					diaNoHabilServicio.getCantidadDiasNoHabiles(
							fechaExpediente, fechaActual).intValue()
							+ expediente.getTipoTramite().getTipoAten());
			if (SigeUtil.obtenerFechaFormato(fechaVencimiento.toString(), 0)
					.compareTo(
							SigeUtil.obtenerFechaFormato(
									fechaActual.toString(), 0)) == 0
					|| fechaActual.compareTo(fechaVencimiento) > 0) {
				expediente.setTextoFinaliza(SigeUtil
						.obtenerFormatoFecha(expediente.getFechaCreacion()));
				expediente.setObservaciones(expedienteMovimientoServicio
						.obtenerExpedienteMovimiento(expediente.getCodigo())
						.getDependencia().getNombre());
				documentosVencidos.add(expediente);
			}
		}
		if (documentosVencidos.size() > 0) {
			mailService
					.sendMail(
							mailExpedientesPlazoAtencionVencidoTo,
							"Sistema Integrado de Gestión de Expedientes-Expedientes con plazo de atención Vencido-"
									+ SigeUtil
											.obtenerFormatoFecha(new Timestamp(
													fechaActual.getTime())),
							documentosVencidos, Boolean.FALSE);
		}

	}

}
