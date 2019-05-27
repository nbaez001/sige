package com.sige.util;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class Notificacion extends Window {

	private Button btnOk = new Button();
	private final AbsoluteLayout mainLayout = new AbsoluteLayout();
	private final Label mensaje = new Label();
	private Window notification;

	public static void show(Notificacion notificacion) {
		UI.getCurrent().addWindow(notificacion);
	}

	public Notificacion(String msaje, int tipo) {
		super.setCaption("Mensaje del sistema");
		super.setWidth("300px");
		super.setHeight("150px");
		btnOk.setCaption("OK");
		super.setModal(true);
		super.setResizable(false);
		mensaje.setValue(msaje);
		mensaje.setContentMode(ContentMode.HTML);
		mensaje.setWidth("290px");
		mensaje.setHeight("130px");
		btnOk.setWidth("50px");
		btnOk.setHeight("-1px");
		this.notification = this;
		btnOk.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().removeWindow(notification);
			}
		});
		btnOk.addShortcutListener(new ShortcutListener("", KeyCode.ENTER, null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void handleAction(Object sender, Object target) {
				btnOk.click();
			}
		});
		mainLayout.addComponent(mensaje, "top:10.0px;left:10.0px;");
		mainLayout.addComponent(btnOk, "bottom:5.0px;right:5.0px;");
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		if (tipo == Constantes.MENSAJE.TYPE_SUCCES) {
			this.setStyleName("succes");
		} else if (tipo == Constantes.MENSAJE.TYPE_WARNING) {
			this.setStyleName("warning");
		} else if (tipo == Constantes.MENSAJE.TYPE_ERROR) {
			this.setStyleName("erro");
		}
		btnOk.focus();
		super.setContent(mainLayout);
	}
}
