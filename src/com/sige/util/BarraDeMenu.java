package com.sige.util;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;

public class BarraDeMenu extends MenuBar {

	private static final long serialVersionUID = 1L;

	public MenuElemento addItem(String codigo, String caption, Command command) {
		MenuElemento newItem = new MenuElemento(caption, null, command, codigo);
		getItems().add((MenuItem) newItem);
		requestRepaint();// replace to requestRepaint();
		return newItem;
	}

	public class MenuElemento extends MenuItem {

		private static final long serialVersionUID = 1L;
		private String codigo;

		public MenuElemento(String caption, ThemeResource icon,
				Command command, String codigo) {
			super(caption, icon, command);
			this.codigo = codigo;
		}

		public String getCodigo() {
			return codigo;
		}

		public MenuElemento addItem(String codigo, String caption,
				Command command) {
			MenuItem menuItem = super.addItem(caption, command);
			int index = getChildren().indexOf(menuItem);
			MenuElemento newItem = new MenuElemento(caption, null, command,
					codigo);
			newItem.setParent(this);
			getChildren().set(index, newItem);
			requestRepaint();// replace to requestRepaint();
			return newItem;
		}
	}
}