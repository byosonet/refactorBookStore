// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Holliday generated by hbm2java
 */
public class Holliday implements java.io.Serializable {

	private Integer id;
	private Sistema sistema;
	private Usuario usuario;
	private int estatus;
	private Date holliday;
	private Date fechaUmodif;

	public Holliday() {
	}

	public Holliday(Usuario usuario, int estatus, Date holliday, Date fechaUmodif) {
		this.usuario = usuario;
		this.estatus = estatus;
		this.holliday = holliday;
		this.fechaUmodif = fechaUmodif;
	}

	public Holliday(Sistema sistema, Usuario usuario, int estatus, Date holliday, Date fechaUmodif) {
		this.sistema = sistema;
		this.usuario = usuario;
		this.estatus = estatus;
		this.holliday = holliday;
		this.fechaUmodif = fechaUmodif;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public Date getHolliday() {
		return this.holliday;
	}

	public void setHolliday(Date holliday) {
		this.holliday = holliday;
	}

	public Date getFechaUmodif() {
		return this.fechaUmodif;
	}

	public void setFechaUmodif(Date fechaUmodif) {
		this.fechaUmodif = fechaUmodif;
	}

}
