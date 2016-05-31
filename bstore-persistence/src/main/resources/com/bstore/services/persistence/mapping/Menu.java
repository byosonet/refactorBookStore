// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Menu generated by hbm2java
 */
public class Menu implements java.io.Serializable {

	private Short id;
	private Sistema sistema;
	private int estatus;
	private String icon;
	private String nombre;
	private short orden;
	private int visible;
	private SubMenu subMenuById;
	private Set<UsuarioMenu> usuarioMenus = new HashSet<UsuarioMenu>(0);
	private Set<Perfil> perfils = new HashSet<Perfil>(0);
	private Set<SubMenu> subMenusForIdMenuPadre = new HashSet<SubMenu>(0);
	private Set<MenuUrl> menuUrls = new HashSet<MenuUrl>(0);

	public Menu() {
	}

	public Menu(Sistema sistema, int estatus, String nombre, short orden, int visible) {
		this.sistema = sistema;
		this.estatus = estatus;
		this.nombre = nombre;
		this.orden = orden;
		this.visible = visible;
	}

	public Menu(Sistema sistema, int estatus, String icon, String nombre, short orden, int visible, SubMenu subMenuById,
			Set<UsuarioMenu> usuarioMenus, Set<Perfil> perfils, Set<SubMenu> subMenusForIdMenuPadre,
			Set<MenuUrl> menuUrls) {
		this.sistema = sistema;
		this.estatus = estatus;
		this.icon = icon;
		this.nombre = nombre;
		this.orden = orden;
		this.visible = visible;
		this.subMenuById = subMenuById;
		this.usuarioMenus = usuarioMenus;
		this.perfils = perfils;
		this.subMenusForIdMenuPadre = subMenusForIdMenuPadre;
		this.menuUrls = menuUrls;
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public int getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public short getOrden() {
		return this.orden;
	}

	public void setOrden(short orden) {
		this.orden = orden;
	}

	public int getVisible() {
		return this.visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public SubMenu getSubMenuById() {
		return this.subMenuById;
	}

	public void setSubMenuById(SubMenu subMenuById) {
		this.subMenuById = subMenuById;
	}

	public Set<UsuarioMenu> getUsuarioMenus() {
		return this.usuarioMenus;
	}

	public void setUsuarioMenus(Set<UsuarioMenu> usuarioMenus) {
		this.usuarioMenus = usuarioMenus;
	}

	public Set<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(Set<Perfil> perfils) {
		this.perfils = perfils;
	}

	public Set<SubMenu> getSubMenusForIdMenuPadre() {
		return this.subMenusForIdMenuPadre;
	}

	public void setSubMenusForIdMenuPadre(Set<SubMenu> subMenusForIdMenuPadre) {
		this.subMenusForIdMenuPadre = subMenusForIdMenuPadre;
	}

	public Set<MenuUrl> getMenuUrls() {
		return this.menuUrls;
	}

	public void setMenuUrls(Set<MenuUrl> menuUrls) {
		this.menuUrls = menuUrls;
	}

}
