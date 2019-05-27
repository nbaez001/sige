package com.sige.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class SigeUtil {

	static Logger logger = Logger.getLogger(SigeUtil.class);
	private static final String dateFormat = "yyyy-MM-dd";

	public static String getDiasTranscurridos(Timestamp fechaExpediente) {
		return ((new Date().getTime()) - fechaExpediente.getTime())
				/ (24 * 60 * 60 * 1000) + "";
	}

	public static String getTiempoTranscurrido(Timestamp fechaExpediente,
			Timestamp fechaFinalizacion) {
		Long totalHoras = (fechaFinalizacion.getTime() - fechaExpediente
				.getTime()) / (60 * 60 * 1000);
		return (totalHoras / 24) + " Dia(s) y " + (totalHoras % 24)
				+ " Hora(s) ";
	}

	public static String getDiasTranscurridos(Timestamp desde, Timestamp hasta) {
		return ((hasta.getTime()) - desde.getTime()) / (24 * 60 * 60 * 1000)
				+ "";
	}

	public static String obtenerUsuarioSesion() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		String nick = null;
		if (session != null) {
			nick = (String) session.getAttribute(Constantes.SESION.USUARIO);
		}
		logger.info("nick=" + nick);
		return nick;
	}

	public static Long obtenerCodigoUsuarioSesion() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		Long id = null;
		if (session != null) {
			id = (Long) session.getAttribute(Constantes.SESION.USUARIO_CODIGO);
		}
		logger.info("nick=" + id);
		return id;
	}

	public static void asignarUsuarioSesion(String nick) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(Constantes.SESION.USUARIO, nick);
	}

	public static void asignarCodigoUsuarioSesion(Long id) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(Constantes.SESION.USUARIO_CODIGO, id);
	}

	public static String obtenerIpUsuairo() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request.getRemoteAddr();
	}

	public static String obtenerHostNameUsuario() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request.getRemoteHost();
	}

	public static void validarBotones(Permiso permiso, Button... botones) {
		for (Button btn : botones) {

			if (btn.getId().equals("guardar") || btn.getId().equals("cancelar")) {
				if (permiso.getNuevo() || permiso.getModificar()) {
					btn.setVisible(true);
				} else {
					btn.setVisible(false);
				}
			} else if (btn.getId().equals("eliminar")) {
				btn.setVisible(permiso.getEliminar());
			} else if (btn.getId().equals("nuevo")) {
				btn.setVisible(permiso.getNuevo());
			} else if (btn.getId().equals("imprimir")) {
				btn.setVisible(permiso.getImprimir());
			} else if (btn.getId().equals("buscar")) {
				btn.setVisible(permiso.getConsultar());
			} else if (btn.getId().equals("consultar")) {
				btn.setVisible(permiso.getConsultar());
			}

		}
	}

	public static Window generarBuscador(Window ventana, String titulo,
			String width, String height) {
		ventana.setModal(true);
		ventana.setResizable(false);
		ventana.center();
		ventana.setCaption(titulo);
		ventana.setWidth(width);
		ventana.setHeight(height);
		ventana.setScrollLeft(0);
		ventana.setScrollTop(0);
		return ventana;
	}

	public static String completarCeros(String codigo, int lenghtTotal, int tipo) {
		String respuesta = "";
		for (int i = 0; i < lenghtTotal - codigo.length(); i++) {
			respuesta += "0";
		}
		if (tipo == 1) {
			respuesta = respuesta + codigo;
		} else if (tipo == 2) {
			respuesta = codigo + respuesta;
		}
		return respuesta;
	}

	public static boolean validarCamposTexto(TextField... camposTexto) {
		for (TextField txt : camposTexto) {
			if (txt.getValue().toString().length() == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * OBTIENE LA FECHA EN BASE AL SIGUIENTE FORMATO EEE MMM d HH:mm:ss zzz yyyy
	 */

	public static Date obtenerFechaFormato(String fecha, Integer tipoFecha) {
		Date dateResult = null;
		SimpleDateFormat parserSDF = null;
		Date dateParser = null;
		String dateFormatted = "";
		try {
			if (tipoFecha == 0) {
				parserSDF = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy",
						Locale.ENGLISH);
			}
			if (tipoFecha == 1) {
				parserSDF = new SimpleDateFormat("dd/MM/yyyy");
			}

			dateParser = parserSDF.parse(fecha);
			parserSDF = new SimpleDateFormat(dateFormat);
			dateFormatted = parserSDF.format(dateParser);
			dateResult = parserSDF.parse(dateFormatted);
			return dateResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateResult;
	}

	public static Date sumarDiasFecha(Date fecha, Integer dias) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha);

		calendar.add(Calendar.DAY_OF_YEAR, dias);

		return calendar.getTime();
	}

	public static String obtenerFormatoFecha(Timestamp Fecha) {

		String fecha = null;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Fecha);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}

	public static Embedded asignarEstado(Character tipoMovimiento,
			String recepcionadoPor) {
		Embedded icono = null;
		if (tipoMovimiento.equals('0') && !recepcionadoPor.equals("")) {
			icono = new Embedded("", new ThemeResource(
					"images/botones/receive.png"));

		}
		if (tipoMovimiento.equals('0') && recepcionadoPor.equals("")) {
			icono = new Embedded("");

		}
		if (tipoMovimiento.equals('1')) {
			icono = new Embedded("", new ThemeResource(
					"images/botones/response.png"));

		}
		if (tipoMovimiento.equals('2')) {
			icono = new Embedded("", new ThemeResource(
					"images/botones/forward.png"));

		}
		if (tipoMovimiento.equals('3')) {
			icono = new Embedded("", new ThemeResource(
					"images/botones/finalizado.png"));

		}
		return icono;
	}

	public static Date getPrimerDiaDelMes() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.getActualMinimum(Calendar.DAY_OF_MONTH),
				cal.getMinimum(Calendar.HOUR_OF_DAY),
				cal.getMinimum(Calendar.MINUTE),
				cal.getMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getUltimoDiaDelMes() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.getActualMaximum(Calendar.DAY_OF_MONTH),
				cal.getMaximum(Calendar.HOUR_OF_DAY),
				cal.getMaximum(Calendar.MINUTE),
				cal.getMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	public static String completarCeros(String cadena, int cantidad) {
		String respuesta = cadena.trim();
		for (int i = respuesta.length(); i < cantidad; i++) {
			respuesta = "0" + respuesta;
		}
		return respuesta;
	}

	public static String cortarCadena(String cadena, int maximoCaracteres) {
		if (cadena.length() > maximoCaracteres) {
			return cadena.substring(0, maximoCaracteres) + "...";
		}
		return cadena;
	}

	public static Long getDiasTranscurridos(Date desde, Date hasta) {
		return ((hasta.getTime()) - desde.getTime()) / (24 * 60 * 60 * 1000);
	}
}
