package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;


@RestController
@RequestMapping("/rest/crudSede")
@CrossOrigin(origins = "http://localhost:4200")
public class CrudSedeController {

	@Autowired
	private SedeService service;
	
	@GetMapping("/listaSedePorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Sede>> listaSedePorNombreLike(@PathVariable("nom") String nombre) {
		List<Sede> lista  = null;
		try {
			if (nombre.equals("todos")) {
				lista = service.listaSedePorNombreLike("%");
			}else {
				lista = service.listaSedePorNombreLike("%" + nombre + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	@PostMapping("/registraSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaSede(@RequestBody Sede obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdSede(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Sede objSalida =  service.insertaActualizaSede(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro ");
			} else {
				salida.put("mensaje", "Registro exitoso");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro" + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	@PutMapping("/actualizaSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaSede(@RequestBody Sede obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Sede objSalida =  service.insertaActualizaSede(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en la actualización ");
			} else {
				salida.put("mensaje", "Actualización exitosa");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualización" + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	@DeleteMapping("/eliminaSede/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaSede(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Sede> opt = service.buscaSede(id);
			if (opt.isPresent()) {
				service.eliminaSede(id);
				Optional<Sede> optDocente = service.buscaSede(id);
				if (optDocente.isEmpty()) {
					salida.put("mensaje", "Eliminación exitosa");
				} else {
					salida.put("mensaje", "Error en la eliminación");
				}
			}else {
				salida.put("mensaje", "No existe el ID que se desea eliminar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación" + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
}
