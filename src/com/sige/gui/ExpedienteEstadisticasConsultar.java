package com.sige.gui;

import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.vaadin.addon.JFreeChartWrapper;

import com.sige.entidad.Dependencia;
import com.sige.entidad.TipoTramite;
import com.sige.entidad.Usuario;
import com.sige.servicio.DependenciaServicio;
import com.sige.servicio.DocumentoInternoDestinoService;
import com.sige.servicio.ExpedienteMovimientoServicio;
import com.sige.servicio.ExpedienteServicio;
import com.sige.servicio.TipoTramiteService;
import com.sige.servicio.UsuarioServicio;
import com.sige.util.Constantes;
import com.sige.util.Injector;
import com.sige.util.Notificacion;
import com.sige.util.SigeUtil;
import com.sige.util.TramiteTupaEstadistica;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

public class ExpedienteEstadisticasConsultar extends CustomComponent implements
		ClickListener {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button btnConsultar;
	@AutoGenerated
	private Panel pnlGrafico;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private PopupDateField pdfHasta;
	@AutoGenerated
	private PopupDateField pdfDel;
	@AutoGenerated
	private Label lblHasta;
	@AutoGenerated
	private Label lblDel;
	@AutoGenerated
	private Label lblIntervaloFechas;
	@AutoGenerated
	private ComboBox cbxTipoEstadistica;
	@AutoGenerated
	private Label lblTipoEstadistica;
	private ExpedienteServicio expedienteServicio;
	private TipoTramiteService tipoTramiteService;
	private DefaultCategoryDataset dataset;
	private ExpedienteMovimientoServicio expedienteMovimientoServicio;
	private DependenciaServicio dependenciaServicio;
	private UsuarioServicio usuarioServicio;
	private DocumentoInternoDestinoService documentoInternoDestinoService;
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	private Long total;
	private Long acumulado;
	private String titulo;
	private String categoria;
	private List<TipoTramite> TipoTramiteTupa;
	private List<Dependencia> dependencias;
	private String[] optionTipoStadistica = {
			"Expedientes por procedimiento TUPA",
			"Expediente por unidad org�nica",
			"Expedientes no TUPA por Usuario",
			"Tiempo promedio de procesos TUPA" };
	private Long[] estadisticasParciales;
	private String[] nombreParcial;
	private List<Usuario> usuarios;
	private TramiteTupaEstadistica tramiteTupaEstadistica;
	private Date desde;
	private Date hasta;
	private Long totalTipo;
	private Double porcentaje;

	public ExpedienteEstadisticasConsultar() {
		this.expedienteServicio = Injector
				.obtenerServicio(ExpedienteServicio.class);
		this.tipoTramiteService = Injector
				.obtenerServicio(TipoTramiteService.class);
		this.expedienteMovimientoServicio = Injector
				.obtenerServicio(ExpedienteMovimientoServicio.class);
		this.dependenciaServicio = Injector
				.obtenerServicio(DependenciaServicio.class);
		this.usuarioServicio = Injector.obtenerServicio(UsuarioServicio.class);
		this.documentoInternoDestinoService = Injector
				.obtenerServicio(DocumentoInternoDestinoService.class);
		buildMainLayout();

		setCompositionRoot(mainLayout);
		postBuild();
	}

	private void postBuild() {
		total = 0L;
		acumulado = 0L;
		titulo = "";
		categoria = "";
		this.btnConsultar.addClickListener((ClickListener) this);
		TipoTramiteTupa = tipoTramiteService.obtenerTipoTramiteTupa();
		usuarios = usuarioServicio.obtenerTodos();
		dependencias = dependenciaServicio.obtenerTodos();

		for (int i = 0; i < optionTipoStadistica.length; i++) {
			cbxTipoEstadistica.addItem(i);
			cbxTipoEstadistica.setItemCaption(i, optionTipoStadistica[i]);
		}
		this.pdfDel.setDateFormat("dd/MM/yyyy");
		this.pdfDel.setValue(SigeUtil.getPrimerDiaDelMes());
		this.pdfHasta.setDateFormat("dd/MM/yyyy");
		this.pdfHasta.setValue(new Date());
		cbxTipoEstadistica.setValue(0);
	}

	private void createDataset() {
		if (pdfDel.getValue() == null || pdfHasta.getValue() == null) {
			Notificacion.show(new Notificacion(
					"Debe seleccionar el intervalo de fechas",
					Constantes.MENSAJE.TYPE_ERROR));
			pnlGrafico.setContent(null);
			return;
		}

		desde = SigeUtil.obtenerFechaFormato(this.pdfDel.getValue().toString(),
				0);
		hasta = SigeUtil
				.obtenerFechaFormato(
						SigeUtil.sumarDiasFecha(this.pdfHasta.getValue(), 1)
								.toString(), 0);
		acumulado = 0L;
		porcentaje = (double) 0;
		totalTipo = 0L;
		total = 0L;
		if ((Integer) cbxTipoEstadistica.getValue() == 0) {

			titulo = "Evaluaci�n de calificaci�n de tr�mites por tipo de procedimiento TUPA";
			categoria = "PROCEDIMIENTO TUPA";
			dataset = new DefaultCategoryDataset();

			total = expedienteServicio.consultarEstadistica(
					Constantes.TIPO_ESTADISTICA.PROCEDIMIENTO_TUPA, null,
					desde, hasta, Boolean.FALSE).getTotal();
			for (int i = 0; i < TipoTramiteTupa.size(); i++) {
				if (i < 9) {
					totalTipo = expedienteServicio.consultarEstadistica(
							Constantes.TIPO_ESTADISTICA.PROCEDIMIENTO_TUPA,
							TipoTramiteTupa.get(i).getCodigoTipoTramite(),
							desde, hasta, Boolean.FALSE).getTotal();
					acumulado += totalTipo;
					if (total > 0) {
						porcentaje = Math.round((totalTipo * 100)
								/ ((double) total) * Math.pow(10, 2))
								/ Math.pow(10, 2);

					} else {
						porcentaje = (double) 0;
					}
					if (totalTipo > 0) {
						dataset.addValue(
								totalTipo,
								SigeUtil.cortarCadena(TipoTramiteTupa.get(i)
										.getNombre().trim(), 105)
										+ " Total: "
										+ totalTipo
										+ "-"
										+ porcentaje + "%   ", categoria);
					}

				} else {
					totalTipo = total - acumulado;
					if (total > 0) {
						porcentaje = ((totalTipo) * 100) / ((double) total);
					} else {
						porcentaje = (double) 0;
					}
					if (totalTipo > 0) {
						dataset.addValue(totalTipo, "(OTROS) Total: "
								+ totalTipo + "-" + porcentaje + "%   ",
								categoria);
					}
					break;
				}
			}
		} else if ((Integer) cbxTipoEstadistica.getValue() == 1) {

			titulo = "Evaluaci�n de calificaci�n de tr�mites por dependencia";
			categoria = "DEPENDENCIA";
			dataset = new DefaultCategoryDataset();
			if (dependencias.size() < 9) {
				estadisticasParciales = new Long[dependencias.size()];
				nombreParcial = new String[dependencias.size()];
			} else {
				estadisticasParciales = new Long[9];
				nombreParcial = new String[9];
			}
			for (int i = 0; i < dependencias.size(); i++) {
				if (i < 9) {
					totalTipo = expedienteMovimientoServicio
							.getTotalExpedientesDependencia(dependencias.get(i)
									.getId(), desde, hasta);
					total += totalTipo;
					estadisticasParciales[i] = totalTipo;
					nombreParcial[i] = dependencias.get(i).getNombre();
					acumulado += totalTipo;
				} else {
					total += expedienteMovimientoServicio
							.getTotalExpedientesDependencia(dependencias.get(i)
									.getId(), desde, hasta);
				}
			}

		} else if ((Integer) cbxTipoEstadistica.getValue() == 2) {
			titulo = "Expedientes no TUPA por terminalista";
			categoria = "RECEPCIONISTA";
			dataset = new DefaultCategoryDataset();

			if (usuarios.size() < 9) {

				estadisticasParciales = new Long[usuarios.size()];
				nombreParcial = new String[usuarios.size()];
			} else {
				estadisticasParciales = new Long[9];
				nombreParcial = new String[9];
			}

			for (int i = 0; i < usuarios.size(); i++) {
				if (i < 9) {
					totalTipo = documentoInternoDestinoService
							.getTotalPorRecepcionista(usuarios.get(i)
									.getUsuario(), desde, hasta);
					total += totalTipo;
					estadisticasParciales[i] = totalTipo;
					nombreParcial[i] = usuarios.get(i).getNombres();
					acumulado += totalTipo;
				} else {
					total += documentoInternoDestinoService
							.getTotalPorRecepcionista(usuarios.get(i)
									.getUsuario(), desde, hasta);
				}
			}

		} else if ((Integer) cbxTipoEstadistica.getValue() == 3) {
			titulo = "Tiempo promedio de procesos TUPA";
			categoria = "PROCEDIMIENTO TUPA";
			dataset = new DefaultCategoryDataset();

			total = expedienteServicio.consultarEstadistica(
					Constantes.TIPO_ESTADISTICA.PROCEDIMIENTO_TUPA, null,
					desde, hasta, Boolean.TRUE).getTotal();
			Long totalDias = 0L;
			for (int i = 0; i < TipoTramiteTupa.size(); i++) {
				if (i < 9) {
					tramiteTupaEstadistica = expedienteServicio
							.consultarEstadistica(
									Constantes.TIPO_ESTADISTICA.PROCEDIMIENTO_TUPA,
									TipoTramiteTupa.get(i)
											.getCodigoTipoTramite(), desde,
									hasta, Boolean.TRUE);
					totalTipo = tramiteTupaEstadistica.getTotal();
					acumulado += totalTipo;
					if (totalTipo > 0) {
						dataset.addValue(totalTipo, TipoTramiteTupa.get(i)
								.getNombre().trim()
								+ "- "
								+ totalTipo
								+ " Final... Dias Prom. : "
								+ tramiteTupaEstadistica.getPromedioDias()
								+ "   ", categoria);
					}
				} else {
					totalDias += expedienteServicio.consultarEstadistica(
							Constantes.TIPO_ESTADISTICA.PROCEDIMIENTO_TUPA,
							TipoTramiteTupa.get(i).getCodigoTipoTramite(),
							desde, hasta, Boolean.TRUE).getDiasTotal();
				}
			}
			totalTipo = total - acumulado;
			if (totalTipo > 0) {
				dataset.addValue(
						totalTipo,
						"(OTROS)... Dias Prom. : "
								+ Math.round((totalDias / (double) totalTipo)
										* Math.pow(10, 2)) / Math.pow(10, 2)
								+ "   ", categoria);
			}
		}
		if ((Integer) cbxTipoEstadistica.getValue() == 1
				|| (Integer) cbxTipoEstadistica.getValue() == 2) {
			for (int i = 0; i < estadisticasParciales.length; i++) {

				if (total > 0) {
					porcentaje = Math
							.round(((estadisticasParciales[i] * 100) / (double) total)
									* Math.pow(10, 2))
							/ Math.pow(10, 2);
				} else {
					porcentaje = (double) 0;
				}
				if (estadisticasParciales[i] > 0) {
					dataset.addValue(estadisticasParciales[i],
							SigeUtil.cortarCadena(nombreParcial[i].trim(), 105)
									+ " Total: " + estadisticasParciales[i]
									+ "-" + porcentaje + "%   ", categoria);
				}
			}
			totalTipo = total - acumulado;
			if (total > 0) {

				porcentaje = Math.round((((totalTipo) * 100) / (double) total)
						* Math.pow(10, 2))
						/ Math.pow(10, 2);

			} else {
				porcentaje = (double) 0;
			}
			if (totalTipo > 0) {
				dataset.setValue(totalTipo, "(OTROS) Total: " + totalTipo + "-"
						+ porcentaje + "%   ", categoria);
			}
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getSource() == this.btnConsultar) {
			if (cbxTipoEstadistica.getValue() != null) {
				createDataset();
				if (dataset != null) {

					JFreeChart chart = ChartFactory.createBarChart(titulo,
							"Nombre", "Total", dataset,
							PlotOrientation.VERTICAL, true, true, false);

					JFreeChartWrapper wrapper = new JFreeChartWrapper(chart) {
						private static final long serialVersionUID = 1L;

						@Override
						public void attach() {
							super.attach();
							setResource("src", getSource());
						}
					};
					pnlGrafico.setContent(wrapper);
					dataset = null;
				}
			} else {
				Notificacion.show(new Notificacion(
						"Debe seleccionar un tipo de estadistica",
						Constantes.MENSAJE.TYPE_ERROR));
				pnlGrafico.setContent(null);
			}
		}

	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// lblTipoEstadistica
		lblTipoEstadistica = new Label();
		lblTipoEstadistica.setImmediate(false);
		lblTipoEstadistica.setWidth("-1px");
		lblTipoEstadistica.setHeight("-1px");
		lblTipoEstadistica.setValue("Seleccione tipo de estadistica:");
		mainLayout.addComponent(lblTipoEstadistica, "top:2.0px;left:20.0px;");

		// cbxTipoEstadistica
		cbxTipoEstadistica = new ComboBox();
		cbxTipoEstadistica.setImmediate(false);
		cbxTipoEstadistica.setWidth("340px");
		cbxTipoEstadistica.setHeight("-1px");
		mainLayout.addComponent(cbxTipoEstadistica, "top:20.0px;left:20.0px;");

		// lblIntervaloFechas
		lblIntervaloFechas = new Label();
		lblIntervaloFechas.setImmediate(false);
		lblIntervaloFechas.setWidth("-1px");
		lblIntervaloFechas.setHeight("-1px");
		lblIntervaloFechas.setValue("Intervalo de Fechas:");
		mainLayout.addComponent(lblIntervaloFechas, "top:2.0px;left:560.0px;");

		// lblDel
		lblDel = new Label();
		lblDel.setImmediate(false);
		lblDel.setWidth("-1px");
		lblDel.setHeight("-1px");
		lblDel.setValue("Del");
		mainLayout.addComponent(lblDel, "top:24.0px;left:479.0px;");

		// lblHasta
		lblHasta = new Label();
		lblHasta.setImmediate(false);
		lblHasta.setWidth("-1px");
		lblHasta.setHeight("-1px");
		lblHasta.setValue("Hasta");
		mainLayout.addComponent(lblHasta, "top:24.0px;left:633.0px;");

		// pdfDel
		pdfDel = new PopupDateField();
		pdfDel.setImmediate(false);
		pdfDel.setWidth("127px");
		pdfDel.setHeight("-1px");
		mainLayout.addComponent(pdfDel, "top:20.0px;left:503.0px;");

		// pdfHasta
		pdfHasta = new PopupDateField();
		pdfHasta.setImmediate(false);
		pdfHasta.setWidth("127px");
		pdfHasta.setHeight("-1px");
		mainLayout.addComponent(pdfHasta, "top:20.0px;left:675.0px;");

		// pnlGrafico
		pnlGrafico = buildPnlGrafico();
		mainLayout.addComponent(pnlGrafico, "top:60.0px;left:18.0px;");

		// btnConsultar
		btnConsultar = new Button();
		btnConsultar.setCaption("Consultar");
		btnConsultar.setIcon(new ThemeResource("images/botones/consult.png"));
		btnConsultar.setImmediate(true);
		btnConsultar.setWidth("110px");
		btnConsultar.setHeight("-1px");
		mainLayout.addComponent(btnConsultar, "top:20.0px;left:363.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPnlGrafico() {
		// common part: create layout
		pnlGrafico = new Panel();
		pnlGrafico.setImmediate(false);
		pnlGrafico.setWidth("830px");
		pnlGrafico.setHeight("500px");

		// verticalLayout_1
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		pnlGrafico.setContent(verticalLayout_1);

		return pnlGrafico;
	}

}
