package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;

@RestController
@RequestMapping("/rest/crudProveedor")
@CrossOrigin(origins = "http://localhost:4200")
public class CrudProveedorController {
	
	@Autowired
	private ProveedorService service;
	
	
	@GetMapping("/listaProveedorPorRazonSocialLike/{razonS}")
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedorPorRazonSocialLike(@PathVariable("razonS") String razonS) {
		List<Proveedor> lista  = null;
		try {
			if (razonS.equals("todos")) {
				lista = service.listaProveedorPorRazonSocialLike("%");
			}else {
				lista = service.listaProveedorPorRazonSocialLike("%" + razonS + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProveedor(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Proveedor objSalida =  service.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el Registro");
			} else {
				salida.put("mensaje","Proveedor Registrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje"," Error en el Registro");
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Proveedor objSalida =  service.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en la actualización");
			} else {
				salida.put("mensaje", "Proveedor Actualizado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualización");
		}
		return ResponseEntity.ok(salida);
	}
	/*
	@DeleteMapping("/eliminaProveedor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id") int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Proveedor> opt = service.buscaProveedor(id);
			if (opt.isPresent()) {
				service.eliminaProveedor(id);
				Optional<Proveedor> optDocente = service.buscaProveedor(id);
				if (optDocente.isEmpty()) {
					salida.put("mensaje", "Se eliminó correctamente.");
				} else {
					salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
				}
			} else {
				salida.put("mensaje", "No existe el ID que se desea eliminar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
		}
		return ResponseEntity.ok(salida);
	}*/
}
