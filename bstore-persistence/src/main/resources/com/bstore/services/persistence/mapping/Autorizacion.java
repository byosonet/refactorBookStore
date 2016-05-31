// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Autorizacion generated by hbm2java
 */
public class Autorizacion implements java.io.Serializable {

	private Short id;
	private Sistema sistema;
	private Usuario usuario;
	private int estatus;
	private String nombre;
	private Date fechaUmodif;
	private Set<AutorizacionUsuario> autorizacionUsuarios = new HashSet<AutorizacionUsuario>(0);

	public Autorizacion() {
	}

	public Autorizacion(Sistema sistema, Usuario usuario, int estatus, String nombre, Date fechaUmodif) {
		this.sistema = sistema;
		this.usuario = usuario;
		this.estatus = estatus;
		this.nombre = nombre;
		this.fechaUmodif = fechaUmodif;
	}

	public Autorizacion(Sistema sistema, Usuario usuario, int estatus, String nombre, Date fechaUmodif,
			Set<AutorizacionUsuario> autorizacionUsuarios) {
		this.sistema = sistema;
		this.usuario = usuario;
		this.estatus = estatus;
		this.nombre = nombre;
		this.fechaUmodif = fechaUmodif;
		this.autorizacionUsuarios = autorizacionUsuarios;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaUmodif() {
		return this.fechaUmodif;
	}

	public void setFechaUmodif(Date fechaUmodif) {
		this.fechaUmodif = fechaUmodif;
	}

	public Set<AutorizacionUsuario> getAutorizacionUsuarios() {
		return this.autorizacionUsuarios;
	}

	public void setAutorizacionUsuarios(Set<AutorizacionUsuario> autorizacionUsuarios) {
		this.autorizacionUsuarios = autorizacionUsuarios;
	}

}
