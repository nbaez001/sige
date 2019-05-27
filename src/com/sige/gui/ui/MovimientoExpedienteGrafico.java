package com.sige.gui.ui;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DocumentoInternoMapeo;
import com.sige.entidad.TipoDocumento;
import com.sige.gui.DetalleExpedienteGrafico;
import com.sige.gui.widgetset.client.numberfield.MovimientoExpedienteServerRpc;
import com.sige.gui.widgetset.client.numberfield.MovimientoExpedienteState;
import com.sige.util.SigeUtil;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class MovimientoExpedienteGrafico extends
		com.vaadin.ui.AbstractComponent {

	private List<DocumentoInternoMapeo> documentoInternoMapeo;
	private List<Dependencia> dependencias;
	private List<TipoDocumento> tiposDocumentos;
	private String[] idTipoDocumento;
	private String[] idDependencias;
	private String[] codigoDocumento;
	private String[] nivel;
	private String[] codigoDocumentoPadre;
	private String[] codigoDocumentoReferencia;
	private String[] idTipoDocumentos;
	private String[] nombreTipoDocumentos;
	private String[] idDependenciasTotal;
	private String[] nombreDependenciasTotal;

	
	private MovimientoExpedienteServerRpc rpc = new MovimientoExpedienteServerRpc() {

		public void clicked(MouseEventDetails mouseDetails, String codigo) {
			if (codigo.length() > 0) {
				Window ventana = SigeUtil.generarBuscador(new Window(),
						"DETALLE DE DOCUMENTO", "440px", "570px");
				ventana.setContent(new DetalleExpedienteGrafico(codigo));
				UI.getCurrent().addWindow(ventana);
			}
			// update shared state

		}
	};

	public MovimientoExpedienteGrafico(
			List<DocumentoInternoMapeo> documentoInternoMapeo,
			List<Dependencia> dependencias, List<TipoDocumento> tipoDocumentos,
			String idMesaPartes) {
		registerRpc(rpc);
		this.documentoInternoMapeo = documentoInternoMapeo;
		this.dependencias = dependencias;
		this.tiposDocumentos = tipoDocumentos;

		nombreDependenciasTotal = new String[this.dependencias.size()];
		idDependenciasTotal = new String[this.dependencias.size()];
		codigoDocumento = new String[this.documentoInternoMapeo.size()];
		codigoDocumentoPadre = new String[this.documentoInternoMapeo.size()];
		codigoDocumentoReferencia = new String[this.documentoInternoMapeo
				.size()];
		idTipoDocumento = new String[this.documentoInternoMapeo.size()];
		idDependencias = new String[this.documentoInternoMapeo.size()];

		idTipoDocumentos = new String[this.tiposDocumentos.size()];
		nombreTipoDocumentos = new String[this.tiposDocumentos.size()];

		nivel = new String[this.documentoInternoMapeo.size()];
		for (int i = 0; i < documentoInternoMapeo.size(); i++) {
			idTipoDocumento[i] = documentoInternoMapeo.get(i)
					.getCodigotipodocumento();
			idDependencias[i] = documentoInternoMapeo.get(i)
					.getCodigodependencia() + "";
			codigoDocumento[i] = documentoInternoMapeo.get(i)
					.getCodigoDocumento();
			codigoDocumentoPadre[i] = documentoInternoMapeo.get(i)
					.getCodigoDocumentoPadre();
			codigoDocumentoReferencia[i] = documentoInternoMapeo.get(i)
					.getCodigoDocumentoReferencia();
			nivel[i] = documentoInternoMapeo.get(i).getNivel() + "";
		}

		for (int i = 0; i < dependencias.size(); i++) {
			idDependenciasTotal[i] = dependencias.get(i).getId() + "";
			nombreDependenciasTotal[i] = dependencias.get(i).getNombre();
		}

		for (int i = 0; i < tiposDocumentos.size(); i++) {
			idTipoDocumentos[i] = tiposDocumentos.get(i)
					.getCodigoTipoDocumento();
			nombreTipoDocumentos[i] = tiposDocumentos.get(i).getNombre();
		}
		if (getState() != null) {

			getState().idTipoDocumento = idTipoDocumento;
			Log l = LogFactory.getLog(MovimientoExpedienteGrafico.class);
			l.info(getState().idTipoDocumento.length);
			getState().idDependencias = idDependencias;
			l.info(getState().idDependencias.length);
			getState().codigoDocumento = codigoDocumento;
			getState().codigoDocumentoReferencia = codigoDocumentoReferencia;

			getState().codigoDocumentoPadre = codigoDocumentoPadre;
			getState().nombreDependenciasTotal = nombreDependenciasTotal;

			getState().idDependenciasTotal = idDependenciasTotal;
			getState().idTipoDocumentos = idTipoDocumentos;
			getState().nombreTipoDocumentos = nombreTipoDocumentos;
			getState().nivel = nivel;
			getState().idMesaPartes = idMesaPartes;

			getState().graficar = true;
		}
	}

	@Override
	public MovimientoExpedienteState getState() {
		return (MovimientoExpedienteState) super.getState();
	}
}
