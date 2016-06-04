package com.bstore.services.persistence.pojo;
// Generated 02-jun-2016 21:47:36 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Coleccion generated by hbm2java
 */
public class Coleccion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6510907094497582463L;
	private int id;
	private int estatus;
	private String nombre;
	private Date fechaUmodif;
	private int idUsuarioUmodif;
	private String nombreMostrar;
	private Integer ranking;

	public Coleccion() {
	}

	public Coleccion(int id, int estatus, String nombre, Date fechaUmodif, int idUsuarioUmodif) {
		this.id = id;
		this.estatus = estatus;
		this.nombre = nombre;
		this.fechaUmodif = fechaUmodif;
		this.idUsuarioUmodif = idUsuarioUmodif;
	}

	public Coleccion(int id, int estatus, String nombre, Date fechaUmodif, int idUsuarioUmodif, String nombreMostrar,
			Integer ranking) {
		this.id = id;
		this.estatus = estatus;
		this.nombre = nombre;
		this.fechaUmodif = fechaUmodif;
		this.idUsuarioUmodif = idUsuarioUmodif;
		this.nombreMostrar = nombreMostrar;
		this.ranking = ranking;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdUsuarioUmodif() {
		return this.idUsuarioUmodif;
	}

	public void setIdUsuarioUmodif(int idUsuarioUmodif) {
		this.idUsuarioUmodif = idUsuarioUmodif;
	}

	public String getNombreMostrar() {
		return this.nombreMostrar;
	}

	public void setNombreMostrar(String nombreMostrar) {
		this.nombreMostrar = nombreMostrar;
	}

	public Integer getRanking() {
		return this.ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "Coleccion [id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", fechaUmodif=" + fechaUmodif
				+ ", idUsuarioUmodif=" + idUsuarioUmodif + ", nombreMostrar=" + nombreMostrar + ", ranking=" + ranking
				+ "]";
	}
	
}
