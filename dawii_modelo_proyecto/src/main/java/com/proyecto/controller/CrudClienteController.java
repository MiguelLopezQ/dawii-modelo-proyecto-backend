package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


import com.proyecto.entidad.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudcliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudClienteController {

	@Autowired
	private ClienteService serciceC;
	
	@GetMapping("/listaPoNombre/{nom}")
	@ResponseBody
	public ResponseEntity<List<Cliente>> lista(@PathVariable("nom")String nombre){
		
		List<Cliente> salida = null;
		try {
			if(nombre.equals("todos")) {
				salida =serciceC.listaCliente();
			}
			else {
			salida = serciceC.listaPoNombre("%"+nombre+"%");
			}
			} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/registraCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Cliente obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Cliente>lstCliet= serciceC.listDniCliient(obj.getDni());
			if (lstCliet.isEmpty()) {
				obj.setIdCliente(0);
			  String f2 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
				Date fecha2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f2);
				obj.setFechaRegistro(fecha2);
				obj.setEstado(1);
				
				Cliente objsalida = serciceC.insertUpdateCliente(obj);
				if(objsalida==null) {
					salida.put("mensaje","Error en el registro");
				}else {
					salida.put("mensaje", "Registro exitosos");
				}
			}else {
				salida.put("mensaje","El Dni ya existe:"+obj.getDni());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			salida.put("mensaje", "Error en el registro"+e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCliente(@RequestBody Cliente obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Cliente objSalida = serciceC.insertUpdateCliente(obj);
			if (objSalida == null) {
				salida.put("mensaje","Error en el Actualizar");
			}else {
				salida.put("mensaje", "Actualizacion exitosa");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			salida.put("mensaje", "El Cliente no existe "+ obj.getDni());
		}
		return ResponseEntity.ok(salida);
	}
	

	@DeleteMapping("/eliminaCliente/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Cliente opt = serciceC.buscaCliente(id);
			if (opt != null) {
				serciceC.eliminaCliente(id);
				Cliente optDocente = serciceC.buscaCliente(id);
				if (optDocente==null) {
					salida.put("mensaje", "El Cliente fue eliminado");
				} else {
					salida.put("mensaje", "El cliente tiene esta relacionado");
				}
			}else {
				salida.put("mensaje","El Cliente no existe");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
}
