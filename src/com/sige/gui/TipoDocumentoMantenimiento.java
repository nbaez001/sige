package com.sige.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.dialogs.ConfirmDialog;

import com.sige.entidad.TipoDocumento;
import com.sige.gui.ui.NumberField;
import com.sige.servicio.TipoDocumentoServicio;
import com.sige.servicio.UtilService;
import com.sige.util.Boton;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Injector;
import com.sige.util.Notificacion;
import com.sige.util.Permiso;
import com.sige.util.SigeUtil;
import com.sige.util.TextField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class TipoDocumentoMantenimiento extends CustomComponent implements
		ClickListener {

	/*- VaadinEditorProperties={"grid":"RegularGrid,10","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Button btnImprimir;

	@AutoGenerated
	private Label lblTotalPaginas;

	@AutoGenerated
	private Label lblSeparador;

	@AutoGenerated
	private Boton btnFin;

	@AutoGenerated
	private Boton btnSiguiente;

	@AutoGenerated
	private NumberField txtPaginaActual;

	@AutoGenerated
	private Label lblPagina;

	@AutoGenerated
	private Boton btnAtras;

	@AutoGenerated
	private Boton btnInicio;

	@AutoGenerated
	private Boton btnCancelar;

	@AutoGenerated
	private Boton btnEliminar;

	@AutoGenerated
	private Boton btnGuardar;

	@AutoGenerated
	private Boton btnNuevo;

	@AutoGenerated
	private TextField txtNombreTipo;

	@AutoGenerated
	private Label lblNombre;

	@AutoGenerated
	private Label lblTitulo;

	@AutoGenerated
	private Table tblTipoDocumento;

	@AutoGenerated
	private Boton btnBuscar;

	@AutoGenerated
	private TextField txtNombre;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */

	private boolean flagEsNuevo = false;
	private TipoDocumentoServicio documentoServicio;
	private TipoDocumento tipoDocumento;
	private Long cantTipoDocs;
	private Window quienLlama;

	private Permiso permiso;
	// private List<TipoDocumento> tiposDocumentos;
	private UtilService utilService;
	private Map<String, Integer> columnLenghts;
	private boolean confirmacion = false;

	public TipoDocumentoMantenimiento(Window quienLlama, Permiso permiso) {
		documentoServicio = Injector
				.obtenerServicio(TipoDocumentoServicio.class);
		utilService = Injector.obtenerServicio(UtilService.class);
		this.quienLlama = quienLlama;
		this.permiso = permiso;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		postBuild();
		resetearFormulario();
		getAllTipoDocumento();
		getCantidadRegistros();
		// TODO add user code here
	}

	private void desactivarCampos(boolean estado) {
		txtNombreTipo.setEnabled(estado);
	}

	private void resetearFormulario() {
		this.flagEsNuevo = false;
		this.btnCancelar.setEnabled(false);
		this.btnNuevo.setEnabled(true);
		this.btnGuardar.setEnabled(false);
		this.btnEliminar.setEnabled(false);
		this.txtNombreTipo.setValue("");
		this.txtNombre.setValue("");
		desactivarCampos(false);
	}

	private void NuevoRegistro() {
		this.tipoDocumento = new TipoDocumento();
		this.flagEsNuevo = true;
		this.btnGuardar.setCaption("Guardar");
		this.btnGuardar.setVisible(permiso.getNuevo());
		this.btnGuardar.setEnabled(true);
		this.btnGuardar.setVisible(permiso.getNuevo());
		this.btnCancelar.setEnabled(true);
		this.btnNuevo.setEnabled(false);
		this.txtNombreTipo.setValue("");
		this.txtNombre.setValue("");
		desactivarCampos(permiso.getNuevo());
		getCantidadRegistros();
	}

	private void getCantidadRegistros() {
		this.cantTipoDocs = documentoServicio.getCantidadRegistros();
	}

	private void postBuild() {

		lblTitulo.setContentMode(ContentMode.HTML);

		columnLenghts = utilService.getLengthColumns("tipodocumento");
		this.txtNombre.setMaxLength(columnLenghts.get("nombre"));
		this.txtNombreTipo.setMaxLength(columnLenghts.get("nombre"));
		this.btnNuevo.addClickListener((ClickListener) this);
		this.btnGuardar.addClickListener((ClickListener) this);
		this.btnCancelar.addClickListener((ClickListener) this);
		this.btnEliminar.addClickListener((ClickListener) this);
		this.btnBuscar.addClickListener((ClickListener) this);
		this.btnInicio.addClickListener((ClickListener) this);
		this.btnAtras.addClickListener((ClickListener) this);
		this.btnSiguiente.addClickListener((ClickListener) this);
		this.btnFin.addClickListener((ClickListener) this);
		this.btnImprimir.addClickListener((ClickListener) this);
		this.btnInicio.setStyleName(Reindeer.BUTTON_LINK);
		this.btnAtras.setStyleName(Reindeer.BUTTON_LINK);
		this.btnSiguiente.setStyleName(Reindeer.BUTTON_LINK);
		this.btnFin.setStyleName(Reindeer.BUTTON_LINK);
		this.btnBuscar.setStyleName("buscar");
		this.txtPaginaActual.setValue("1");

		this.txtPaginaActual.setId("paginaActual");

		this.btnBuscar.setStyleName("buscar");
		this.txtNombre.setId("buscarNombres");

		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("id", Long.class, null);
		container.addContainerProperty("item", Long.class, null);
		container.addContainerProperty("nombre", String.class, null);

		tblTipoDocumento.setContainerDataSource(container);
		tblTipoDocumento.setVisibleColumns(new Object[] { "item", "nombre" });
		tblTipoDocumento.setColumnWidth("item", 25);
		tblTipoDocumento.setColumnWidth("nombre", 250);

		tblTipoDocumento.setColumnHeader("item", "Item");
		tblTipoDocumento.setColumnHeader("nombre", "Nombre");
		tblTipoDocumento.setSelectable(true);
		tblTipoDocumento.setImmediate(true);
		this.txtPaginaActual.setImmediate(true);
		this.btnCancelar.setId("cancelar");
		this.btnEliminar.setId("eliminar");
		this.btnGuardar.setId("guardar");
		this.btnNuevo.setId("nuevo");
		this.btnBuscar.setId("consultar");
		this.btnImprimir.setId("imprimir");
		SigeUtil.validarBotones(permiso, btnImprimir, btnNuevo, btnEliminar,
				btnBuscar, btnCancelar, btnGuardar);
		txtNombre.setVisible(permiso.getConsultar());
		this.tblTipoDocumento
				.addValueChangeListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						Item item = tblTipoDocumento.getItem(tblTipoDocumento
								.getValue());
						if (item != null) {
							btnGuardar.setCaption("Actualizar");
							btnEliminar.setEnabled(true);
							btnCancelar.setVisible(permiso.getModificar());
							btnCancelar.setEnabled(true);
							btnNuevo.setEnabled(true);
							flagEsNuevo = false;
							btnGuardar.setVisible(permiso.getModificar());
							btnGuardar.setEnabled(true);
							desactivarCampos(permiso.getModificar());
							getTipoDocumento(new Long(item
									.getItemProperty("id").getValue()
									.toString()));
							tblTipoDocumento.unselect(tblTipoDocumento
									.getValue());
						}
					}
				});

		this.txtPaginaActual.addShortcutListener(new ShortcutListener("",
				KeyCode.ENTER, null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void handleAction(Object sender, Object target) {
				if (target instanceof TextField
						&& ((TextField) target).getId().equals("paginaActual")) {
					Long paginaActual = null;
					try {
						paginaActual = Long.parseLong(txtPaginaActual
								.getValue().toString());
					} catch (Exception exception) {
						paginaActual = 1L;
						Notification notificacion = new Notification(
								"Mensaje de error",
								"<br/>Debe Ingresar un n�mero");
						notificacion.setDelayMsec(10000);
						notificacion
								.setPosition(Notification.POSITION_CENTERED_TOP);
						UI.getCurrent().showNotification(notificacion);
					}
					txtPaginaActual.setValue(paginaActual.toString());
					getAllTipoDocumento();
				}
			}
		});

		this.txtNombre.setImmediate(true);
		this.txtNombre.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub
				txtPaginaActual.setValue("1");
				txtNombre.setValue(event.getText());
				getAllTipoDocumento();
			}
		});
	}

	public void obtenerMotivo(String motivo, Boolean confirmacion,
			Integer tipoOperacion) {
		if (confirmacion) {
			tipoDocumento.setMotivoModificacion(motivo);
			this.confirmacion = true;
			if (tipoOperacion.equals(Constantes.TIPO_OPERACION.MODIFICACION)) {
				btnGuardar.click();
			} else {
				btnEliminar.click();
			}
			this.confirmacion = false;
		}
	}

	private void getTipoDocumento(Long idTipoDoc) {
		tipoDocumento = documentoServicio.obtener(idTipoDoc);
		this.txtNombreTipo.setValue(tipoDocumento.getNombre().trim());
	}

	@SuppressWarnings("unchecked")
	private void getAllTipoDocumento() {
		IndexedContainer container = (IndexedContainer) tblTipoDocumento
				.getContainerDataSource();
		container.removeAllItems();

		List<TipoDocumento> tiposDocs = null;
		Long paginaActual = Long.parseLong(this.txtPaginaActual.getValue()
				.toString());
		TipoDocumento documBuscar = new TipoDocumento();
		documBuscar.setNombre(this.txtNombre.getValue().toString());
		Busqueda busqueda = documentoServicio.buscarPorTipoDocumento(
				documBuscar, paginaActual);

		tiposDocs = (List<TipoDocumento>) busqueda.getRegistos();
		this.lblTotalPaginas.setValue(busqueda.getNumeroPaginas().toString());
		this.txtPaginaActual.setValue(busqueda.getPaginaActual().toString());
		Long numeroItem = (Long.parseLong(this.txtPaginaActual.getValue()
				.toString()) - 1) * Constantes.PAGINACION.USUARIO + 1;
		cantTipoDocs = new Long(tiposDocs.size());

		for (int i = 0; i < tiposDocs.size(); i++) {
			Item item = container.addItem(i);
			item.getItemProperty("id").setValue(tiposDocs.get(i).getId());
			item.getItemProperty("item").setValue(numeroItem++);
			item.getItemProperty("nombre").setValue(
					tiposDocs.get(i).getNombre());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == (this.btnNuevo)) {
			NuevoRegistro();
			this.txtNombreTipo.focus();

		} else {
			if (event.getSource() == this.btnGuardar) {
				if (SigeUtil.validarCamposTexto(txtNombreTipo)) {
					tipoDocumento.setNombre(this.txtNombreTipo.getValue()
							.toString());
					if (!documentoServicio.validarDuplicado(tipoDocumento)) {
						Notificacion.show(new Notificacion(
								"El nombre de el tipo de documento ya existe",
								Constantes.MENSAJE.TYPE_ERROR));
						return;
					}
					if (this.flagEsNuevo) {
						tipoDocumento.setCodigoTipoDocumento(cantTipoDocs + 1);
						documentoServicio.crear(tipoDocumento);
						Notificacion.show(new Notificacion(
								"Se guardo correctamente el tipo de documento",
								Constantes.MENSAJE.TYPE_SUCCES));
					} else {
						if (!confirmacion) {
							Window ventana = SigeUtil.generarBuscador(
									new Window(), "INGRESAR MOTIVO", "360",
									"260");
							ventana.setContent(new PanelMotivoObservacion(
									ventana, this,
									Constantes.TIPO_OPERACION.MODIFICACION,
									(columnLenghts.get("motivomodifica"))));
							UI.getCurrent().addWindow(ventana);
							return;
						}
						documentoServicio.actualizar(tipoDocumento);
						Notificacion
								.show(new Notificacion(
										"Se actualizo correctamente el tipo de documento",
										Constantes.MENSAJE.TYPE_SUCCES));
					}
					resetearFormulario();
					getAllTipoDocumento();
				} else {
					Notificacion.show(new Notificacion(
							"Debe llenar todos los campos",
							Constantes.MENSAJE.TYPE_ERROR));
				}
			} else {
				if (event.getSource() == this.btnCancelar) {
					resetearFormulario();
					getAllTipoDocumento();
				} else {
					if (event.getSource() == this.btnBuscar) {
						this.txtPaginaActual.setValue("1");
						getAllTipoDocumento();
					} else {
						if (event.getSource() == this.btnEliminar) {
							if (!confirmacion) {
								Window ventana = SigeUtil.generarBuscador(
										new Window(), "INGRESAR MOTIVO", "360",
										"260");
								ventana.setContent(new PanelMotivoObservacion(
										ventana, this,
										Constantes.TIPO_OPERACION.ELIMINACION,
										(columnLenghts.get("motivomodifica"))));
								UI.getCurrent().addWindow(ventana);
								return;
							}
							ConfirmDialog.show(UI.getCurrent(), "Confirmaci�n",
									"�Desea elminar el tipo de documento?",
									"Aceptar", "Cancelar",
									new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												documentoServicio
														.eliminarTipoDocumento(tipoDocumento);
												resetearFormulario();
												getAllTipoDocumento();
											}
										}
									});
						} else {
							if (event.getSource() == this.btnSiguiente) {
								Long paginaActual = Long
										.parseLong(this.txtPaginaActual
												.getValue().toString()) + 1;
								if (paginaActual <= Long
										.parseLong(this.lblTotalPaginas
												.getValue().toString())) {
									this.txtPaginaActual.setValue(paginaActual
											.toString());
								}
								getAllTipoDocumento();
							} else {
								if (event.getSource() == this.btnAtras) {
									Long paginaActual = Long
											.parseLong(this.txtPaginaActual
													.getValue().toString()) - 1;
									if (paginaActual >= 1) {
										this.txtPaginaActual
												.setValue(paginaActual
														.toString());
									}
									getAllTipoDocumento();
								} else {
									if (event.getSource() == this.btnInicio) {
										this.txtPaginaActual.setValue("1");
										getAllTipoDocumento();
									} else {
										if (event.getSource() == this.btnFin) {
											this.txtPaginaActual
													.setValue(this.lblTotalPaginas
															.getValue());
											getAllTipoDocumento();
										} else if (event.getSource() == this.btnImprimir) {
											Window ventana = SigeUtil
													.generarBuscador(
															new Window(),
															"IMPRIMIR",
															"800px", "550px");
											Map<String, Object> parametros = new HashMap<String, Object>();
											parametros.put("nombre",
													this.txtNombre.getValue()
															.toUpperCase());
											ventana.setContent(new PDFVizualizador(
													this, permiso, parametros,
													null,
													"TipoDocumentoImprimir",
													null));
											UI.getCurrent().addWindow(ventana);
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("642px");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("642px");
		setHeight("100.0%");

		// txtNombre
		txtNombre = new TextField();
		txtNombre.setImmediate(false);
		txtNombre.setWidth("234px");
		txtNombre.setHeight("-1px");
		mainLayout.addComponent(txtNombre, "top:10.0px;left:50.0px;");

		// btnBuscar
		btnBuscar = new Boton();
		btnBuscar.setIcon(new ThemeResource("images/botones/find.png"));
		btnBuscar.setImmediate(true);
		btnBuscar.setWidth("28px");
		btnBuscar.setHeight("24px");
		mainLayout.addComponent(btnBuscar, "top:10.0px;left:284.0px;");

		// tblTipoDocumento
		tblTipoDocumento = new Table();
		tblTipoDocumento.setImmediate(false);
		tblTipoDocumento.setWidth("310px");
		tblTipoDocumento.setHeight("236px");
		mainLayout.addComponent(tblTipoDocumento, "top:34.0px;left:20.0px;");

		// lblTitulo
		lblTitulo = new Label();
		lblTitulo.setImmediate(false);
		lblTitulo.setWidth("-1px");
		lblTitulo.setHeight("-1px");
		lblTitulo.setValue("<b>Datos de Tipo de Documento</b>");
		mainLayout.addComponent(lblTitulo, "top:23.0px;left:377.0px;");

		// lblNombre
		lblNombre = new Label();
		lblNombre.setImmediate(false);
		lblNombre.setWidth("-1px");
		lblNombre.setHeight("-1px");
		lblNombre.setValue("Nombre");
		mainLayout.addComponent(lblNombre, "top:51.0px;left:335.0px;");

		// txtNombreTipo
		txtNombreTipo = new TextField();
		txtNombreTipo.setImmediate(false);
		txtNombreTipo.setWidth("230px");
		txtNombreTipo.setHeight("-1px");
		mainLayout.addComponent(txtNombreTipo, "top:52.0px;left:390.0px;");

		// btnNuevo
		btnNuevo = new Boton();
		btnNuevo.setCaption("Nuevo");
		btnNuevo.setIcon(new ThemeResource("images/botones/new.png"));
		btnNuevo.setImmediate(true);
		btnNuevo.setWidth("110px");
		btnNuevo.setHeight("-1px");
		mainLayout.addComponent(btnNuevo, "top:100.0px;left:363.0px;");

		// btnGuardar
		btnGuardar = new Boton();
		btnGuardar.setCaption("Guardar");
		btnGuardar.setIcon(new ThemeResource("images/botones/save.png"));
		btnGuardar.setImmediate(true);
		btnGuardar.setWidth("110px");
		btnGuardar.setHeight("-1px");
		mainLayout.addComponent(btnGuardar, "top:100.0px;left:487.0px;");

		// btnEliminar
		btnEliminar = new Boton();
		btnEliminar.setCaption("Eliminar");
		btnEliminar.setIcon(new ThemeResource("images/botones/delete.png"));
		btnEliminar.setImmediate(true);
		btnEliminar.setWidth("110px");
		btnEliminar.setHeight("-1px");
		mainLayout.addComponent(btnEliminar, "top:134.0px;left:364.0px;");

		// btnCancelar
		btnCancelar = new Boton();
		btnCancelar.setCaption("Cancelar");
		btnCancelar.setIcon(new ThemeResource("images/botones/undo.png"));
		btnCancelar.setImmediate(true);
		btnCancelar.setWidth("110px");
		btnCancelar.setHeight("-1px");
		mainLayout.addComponent(btnCancelar, "top:131.0px;left:487.0px;");

		// btnInicio
		btnInicio = new Boton();
		btnInicio.setIcon(new ThemeResource("images/botones/start.png/"));
		btnInicio.setImmediate(true);
		btnInicio.setWidth("30px");
		btnInicio.setHeight("-1px");
		mainLayout.addComponent(btnInicio, "top:273.0px;left:47.0px;");

		// btnAtras
		btnAtras = new Boton();
		btnAtras.setIcon(new ThemeResource("images/botones/previous.png"));
		btnAtras.setImmediate(true);
		btnAtras.setWidth("30px");
		btnAtras.setHeight("-1px");
		mainLayout.addComponent(btnAtras, "top:273.0px;left:75.0px;");

		// lblPagina
		lblPagina = new Label();
		lblPagina.setImmediate(false);
		lblPagina.setWidth("-1px");
		lblPagina.setHeight("-1px");
		lblPagina.setValue("P�gina");
		mainLayout.addComponent(lblPagina, "top:280.0px;left:105.0px;");

		// txtPaginaActual
		txtPaginaActual = new NumberField();
		txtPaginaActual.setImmediate(false);
		txtPaginaActual.setWidth("100.0%");
		txtPaginaActual.setHeight("-1px");
		mainLayout.addComponent(txtPaginaActual,
				"top:275.0px;right:462.0px;left:143.0px;");

		// btnSiguiente
		btnSiguiente = new Boton();
		btnSiguiente.setIcon(new ThemeResource("images/botones/next.png/"));
		btnSiguiente.setImmediate(true);
		btnSiguiente.setWidth("30px");
		btnSiguiente.setHeight("-1px");
		mainLayout.addComponent(btnSiguiente, "top:273.0px;left:239.0px;");

		// btnFin
		btnFin = new Boton();
		btnFin.setIcon(new ThemeResource("images/botones/final.png/"));
		btnFin.setImmediate(true);
		btnFin.setWidth("30px");
		btnFin.setHeight("-1px");
		mainLayout.addComponent(btnFin, "top:273.0px;right:344.0px;");

		// lblSeparador
		lblSeparador = new Label();
		lblSeparador.setImmediate(false);
		lblSeparador.setWidth("100.0%");
		lblSeparador.setHeight("-1px");
		lblSeparador.setValue("/");
		mainLayout.addComponent(lblSeparador,
				"top:275.0px;right:456.0px;left:183.0px;");

		// lblTotalPaginas
		lblTotalPaginas = new Label();
		lblTotalPaginas.setImmediate(false);
		lblTotalPaginas.setWidth("-1px");
		lblTotalPaginas.setHeight("-1px");
		lblTotalPaginas.setValue("9999");
		mainLayout.addComponent(lblTotalPaginas, "top:278.0px;left:196.0px;");

		// btnImprimir
		btnImprimir = new Button();
		btnImprimir.setCaption("Imprimir");
		btnImprimir.setIcon(new ThemeResource("images/botones/print.png"));
		btnImprimir.setImmediate(true);
		btnImprimir.setWidth("110px");
		btnImprimir.setHeight("-1px");
		mainLayout.addComponent(btnImprimir, "top:165.0px;left:364.0px;");

		return mainLayout;
	}

}
