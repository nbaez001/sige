package com.sige.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.vaadin.dialogs.ConfirmDialog;

import com.sige.entidad.Dependencia;
import com.sige.entidad.Expediente;
import com.sige.entidad.ExpedienteEscaneado;
import com.sige.servicio.ExpedienteEscaneadoServicio;
import com.sige.servicio.UtilService;
import com.sige.util.Constantes;
import com.sige.util.Injector;
import com.sige.util.Notificacion;
import com.sige.util.ReceiverCustom;
import com.sige.util.SigeUtil;
import com.sige.util.TextField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Window;

public class ExpedienteAdjuntar extends CustomComponent implements
		ClickListener {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label lblNombreArchivo;
	@AutoGenerated
	private Label lblExpedienteValue;
	@AutoGenerated
	private Label lblExpediente;
	@AutoGenerated
	private Button cancelProcessing;
	@AutoGenerated
	private HorizontalLayout progressLayout;
	@AutoGenerated
	private Label lblUsuarioValue;
	@AutoGenerated
	private Label lblDependenciaValue;
	@AutoGenerated
	private Label lblUsuario;
	@AutoGenerated
	private Label lblDependencia;
	@AutoGenerated
	private Button btnCancelar;
	@AutoGenerated
	private Button btnEliminar;
	@AutoGenerated
	private Button btnGuardar;
	@AutoGenerated
	private Button btnNuevo;
	@AutoGenerated
	private Table tblExpedientesEscaneados;
	@AutoGenerated
	private Upload upload;
	@AutoGenerated
	private TextField txtDescripcion;
	@AutoGenerated
	private Label lblArchivo;
	@AutoGenerated
	private Label lblDescripcion;
	private Map<String, Integer> columnLenghts;
	private UtilService utilService;
	private ReceiverCustom receiver;
	@SuppressWarnings("deprecation")
	private ProgressIndicator pi;
	private SimpleDateFormat formato;
	private ExpedienteEscaneadoServicio expedienteEscaneadoServicio;
	private ExpedienteEscaneado expedienteEscaneado;
	private String nombreArchivoTemporal;
	private String nombreArchivo;
	private boolean flagEsNuevo;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	private Dependencia dependenciaSeleccionada;
	private Window windowContiene;
	private Expediente expedienteSeleccionado;
	private Object filaSeleccionada = null;

	public ExpedienteAdjuntar(Window windowContiene,
			Dependencia dependenciaSeleccionada,
			Expediente expedienteSeleccionado) {
		this.receiver = new ReceiverCustom();
		this.utilService = Injector.obtenerServicio(UtilService.class);
		this.expedienteEscaneadoServicio = Injector
				.obtenerServicio(ExpedienteEscaneadoServicio.class);
		this.receiver.setFileLocation(System.getProperty("java.io.tmpdir"));
		this.formato = new SimpleDateFormat("ddMyyyyhhmmss");
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.windowContiene = windowContiene;
		this.dependenciaSeleccionada = dependenciaSeleccionada;
		this.expedienteSeleccionado = expedienteSeleccionado;
		postBuild();
		resetearFormulario();
		getAllExpedientesEscaneados();
	}

	private void postBuild() {
		columnLenghts = utilService.getLengthColumns("expedienteescaneado");

		this.btnCancelar.setId("cancelar");
		this.btnEliminar.setId("eliminar");
		this.btnGuardar.setId("guardar");
		this.btnNuevo.setId("nuevo");

		pi = new ProgressIndicator();

		txtDescripcion.setMaxLength(columnLenghts.get("descripcion"));
		upload.setImmediate(true);
		upload.setButtonCaption("Cargar Archivo");

		lblDependenciaValue.setValue(dependenciaSeleccionada.getNombre());
		lblUsuarioValue.setValue(SigeUtil.obtenerUsuarioSesion());
		lblExpedienteValue.setValue(expedienteSeleccionado.getAnio() + "-"
				+ expedienteSeleccionado.getCodigo());

		progressLayout.setSpacing(true);
		progressLayout.setVisible(false);
		cancelProcessing.setVisible(false);
		progressLayout.addComponent(pi);
		progressLayout.setComponentAlignment(pi, Alignment.MIDDLE_LEFT);

		cancelProcessing.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				upload.interruptUpload();
			}
		});
		cancelProcessing.setStyleName("small");

		upload.addStartedListener(new Upload.StartedListener() {
			@Override
			public void uploadStarted(StartedEvent event) {
				if (txtDescripcion.isEmpty()) {
					Notificacion.show(new Notificacion(
							"Debe introducir una descripci�n",
							Constantes.MENSAJE.TYPE_ERROR));
					upload.interruptUpload();
					return;
				}
				if (event
						.getFilename()
						.substring(event.getFilename().length() - 3,
								event.getFilename().length())
						.equalsIgnoreCase("pdf")) {
					receiver.setFileName(formato.format(new Date()) + ".pdf");
					upload.setVisible(false);
					progressLayout.setVisible(true);
					cancelProcessing.setVisible(true);
					pi.setValue(0f);
					pi.setPollingInterval(500);
				} else {
					upload.interruptUpload();
					Notificacion.show(new Notificacion(
							"El sistema solo acepta archivos .pdf",
							Constantes.MENSAJE.TYPE_ERROR));
				}
			}
		});

		upload.addProgressListener(new Upload.ProgressListener() {
			public void updateProgress(long readBytes, long contentLength) {
				pi.setValue(new Float(readBytes / (float) contentLength));
			}
		});

		upload.addSucceededListener(new Upload.SucceededListener() {
			public void uploadSucceeded(SucceededEvent event) {
				if (flagEsNuevo
						&& expedienteEscaneadoServicio
								.validarDuplicado(txtDescripcion.getValue())) {
					Notificacion
							.show(new Notificacion(
									"Ya existe un expediente escaneado con esta descripcion",
									Constantes.MENSAJE.TYPE_ERROR));
					upload.interruptUpload();
					return;
				}
				nombreArchivo = event.getFilename();
				nombreArchivoTemporal = receiver.getFileLocation()
						+ receiver.getFileName();
				Notificacion.show(new Notificacion(
						"Se cargo correctamente el expediente escaneado",
						Constantes.MENSAJE.TYPE_SUCCES));
			}
		});

		upload.addFailedListener(new Upload.FailedListener() {
			@Override
			public void uploadFailed(FailedEvent event) {

			}
		});

		upload.addFinishedListener(new Upload.FinishedListener() {
			@Override
			public void uploadFinished(FinishedEvent event) {
				progressLayout.setVisible(false);
				cancelProcessing.setVisible(false);
				upload.setVisible(true);
				lblNombreArchivo.setValue(nombreArchivo);
			}
		});

		this.btnCancelar.addClickListener((ClickListener) this);
		this.btnEliminar.addClickListener((ClickListener) this);
		this.btnGuardar.addClickListener((ClickListener) this);
		this.btnNuevo.addClickListener((ClickListener) this);

		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("id", Long.class, null);
		container.addContainerProperty("item", Long.class, null);
		container.addContainerProperty("descripcion", String.class, null);
		container.addContainerProperty("archivo", String.class, null);
		container.addContainerProperty("usuario", String.class, null);

		tblExpedientesEscaneados.setContainerDataSource(container);
		tblExpedientesEscaneados.setVisibleColumns(new Object[] { "item",
				"descripcion", "archivo", "usuario" });

		tblExpedientesEscaneados.setColumnWidth("item", 25);
		tblExpedientesEscaneados.setColumnWidth("descripcion", 205);
		tblExpedientesEscaneados.setColumnWidth("nombre", 185);
		tblExpedientesEscaneados.setColumnWidth("usuario", 100);

		tblExpedientesEscaneados.setColumnHeader("item", "Item");
		tblExpedientesEscaneados.setColumnHeader("descripcion", "Descripci�n");
		tblExpedientesEscaneados.setColumnHeader("archivo", "Nombre Archivo");
		tblExpedientesEscaneados.setColumnHeader("usuario", "Usuario");

		tblExpedientesEscaneados.setColumnAlignment("descripcion",
				Table.Align.CENTER);
		tblExpedientesEscaneados.setColumnAlignment("archivo",
				Table.Align.CENTER);
		tblExpedientesEscaneados.setColumnAlignment("usuario",
				Table.Align.CENTER);
		tblExpedientesEscaneados.setColumnAlignment("item", Table.Align.CENTER);
		tblExpedientesEscaneados.setSelectable(true);
		tblExpedientesEscaneados.setImmediate(true);
		tblExpedientesEscaneados
				.addValueChangeListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						Item item = tblExpedientesEscaneados
								.getItem(tblExpedientesEscaneados.getValue());
						if (item != null) {
							btnGuardar.setCaption("Actualizar");
							btnEliminar.setEnabled(true);
							btnNuevo.setEnabled(true);
							btnGuardar.setEnabled(true);
							btnCancelar.setEnabled(true);
							upload.setEnabled(true);
							txtDescripcion.setEnabled(true);
							desactivarCampos(true);
							getExpedienteEscaneado(new Long(item
									.getItemProperty("id").getValue()
									.toString()));
							filaSeleccionada = tblExpedientesEscaneados
									.getValue();
						}
					}
				});
	}

	private void getExpedienteEscaneado(Long idExpedienteEscaneado) {
		expedienteEscaneado = expedienteEscaneadoServicio
				.obtener(idExpedienteEscaneado);
		txtDescripcion.setValue(expedienteEscaneado.getDescripcion());
	}

	private void desactivarCampos(boolean estado) {
		upload.setEnabled(estado);
		txtDescripcion.setEnabled(estado);
	}

	private void resetearFormulario() {
		this.flagEsNuevo = false;
		this.btnNuevo.setEnabled(true);
		this.btnCancelar.setEnabled(false);
		this.btnGuardar.setEnabled(false);
		lblNombreArchivo.setValue("");

		desactivarCampos(false);
		resetearComun();
	}

	private void resetearComun() {
		nombreArchivo = "";
		nombreArchivoTemporal = "";
		txtDescripcion.setValue("");
		this.btnEliminar.setEnabled(false);
	}

	private void NuevoRegistro() {
		expedienteEscaneado = new ExpedienteEscaneado();
		this.flagEsNuevo = true;
		this.btnCancelar.setVisible(true);
		this.btnCancelar.setEnabled(true);
		this.btnGuardar.setCaption("Guardar");
		this.btnGuardar.setVisible(true);
		this.btnGuardar.setEnabled(true);

		this.btnNuevo.setEnabled(false);
		desactivarCampos(true);
		resetearComun();
	}

	@SuppressWarnings("unchecked")
	private void getAllExpedientesEscaneados() {
		IndexedContainer container = (IndexedContainer) tblExpedientesEscaneados
				.getContainerDataSource();
		container.removeAllItems();
		List<ExpedienteEscaneado> expedienteEscaneadoListado = null;
		expedienteEscaneadoListado = expedienteEscaneadoServicio
				.obtenerExpedientesEscaneados(dependenciaSeleccionada,
						expedienteSeleccionado);

		Long numeroItem = 1L;
		for (int i = 0; i < expedienteEscaneadoListado.size(); i++) {
			Item item = container.addItem(i);
			item.getItemProperty("id").setValue(
					expedienteEscaneadoListado.get(i).getId());
			item.getItemProperty("item").setValue(numeroItem++);
			item.getItemProperty("descripcion").setValue(
					expedienteEscaneadoListado.get(i).getDescripcion());
			item.getItemProperty("archivo").setValue(
					expedienteEscaneadoListado.get(i).getNombreArchivoReal());
			item.getItemProperty("usuario").setValue(
					expedienteEscaneadoListado.get(i).getCreadoPor());
		}
	}

	private String obtenerNombreArchivo() {
		return "E"
				+ SigeUtil.completarCeros(
						String.valueOf(expedienteSeleccionado.getId()), 8, 1)
				+ "-D"
				+ SigeUtil.completarCeros(
						String.valueOf(dependenciaSeleccionada.getId()), 5, 1)
				+ "-A"
				+ SigeUtil.completarCeros(String
						.valueOf(expedienteEscaneadoServicio
								.obtenerCorrelativo(dependenciaSeleccionada,
										expedienteSeleccionado) + 1), 5, 1)
				+ ".pdf";
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getSource() == this.btnNuevo) {
			NuevoRegistro();
			this.txtDescripcion.focus();
		} else {
			if (event.getSource() == this.btnGuardar) {
				if (SigeUtil.validarCamposTexto(txtDescripcion)) {
					expedienteEscaneado.setDescripcion(txtDescripcion
							.getValue());
					expedienteEscaneado.setExpediente(expedienteSeleccionado);
					expedienteEscaneado.setDependencia(dependenciaSeleccionada);
					if (this.flagEsNuevo) {
						if (nombreArchivo.isEmpty()) {
							Notificacion.show(new Notificacion(
									"Debe cargar un archivo",
									Constantes.MENSAJE.TYPE_ERROR));
							return;
						}
						expedienteEscaneado
								.setNombreArchivo(obtenerNombreArchivo());
						expedienteEscaneado.setNombreArchivoReal(nombreArchivo);
						expedienteEscaneado
								.setNombreArchivoTemporal(nombreArchivoTemporal);
						expedienteEscaneadoServicio.crear(expedienteEscaneado);
						Notificacion
								.show(new Notificacion(
										"Se guardo correctamente el expediente escaneado",
										Constantes.MENSAJE.TYPE_SUCCES));
					} else {
						if (!nombreArchivo.isEmpty()) {
							expedienteEscaneado
									.setNombreArchivo(obtenerNombreArchivo());
							expedienteEscaneado
									.setNombreArchivoReal(nombreArchivo);
							expedienteEscaneado
									.setNombreArchivoTemporal(nombreArchivoTemporal);
						}
						expedienteEscaneadoServicio
								.actualizar(expedienteEscaneado);
						Notificacion
								.show(new Notificacion(
										"Se actualizo correctamente el expediente escaneado",
										Constantes.MENSAJE.TYPE_SUCCES));
					}
					if (this.filaSeleccionada != null) {
						tblExpedientesEscaneados
								.unselect(this.filaSeleccionada);
					}
					resetearFormulario();
					getAllExpedientesEscaneados();
				} else {
					Notificacion.show(new Notificacion(
							"Debe ingresar una descripci�n",
							Constantes.MENSAJE.TYPE_ERROR));
				}
			} else {
				if (event.getSource() == this.btnCancelar) {
					resetearFormulario();
				} else {
					if (event.getSource() == this.btnEliminar) {
						ConfirmDialog.show(UI.getCurrent(), "Confirmaci�n",
								"�Desea elminar el expediente escaneado?",
								"Aceptar", "Cancelar",
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											expedienteEscaneadoServicio
													.eliminar(expedienteEscaneado);
											resetearFormulario();
											getAllExpedientesEscaneados();
										}
									}
								});
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
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// lblDescripcion
		lblDescripcion = new Label();
		lblDescripcion.setImmediate(false);
		lblDescripcion.setWidth("-1px");
		lblDescripcion.setHeight("-1px");
		lblDescripcion.setValue("Descripci�n :");
		mainLayout.addComponent(lblDescripcion, "top:63.0px;left:20.0px;");

		// lblArchivo
		lblArchivo = new Label();
		lblArchivo.setImmediate(false);
		lblArchivo.setWidth("-1px");
		lblArchivo.setHeight("-1px");
		lblArchivo.setValue("Archivo :");
		mainLayout.addComponent(lblArchivo, "top:99.0px;left:20.0px;");

		// txtDescripcion
		txtDescripcion = new TextField();
		txtDescripcion.setImmediate(false);
		txtDescripcion.setWidth("463px");
		txtDescripcion.setHeight("-1px");
		mainLayout.addComponent(txtDescripcion, "top:60.0px;left:97.0px;");

		// upload
		upload = new Upload(null, receiver);
		upload.setImmediate(false);
		upload.setWidth("-1px");
		upload.setHeight("-1px");
		mainLayout.addComponent(upload, "top:95.0px;left:80.0px;");

		// tblExpedientesEscaneados
		tblExpedientesEscaneados = new Table();
		tblExpedientesEscaneados.setImmediate(false);
		tblExpedientesEscaneados.setWidth("540px");
		tblExpedientesEscaneados.setHeight("220px");
		mainLayout.addComponent(tblExpedientesEscaneados,
				"top:130.0px;left:20.0px;");

		// btnNuevo
		btnNuevo = new Button();
		btnNuevo.setCaption("Nuevo");
		btnNuevo.setIcon(new ThemeResource("images/botones/new.png"));
		btnNuevo.setImmediate(true);
		btnNuevo.setWidth("110px");
		btnNuevo.setHeight("-1px");
		mainLayout.addComponent(btnNuevo, "top:364.0px;left:40.0px;");

		// btnGuardar
		btnGuardar = new Button();
		btnGuardar.setCaption("Guardar");
		btnGuardar.setIcon(new ThemeResource("images/botones/save.png"));
		btnGuardar.setImmediate(true);
		btnGuardar.setWidth("110px");
		btnGuardar.setHeight("-1px");
		mainLayout.addComponent(btnGuardar, "top:364.0px;left:170.0px;");

		// btnEliminar
		btnEliminar = new Button();
		btnEliminar.setCaption("Eliminar");
		btnEliminar.setIcon(new ThemeResource("images/botones/delete.png"));
		btnEliminar.setImmediate(true);
		btnEliminar.setWidth("110px");
		btnEliminar.setHeight("-1px");
		mainLayout.addComponent(btnEliminar, "top:364.0px;left:300.0px;");

		// btnCancelar
		btnCancelar = new Button();
		btnCancelar.setCaption("Cancelar");
		btnCancelar.setIcon(new ThemeResource("images/botones/undo.png"));
		btnCancelar.setImmediate(true);
		btnCancelar.setWidth("110px");
		btnCancelar.setHeight("-1px");
		mainLayout.addComponent(btnCancelar, "top:364.0px;left:431.0px;");

		// lblDependencia
		lblDependencia = new Label();
		lblDependencia.setImmediate(false);
		lblDependencia.setWidth("-1px");
		lblDependencia.setHeight("-1px");
		lblDependencia.setValue("Dependencia :");
		mainLayout.addComponent(lblDependencia, "top:11.0px;left:20.0px;");

		// lblUsuario
		lblUsuario = new Label();
		lblUsuario.setImmediate(false);
		lblUsuario.setWidth("-1px");
		lblUsuario.setHeight("-1px");
		lblUsuario.setValue("Usuario :");
		mainLayout.addComponent(lblUsuario, "top:37.0px;left:20.0px;");

		// lblDependenciaValue
		lblDependenciaValue = new Label();
		lblDependenciaValue.setImmediate(false);
		lblDependenciaValue.setWidth("-1px");
		lblDependenciaValue.setHeight("-1px");
		lblDependenciaValue.setValue("Label");
		mainLayout
				.addComponent(lblDependenciaValue, "top:11.0px;left:104.0px;");

		// lblUsuarioValue
		lblUsuarioValue = new Label();
		lblUsuarioValue.setImmediate(false);
		lblUsuarioValue.setWidth("-1px");
		lblUsuarioValue.setHeight("-1px");
		lblUsuarioValue.setValue("Label");
		mainLayout.addComponent(lblUsuarioValue, "top:37.0px;left:73.0px;");

		// progressLayout
		progressLayout = new HorizontalLayout();
		progressLayout.setImmediate(false);
		progressLayout.setWidth("148px");
		progressLayout.setHeight("28px");
		progressLayout.setMargin(false);
		mainLayout.addComponent(progressLayout, "top:95.0px;left:92.0px;");

		// cancelProcessing
		cancelProcessing = new Button();
		cancelProcessing.setCaption("Cancelar");
		cancelProcessing.setImmediate(true);
		cancelProcessing.setWidth("-1px");
		cancelProcessing.setHeight("-1px");
		mainLayout.addComponent(cancelProcessing, "top:97.0px;left:240.0px;");

		// lblExpediente
		lblExpediente = new Label();
		lblExpediente.setImmediate(false);
		lblExpediente.setWidth("-1px");
		lblExpediente.setHeight("-1px");
		lblExpediente.setValue("Expediente :");
		mainLayout.addComponent(lblExpediente, "top:37.0px;left:318.0px;");

		// lblExpedienteValue
		lblExpedienteValue = new Label();
		lblExpedienteValue.setImmediate(false);
		lblExpedienteValue.setWidth("-1px");
		lblExpedienteValue.setHeight("-1px");
		lblExpedienteValue.setValue("Label");
		mainLayout.addComponent(lblExpedienteValue, "top:37.0px;left:390.0px;");

		// lblNombreArchivo
		lblNombreArchivo = new Label();
		lblNombreArchivo.setImmediate(false);
		lblNombreArchivo.setWidth("360px");
		lblNombreArchivo.setHeight("28px");
		lblNombreArchivo.setValue("Label");
		mainLayout.addComponent(lblNombreArchivo, "top:95.0px;left:200.0px;");

		return mainLayout;
	}

}