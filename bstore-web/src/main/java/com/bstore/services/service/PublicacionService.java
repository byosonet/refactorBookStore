package com.bstore.services.service;

import java.util.List;

import com.bstore.services.persistence.pojo.Publicacion;

public interface PublicacionService {
	List<Publicacion> getPublicacionesByColeccionID(int idColeccion);
	Publicacion getPublicacion(int id);
}