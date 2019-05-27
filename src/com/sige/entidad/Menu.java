package com.sige.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sige.util.AuditoriaBean;

/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name = "menu")
public class Menu extends AuditoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String decripcion;

	private Integer orden;

	// bi-directional many-to-one association to Grupomenu
	@OneToMany(mappedBy = "menu")
	private List<GrupoMenu> grupomenus;

	// bi-directional many-to-one association to Menu
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "padre")
	private Menu menu;

	// bi-directional many-to-one association to Menu
	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
	private List<Menu> menus;

	private String url;

	public Menu() {
	}

	public String getDecripcion() {
		return this.decripcion;
	}

	public void setDecripcion(String decripcion) {
		this.decripcion = decripcion;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public List<GrupoMenu> getGrupomenus() {
		return this.grupomenus;
	}

	public void setGrupomenus(List<GrupoMenu> grupomenus) {
		this.grupomenus = grupomenus;
	}

	public GrupoMenu addGrupomenus(GrupoMenu grupomenus) {
		getGrupomenus().add(grupomenus);
		grupomenus.setMenu(this);

		return grupomenus;
	}

	public GrupoMenu removeGrupomenus(GrupoMenu grupomenus) {
		getGrupomenus().remove(grupomenus);
		grupomenus.setMenu(null);

		return grupomenus;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu addMenus(Menu menus) {
		getMenus().add(menus);
		menus.setMenu(this);

		return menus;
	}

	public Menu removeMenus(Menu menus) {
		getMenus().remove(menus);
		menus.setMenu(null);

		return menus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}