package com.bstore.services.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.PublicacionService;


@Controller
public class CompraController {
	private final Logger log = Logger.getLogger(CompraController.class);
	private static final String NAME_CONTROLLER = "[--CompraController--]";
	
	@Autowired
	private PublicacionService publicacionService;
	
	@RequestMapping(value="/comprar/publicacion/{id}",method = RequestMethod.GET)
	   public String getDetalleCompra(Model model, @PathVariable("id") int id, 
			   HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Cargando detalle de compra: "+NAME_CONTROLLER);
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			model.addAttribute("menu",menu);
			log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
			
			Publicacion publicacion = this.publicacionService.getPublicacion(id);
			model.addAttribute("publicacion", publicacion);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "detalleCompra";
	   }
	
}
