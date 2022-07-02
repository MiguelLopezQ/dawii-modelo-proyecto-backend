package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Pais;
import com.proyecto.service.PaisService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/pais")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PaisController {
	// Autor : Miguel Lopez
	
	@Autowired
	private PaisService paisService;
	
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Pais>> listaPais(){
		List<Pais> lista=paisService.listaPais();
		return ResponseEntity.ok(lista);
	}
	

	
	
}
