package com.sige.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sige.entidad.Grupo;
import com.sige.entidad.GrupoMenu;
import com.sige.entidad.Menu;
import com.sige.servicio.GrupoMenuServicio;
import com.sige.servicio.GrupoServicio;
import com.sige.servicio.UtilService;
import com.sige.util.Constantes;
import com.sige.util.Injector;
import com.sige.util.Notificacion;
import com.sige.util.Permiso;
import com.sige.util.SigeUtil;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class PermisosMantenimiento extends CustomComponent implements
		ClickListener {

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Panel pnlPermisos;

	@AutoGenerated
	private AbsoluteLayout permisosLayout;

	@AutoGenerated
	private Button btnImprimir;

	@AutoGenerated
	private Button btnBuscar;

	@AutoGenerated
	private Button btnEliminar;

	@AutoGenerated
	private Button btnNuevo;

	@AutoGenerated
	private Button btnGuardar;

	@AutoGenerated
	private TreeTable treeMenu;

	@AutoGenerated
	private ComboBox cbxCargo;

	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private Logger logger = Logger.getLogger(getClass());
	private GrupoServicio grupoServicio;
	private GrupoMenuServicio grupoMenuServicio;
	private List<GrupoMenu> grupoMenus;
	private Window quienLlama;
	private Permiso permiso;
	private List<GrupoMenu> gruposMenuActualizar;
	private boolean confirmacion = false;
	private Map<String, Integer> columnLenghts;
	private UtilService utilService;
	private String motivo = null;
	private Long idGrupo = null;

	public PermisosMantenimiento(Window quienLlama, Permiso permiso) {
		this.grupoServicio = Injector.obtenerServicio(GrupoServicio.class);
		this.grupoMenuServicio = Injector
				.obtenerServicio(GrupoMenuServicio.class);
		this.utilService = Injector.obtenerServicio(UtilService.class);
		this.quienLlama = quienLlama;
		this.permiso = permiso;

		buildMainLayout();
		setCompositionRoot(mainLayout);
		postBuild();
		llenarCombo();

	}

	public void obtenerMotivo(String motivo, Boolean confirmacion,
			Integer tipoOperacion) {
		logger.info("confirmacion=" + confirmacion);
		if (confirmacion) {
			this.motivo = motivo;
			this.confirmacion = true;
			if (tipoOperacion.equals(Constantes.TIPO_OPERACION.MODIFICACION)) {
				logger.info("btnGuardar.click();");
				btnGuardar.click();
			} else {
				btnEliminar.click();
			}
			this.confirmacion = false;
		}
	}

	private void llenarCombo() {
		List<Grupo> grupos = grupoServicio.obtenerTodos();
		for (Grupo grupo : grupos) {
			cbxCargo.addItem(grupo.getId());
			cbxCargo.setItemCaption(grupo.getId(), grupo.getDescripcion());
		}
		if (!grupos.isEmpty()) {
			cbxCargo.setValue(grupos.get(0).getId());
		}
	}

	private void postBuild() {
		columnLenghts = utilService.getLengthColumns("grupomenu");
		btnGuardar.addClickListener((ClickListener) this);
		btnImprimir.addClickListener((ClickListener) this);
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("menu", CheckBox.class, null);
		treeMenu.setColumnHeader("menu", "Lista de men�s del Sistema");
		treeMenu.setContainerDataSource(container);
		treeMenu.setImmediate(true);
		cbxCargo.setImmediate(true);
		cbxCargo.setFilteringMode(FilteringMode.CONTAINS);
		cbxCargo.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				cargarMenus((Long) event.getProperty().getValue());
			}
		});
		this.btnImprimir.setId("imprimir");
		this.btnEliminar.setId("eliminar");
		this.btnGuardar.setId("modificar");
		this.btnNuevo.setId("nuevo");
		this.btnBuscar.setId("consultar");
		SigeUtil.validarBotones(permiso, btnGuardar, btnEliminar, btnImprimir,
				btnNuevo, btnBuscar);
		cbxCargo.setEnabled(permiso.getModificar());
		treeMenu.setEnabled(permiso.getModificar());
	}

	private void cargarMenus(Long idGrupo) {
		this.idGrupo = idGrupo;
		treeMenu.getContainerDataSource().removeAllItems();
		if (idGrupo != null) {
			grupoMenus = grupoMenuServicio.buscarPorCodGrupo(idGrupo,
					Boolean.TRUE);
			setTreeTable();
		}
		treeMenu.setPageLength(treeMenu.size());
	}

	public void setTreeTable() {
		Collections.sort(grupoMenus, new Comparator<GrupoMenu>() {
			@Override
			public int compare(GrupoMenu o1, GrupoMenu o2) {
				return new Integer(o1.getMenu().getOrden())
						.compareTo(new Integer(o2.getMenu().getOrden()));
			}
		});
		for (GrupoMenu grupMenu : grupoMenus) {
			Menu menu = grupMenu.getMenu();
			if (menu.getMenu() == null) {
				treeMenu.addItem(
						new Object[] { addListener(new CheckBox(menu
								.getDecripcion(), grupMenu.getAcceso())) },
						menu.getId());
				if (tieneHijos(menu)) {
					agregarHijos(menu.getId());
					treeMenu.setCollapsed(menu.getId(), false);
				}
				addPermisos(grupMenu, menu.getId());
			}
		}
		postValueChange(Boolean.FALSE);
	}

	private void addPermisos(GrupoMenu grupoMenu, Long id) {
		if (grupoMenu.getMenu().getUrl() != null
				&& grupoMenu.getMenu().getUrl().length() > 0) {
			treeMenu.addItem(
					new Object[] { new CheckBox("consultar", grupoMenu
							.getConsultar()) }, id + "c");
			treeMenu.setParent(id + "c", id);
			treeMenu.addItem(
					new Object[] { new CheckBox("eliminar", grupoMenu
							.getEliminar()) }, id + "e");
			treeMenu.setParent(id + "e", id);

			treeMenu.addItem(
					new Object[] { new CheckBox("imprimir", grupoMenu
							.getImprimir()) }, id + "i");
			treeMenu.setParent(id + "i", id);

			treeMenu.addItem(new Object[] { new CheckBox("actualizar",
					grupoMenu.getModificar()) }, id + "m");
			treeMenu.setParent(id + "m", id);

			treeMenu.addItem(
					new Object[] { new CheckBox("nuevo", grupoMenu.getNuevo()) },
					id + "n");
			treeMenu.setParent(id + "n", id);
		}
	}

	private void agregarHijos(Long id) {
		for (GrupoMenu grupMenu : grupoMenus) {
			Menu menu = grupMenu.getMenu();
			if (menu.getMenu() != null && menu.getMenu().getId() == id) {
				treeMenu.addItem(
						new Object[] { addListener(new CheckBox(menu
								.getDecripcion(), grupMenu.getAcceso())) },
						menu.getId());
				if (tieneHijos(menu)) {
					agregarHijos(menu.getId());
				}
				treeMenu.setParent(menu.getId(), id);
				addPermisos(grupMenu, menu.getId());
				treeMenu.setCollapsed(menu.getId(), false);

			}
		}
	}

	private CheckBox addListener(CheckBox menu) {
		menu.setImmediate(true);
		menu.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				postValueChange((Boolean) event.getProperty().getValue());
			}
		});
		return menu;
	}

	private void postValueChange(Boolean value) {
		CheckBox temp = null;
		Item etm = null;
		for (Object id : treeMenu.getItemIds()) {
			etm = treeMenu.getItem(id);
			temp = (CheckBox) etm.getItemProperty("menu").getValue();
			if (treeMenu.getChildren(id) != null && temp.getValue() == value) {
				for (Object idHijo : treeMenu.getChildren(id)) {
					etm = treeMenu.getItem(idHijo);
					temp = (CheckBox) etm.getItemProperty("menu").getValue();
					temp.setValue(value);
					temp.setEnabled(value);
				}
			}
		}
	}

	private boolean tieneHijos(Menu menu) {
		for (GrupoMenu grupMenu : grupoMenus) {
			Menu men = grupMenu.getMenu();
			if (men.getMenu() != null && menu.getId() == men.getMenu().getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(btnGuardar)) {
			Item item = null;
			CheckBox menu = null;
			boolean flagActualizado;

			if (!confirmacion) {
				Window ventana = SigeUtil.generarBuscador(new Window(),
						"INGRESAR MOTIVO", "360", "260");
				ventana.setContent(new PanelMotivoObservacion(ventana, this,
						Constantes.TIPO_OPERACION.MODIFICACION, (columnLenghts
								.get("motivomodifica"))));
				UI.getCurrent().addWindow(ventana);
				return;
			}

			gruposMenuActualizar = new ArrayList<GrupoMenu>();
			for (GrupoMenu grupoMenu : grupoMenus) {
				flagActualizado = false;
				item = treeMenu.getItem(grupoMenu.getMenu().getId());
				if (item != null) {
					menu = (CheckBox) item.getItemProperty("menu").getValue();
					if (menu.getValue() != grupoMenu.getAcceso()) {
						grupoMenu.setAcceso(!grupoMenu.getAcceso());
						flagActualizado = true;
					}
					if (grupoMenu.getMenu().getUrl() != null
							&& grupoMenu.getMenu().getUrl().length() > 0) {
						item = treeMenu.getItem(grupoMenu.getMenu().getId()
								+ "c");
						menu = (CheckBox) item.getItemProperty("menu")
								.getValue();
						if (menu.getValue() != grupoMenu.getConsultar()) {
							grupoMenu.setConsultar(!grupoMenu.getConsultar());
							flagActualizado = true;
						}
						item = treeMenu.getItem(grupoMenu.getMenu().getId()
								+ "e");
						menu = (CheckBox) item.getItemProperty("menu")
								.getValue();
						if (menu.getValue() != grupoMenu.getEliminar()) {
							grupoMenu.setEliminar(!grupoMenu.getEliminar());
							flagActualizado = true;
						}
						item = treeMenu.getItem(grupoMenu.getMenu().getId()
								+ "i");
						menu = (CheckBox) item.getItemProperty("menu")
								.getValue();
						if (menu.getValue() != grupoMenu.getImprimir()) {
							grupoMenu.setImprimir(!grupoMenu.getImprimir());
							flagActualizado = true;
						}
						item = treeMenu.getItem(grupoMenu.getMenu().getId()
								+ "m");
						menu = (CheckBox) item.getItemProperty("menu")
								.getValue();
						if (menu.getValue() != grupoMenu.getModificar()) {
							grupoMenu.setModificar(!grupoMenu.getModificar());
							flagActualizado = true;
						}
						item = treeMenu.getItem(grupoMenu.getMenu().getId()
								+ "n");
						menu = (CheckBox) item.getItemProperty("menu")
								.getValue();
						if (menu.getValue() != grupoMenu.getNuevo()) {
							grupoMenu.setNuevo(!grupoMenu.getNuevo());
							flagActualizado = true;
						}
					}
					if (flagActualizado) {
						grupoMenu.setMotivoModificacion(motivo);
						gruposMenuActualizar.add(grupoMenu);
					}
				}
			}
			if (gruposMenuActualizar.size() > 0) {
				grupoMenuServicio.actualizarTodos(gruposMenuActualizar);
				Notificacion.show(new Notificacion(
						"Se guardaron correctamente los cambios",
						Constantes.MENSAJE.TYPE_SUCCES));
				cargarMenus((Long) cbxCargo.getValue());
			} else {
				Notificacion.show(new Notificacion(
						"No se encontr� ningun cambio",
						Constantes.MENSAJE.TYPE_ERROR));
			}
		} else if (event.getSource() == this.btnImprimir) {
			Window ventana = SigeUtil.generarBuscador(new Window(), "IMPRIMIR",
					"800px", "550px");
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("grupoId", idGrupo);
			ventana.setContent(new PDFVizualizador(this, permiso, parametros,
					null, "PermisosImprimir", null));
			UI.getCurrent().addWindow(ventana);
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

		// pnlPermisos
		pnlPermisos = buildPnlPermisos();
		mainLayout.addComponent(pnlPermisos, "top:10.0px;left:20.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPnlPermisos() {
		// common part: create layout
		pnlPermisos = new Panel();
		pnlPermisos.setImmediate(false);
		pnlPermisos.setWidth("460px");
		pnlPermisos.setHeight("480px");

		// permisosLayout
		permisosLayout = buildPermisosLayout();
		pnlPermisos.setContent(permisosLayout);

		return pnlPermisos;
	}

	@AutoGenerated
	private AbsoluteLayout buildPermisosLayout() {
		// common part: create layout
		permisosLayout = new AbsoluteLayout();
		permisosLayout.setImmediate(false);
		permisosLayout.setWidth("100.0%");
		permisosLayout.setHeight("100.0%");

		// cbxCargo
		cbxCargo = new ComboBox();
		cbxCargo.setImmediate(false);
		cbxCargo.setWidth("421px");
		cbxCargo.setHeight("24px");
		cbxCargo.setTabIndex(1);
		permisosLayout.addComponent(cbxCargo, "top:21.0px;left:18.0px;");

		// treeMenu
		treeMenu = new TreeTable();
		treeMenu.setImmediate(false);
		treeMenu.setWidth("420px");
		treeMenu.setHeight("360px");
		treeMenu.setTabIndex(2);
		permisosLayout.addComponent(treeMenu, "top:61.0px;left:19.0px;");

		// btnGuardar
		btnGuardar = new Button();
		btnGuardar.setCaption("Guardar");
		btnGuardar.setIcon(new ThemeResource("images/botones/save.png"));
		btnGuardar.setImmediate(true);
		btnGuardar.setWidth("85px");
		btnGuardar.setHeight("-1px");
		btnGuardar.setTabIndex(7);
		permisosLayout.addComponent(btnGuardar, "top:436.0px;left:353.0px;");

		// btnNuevo
		btnNuevo = new Button();
		btnNuevo.setCaption("Nuevo");
		btnNuevo.setIcon(new ThemeResource("images/botones/new.png"));
		btnNuevo.setImmediate(true);
		btnNuevo.setWidth("85px");
		btnNuevo.setHeight("-1px");
		btnNuevo.setTabIndex(3);
		permisosLayout.addComponent(btnNuevo, "top:436.0px;left:5.0px;");

		// btnEliminar
		btnEliminar = new Button();
		btnEliminar.setCaption("Eliminar");
		btnEliminar.setIcon(new ThemeResource("images/botones/delete.png"));
		btnEliminar.setImmediate(true);
		btnEliminar.setWidth("85px");
		btnEliminar.setHeight("-1px");
		btnEliminar.setTabIndex(4);
		permisosLayout.addComponent(btnEliminar, "top:436.0px;left:91.0px;");

		// btnBuscar
		btnBuscar = new Button();
		btnBuscar.setCaption("Buscar");
		btnBuscar.setIcon(new ThemeResource("images/botones/find.png"));
		btnBuscar.setImmediate(true);
		btnBuscar.setWidth("85px");
		btnBuscar.setHeight("-1px");
		btnBuscar.setTabIndex(5);
		permisosLayout.addComponent(btnBuscar, "top:436.0px;left:178.0px;");

		// btnImprimir
		btnImprimir = new Button();
		btnImprimir.setCaption("Imprimir");
		btnImprimir.setIcon(new ThemeResource("images/botones/print.png"));
		btnImprimir.setImmediate(true);
		btnImprimir.setWidth("85px");
		btnImprimir.setHeight("-1px");
		btnImprimir.setTabIndex(6);
		permisosLayout.addComponent(btnImprimir, "top:436.0px;left:266.0px;");

		return permisosLayout;
	}

}
