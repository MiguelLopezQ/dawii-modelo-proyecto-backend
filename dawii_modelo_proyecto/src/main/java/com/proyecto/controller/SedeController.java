package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SedeController {
//Soy Palomino .Control. Registro sede .En la base se añadio un nuevo registro
	
	@Autowired
	private SedeService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sede>> listaSede(){
		List<Sede> lista=service.listaSede();	
		return ResponseEntity.ok(lista);
		
	}
	
	   @PostMapping
	   @ResponseBody
        public ResponseEntity<HashMap<String, Object>> insertaSede(@RequestBody Sede obj){
        	HashMap<String, Object> salida = new HashMap<String, Object>();
        	
        	try {
        		List<Sede> lstSedes = service.listaSedePorDireccion(obj.getDireccion());
        		if (CollectionUtils.isEmpty(lstSedes)) {
        			obj.setIdSede(0);
        			Sede objSalida = service.insertaActualizaSede(obj);
        			if (objSalida == null) {
    					salida.put("mensaje", "Error en el registro ");
    				}else {
    					salida.put("mensaje", "Registro exitoso");
    				}
    			}else {
    				salida.put("mensaje", "La Direccion ya existe : " + obj.getDireccion());
    			}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "Error en el registro" + e.getMessage());
			}
        	
        	return ResponseEntity.ok(salida);
        	}
	
	   
	   
		@GetMapping("/direccion/{direccion}")
		@ResponseBody
		public ResponseEntity<List<Sede>> listaSedePorDireccion(@PathVariable String direccion){
	         List<Sede> lista = service.listaSedePorDireccion(direccion);
			return ResponseEntity.ok(lista);
			
		}
		//pc02 Controll-Cambio Palomino
		@GetMapping("/listaPorParametros")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> listaSedeNombreDireccionCodigoPostalPais(
				@RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
				@RequestParam(name = "direccion", required = false, defaultValue = "") String direccion,
				@RequestParam(name = "codigoPostal", required = false, defaultValue = "") String codigoPostal,
				@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
				@RequestParam(name = "estado", required = true, defaultValue = "1") int estado){			
			Map<String, Object> salida = new HashMap<>();
			try {
				List<Sede> lista = service.listaSedeNombreDireccionCodigoPostalPais("%"+nombre+"%", direccion, codigoPostal, idPais, estado);
				if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
				}else {
					salida.put("lista", lista);
					salida.put("mensaje", "Existen " + lista.size() + " datos para mostrar");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "No se filtro, consulte con el administrador."+e.getMessage());
			}
			
			return ResponseEntity.ok(salida);
		}
	   
}
