package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {
	//Autor : Cristian Chavez
	
		@Autowired
		private ProveedorService proveedorService;

		@GetMapping
		@ResponseBody
		public ResponseEntity<List<Proveedor>> listaModalidad(){
			List<Proveedor> lista = proveedorService.listaProveedor();
			return ResponseEntity.ok(lista);
		}
		
		@PostMapping
		@ResponseBody
		public  ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj){
			Map<String, Object> salida = new HashMap<>();
			try {
				List<Proveedor>lstProv= proveedorService.listRucProveedor(obj.getRuc());
				if (lstProv.isEmpty()) {
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
				if (objSalida == null) {
					salida.put("mensaje", "No se registró, consulte con el administrador.");
				}else{
					salida.put("mensaje", "Se registró correctamente.");
				 }
			}else{
					salida.put("mensaje","El Ruc ya existe:"+obj.getRuc());
				}
			  } catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "No se registró, consulte con el administrador."+e.getMessage());
			}
			return ResponseEntity.ok(salida);
		}
		
		
		//PC2-CHAVEZ,CONSULTA PROVEEDOR
		@GetMapping("/listaProveedorConParametros")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> listaProveedorRazonSocialRucUbigeo(
				@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
				@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
				@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
				@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
			Map<String, Object> salida = new HashMap<>();
			try {
				List<Proveedor> lista = proveedorService.listaProveedorPorRazonSocialRucUbigeo("%"+razonsocial+"%", ruc, idUbigeo, estado);
				if (CollectionUtils.isEmpty(lista)) {
					salida.put("mensaje", "No existen datos para mostrar");
				}else {
					salida.put("lista", lista);
					salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "Error en la consulta"+e.getMessage());
			}
			return ResponseEntity.ok(salida);
		}
		
}
