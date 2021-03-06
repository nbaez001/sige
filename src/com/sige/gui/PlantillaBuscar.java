package com.sige.gui;

import java.util.List;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DependenciaTipoTramite;
import com.sige.entidad.DependenciaTipoTramitePlantilla;
import com.sige.entidad.Expediente;
import com.sige.servicio.DependenciaTipoTramitePlantillaService;
import com.sige.servicio.DependenciaTipoTramiteService;
import com.sige.util.Boton;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Injector;
import com.sige.util.TextField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class PlantillaBuscar extends CustomComponent implements ClickListener {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button btnBuscar;
	@AutoGenerated
	private Label lblTotalPaginas;
	@AutoGenerated
	private Label lblSeparador;
	@AutoGenerated
	private Label llblPagina;
	@AutoGenerated
	private TextField txtPaginaActual;
	@AutoGenerated
	private Boton btnFin;
	@AutoGenerated
	private Boton btnSiguiente;
	@AutoGenerated
	private Boton btnAtras;
	@AutoGenerated
	private Boton btnInicio;
	@AutoGenerated
	private Table tblDocumento;
	@AutoGenerated
	private TextField txtPlantilla;
	@AutoGenerated
	private Label lblDocumento;
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */

	private Window windowContiene;
	private CustomComponent quienLlama;
	private Dependencia dependencia;
	private Expediente expediente;
	private DependenciaTipoTramitePlantillaService dependenciaTipoTramitePlantillaService;
	private DependenciaTipoTramiteService dependenciaTipoTramiteService;
	private DependenciaTipoTramite dependenciaTipoTramite;

	public PlantillaBuscar(CustomComponent quienLlama, Window windowContiene,
			Expediente expediente, Dependencia dependencia) {
		this.dependenciaTipoTramitePlantillaService = Injector
				.obtenerServicio(DependenciaTipoTramitePlantillaService.class);
		this.dependenciaTipoTramiteService = Injector
				.obtenerServicio(DependenciaTipoTramiteService.class);
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.quienLlama = quienLlama;
		this.windowContiene = windowContiene;
		this.dependencia = dependencia;
		this.expediente = expediente;
		postBuild();
		getAllDocumento();
		// TODO add user code here
	}

	private void postBuild() {

		this.btnBuscar.addClickListener((ClickListener) this);
		this.btnInicio.addClickListener((ClickListener) this);
		this.btnAtras.addClickListener((ClickListener) this);
		this.btnSiguiente.addClickListener((ClickListener) this);
		this.btnFin.addClickListener((ClickListener) this);
		this.btnInicio.setStyleName(Reindeer.BUTTON_LINK);
		this.btnAtras.setStyleName(Reindeer.BUTTON_LINK);
		this.btnSiguiente.setStyleName(Reindeer.BUTTON_LINK);
		this.btnFin.setStyleName(Reindeer.BUTTON_LINK);
		this.txtPaginaActual.setValue("1");

		dependenciaTipoTramite = dependenciaTipoTramiteService
				.getDependenciaTipoTramite(dependencia,
						expediente.getTipoTramite());

		this.txtPaginaActual.setId("paginaActual");

		this.txtPaginaActual.setImmediate(true);

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
					}
					txtPaginaActual.setValue(paginaActual.toString());
					getAllDocumento();
				}
			}
		});

		this.txtPlantilla.setImmediate(true);
		this.txtPlantilla.addTextChangeListener(new TextChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub
				txtPaginaActual.setValue("1");
				txtPlantilla.setValue(event.getText());
				getAllDocumento();
			}
		});

		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("id", Long.class, null);
		container.addContainerProperty("item", Long.class, null);
		container.addContainerProperty("documento", String.class, null);

		tblDocumento.setContainerDataSource(container);
		tblDocumento.setVisibleColumns(new Object[] { "item", "documento" });

		tblDocumento.setColumnWidth("item", 25);
		tblDocumento.setColumnWidth("documento", 350);

		tblDocumento.setColumnHeader("item", "Item");
		tblDocumento.setColumnHeader("documento", "Documento");

		tblDocumento.setColumnAlignment("item", Table.Align.CENTER);
		tblDocumento.setColumnAlignment("documento", Table.Align.CENTER);
		tblDocumento.setSelectable(true);
		tblDocumento.setImmediate(true);
		tblDocumento.addItemClickListener(new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				// TODO Auto-generated method stub
				if (event.isDoubleClick()) {
					Item item = tblDocumento.getItem(event.getItemId());
					respuesta(new Long(item.getItemProperty("id").getValue()
							.toString()));
				}
			}
		});
	}

	private void getAllDocumento() {
		IndexedContainer container = (IndexedContainer) tblDocumento
				.getContainerDataSource();
		container.removeAllItems();

		List<DependenciaTipoTramitePlantilla> dpttpListado = null;
		Long paginaActual = Long.parseLong(this.txtPaginaActual.getValue()
				.toString());
		DependenciaTipoTramitePlantilla dpttp = new DependenciaTipoTramitePlantilla();
		dpttp.setDependenciaTipoTramite(dependenciaTipoTramite);
		dpttp.setNombrePlantilla(txtPlantilla.getValue());
		Busqueda busqueda = dependenciaTipoTramitePlantillaService
				.buscarDependenciaTipoTramitePlantilla(dpttp, paginaActual);
		dpttpListado = (List<DependenciaTipoTramitePlantilla>) busqueda
				.getRegistos();
		this.lblTotalPaginas.setValue(busqueda.getNumeroPaginas().toString());
		this.txtPaginaActual.setValue(busqueda.getPaginaActual().toString());

		Long numeroItem = (Long.parseLong(this.txtPaginaActual.getValue()
				.toString()) - 1)
				* Constantes.PAGINACION.DEPENDENCIA_TIPO_TRAMITE_PLANTILLA + 1;
		for (int i = 0; i < dpttpListado.size(); i++) {
			Item item = container.addItem(i);
			item.getItemProperty("id").setValue(dpttpListado.get(i).getId());
			item.getItemProperty("item").setValue(numeroItem++);
			item.getItemProperty("documento").setValue(
					dpttpListado.get(i).getNombrePlantilla());
		}
	}

	private void respuesta(Long idPlantilla) {
		if (quienLlama instanceof ModificarDocumentoProceso) {
			ModificarDocumentoProceso panel = (ModificarDocumentoProceso) quienLlama;
			panel.obtenerPlantilla(idPlantilla, expediente.getId());
		}
		UI.getCurrent().removeWindow(windowContiene);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getSource() == this.btnBuscar) {
			this.txtPaginaActual.setValue("1");
			getAllDocumento();
			;
		} else {
			if (event.getSource() == this.btnSiguiente) {
				Long paginaActual = Long.parseLong(this.txtPaginaActual
						.getValue().toString()) + 1;
				if (paginaActual <= Long.parseLong(this.lblTotalPaginas
						.getValue().toString())) {
					this.txtPaginaActual.setValue(paginaActual.toString());
				}
				getAllDocumento();
				;
			} else {
				if (event.getSource() == this.btnAtras) {
					Long paginaActual = Long.parseLong(this.txtPaginaActual
							.getValue().toString()) - 1;
					if (paginaActual >= 1) {
						this.txtPaginaActual.setValue(paginaActual.toString());
					}
					getAllDocumento();
					;
				} else {
					if (event.getSource() == this.btnInicio) {
						this.txtPaginaActual.setValue("1");
						getAllDocumento();
						;
					} else {
						if (event.getSource() == this.btnFin) {
							this.txtPaginaActual.setValue(this.lblTotalPaginas
									.getValue());
							getAllDocumento();
							;
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
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// lblDocumento
		lblDocumento = new Label();
		lblDocumento.setImmediate(false);
		lblDocumento.setWidth("-1px");
		lblDocumento.setHeight("-1px");
		lblDocumento.setValue("Documento :");
		mainLayout.addComponent(lblDocumento, "top:10.0px;left:20.0px;");

		// txtPlantilla
		txtPlantilla = new TextField();
		txtPlantilla.setImmediate(false);
		txtPlantilla.setWidth("247px");
		txtPlantilla.setHeight("-1px");
		mainLayout.addComponent(txtPlantilla, "top:7.0px;left:91.0px;");

		// tblDocumento
		tblDocumento = new Table();
		tblDocumento.setImmediate(false);
		tblDocumento.setWidth("423px");
		tblDocumento.setHeight("230px");
		mainLayout.addComponent(tblDocumento, "top:50.0px;left:17.0px;");

		// btnInicio
		btnInicio = new Boton();
		btnInicio.setIcon(new ThemeResource("images/botones/start.png"));
		btnInicio.setImmediate(false);
		btnInicio.setWidth("28px");
		btnInicio.setHeight("24px");
		mainLayout.addComponent(btnInicio, "top:280.0px;left:60.0px;");

		// btnAtras
		btnAtras = new Boton();
		btnAtras.setIcon(new ThemeResource("images/botones/previous.png"));
		btnAtras.setImmediate(false);
		btnAtras.setWidth("28px");
		btnAtras.setHeight("24px");
		mainLayout.addComponent(btnAtras, "top:280.0px;left:88.0px;");

		// btnSiguiente
		btnSiguiente = new Boton();
		btnSiguiente.setIcon(new ThemeResource("images/botones/next.png"));
		btnSiguiente.setImmediate(false);
		btnSiguiente.setWidth("28px");
		btnSiguiente.setHeight("24px");
		mainLayout.addComponent(btnSiguiente, "top:280.0px;left:344.0px;");

		// btnFin
		btnFin = new Boton();
		btnFin.setIcon(new ThemeResource("images/botones/final.png"));
		btnFin.setImmediate(false);
		btnFin.setWidth("28px");
		btnFin.setHeight("24px");
		mainLayout.addComponent(btnFin, "top:280.0px;left:372.0px;");

		// txtPaginaActual
		txtPaginaActual = new TextField();
		txtPaginaActual.setImmediate(false);
		txtPaginaActual.setWidth("37px");
		txtPaginaActual.setHeight("-1px");
		mainLayout.addComponent(txtPaginaActual, "top:280.0px;left:211.0px;");

		// llblPagina
		llblPagina = new Label();
		llblPagina.setImmediate(false);
		llblPagina.setWidth("-1px");
		llblPagina.setHeight("-1px");
		llblPagina.setValue("P�gina");
		mainLayout.addComponent(llblPagina, "top:282.0px;left:162.0px;");

		// lblSeparador
		lblSeparador = new Label();
		lblSeparador.setImmediate(false);
		lblSeparador.setWidth("-1px");
		lblSeparador.setHeight("-1px");
		lblSeparador.setValue("/");
		mainLayout.addComponent(lblSeparador, "top:282.0px;left:258.0px;");

		// lblTotalPaginas
		lblTotalPaginas = new Label();
		lblTotalPaginas.setImmediate(false);
		lblTotalPaginas.setWidth("-1px");
		lblTotalPaginas.setHeight("-1px");
		lblTotalPaginas.setValue("9999");
		mainLayout.addComponent(lblTotalPaginas, "top:282.0px;left:271.0px;");

		// btnBuscar
		btnBuscar = new Button();
		btnBuscar.setCaption("Buscar");
		btnBuscar.setIcon(new ThemeResource("images/botones/find.png"));
		btnBuscar.setImmediate(true);
		btnBuscar.setWidth("100px");
		btnBuscar.setHeight("-1px");
		mainLayout.addComponent(btnBuscar, "top:7.0px;left:341.0px;");

		return mainLayout;
	}

}
