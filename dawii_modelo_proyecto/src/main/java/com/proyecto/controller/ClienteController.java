package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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


import com.proyecto.entidad.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController  {

	@Autowired
	private ClienteService serciceC;
	
	@GetMapping()
	@ResponseBody
	public ResponseEntity<List<Cliente>> ListCliente(){
		List<Cliente> lista=serciceC.listaCliente();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertCliente(@RequestBody Cliente obj){
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
			salida.put("mensaje", "Error en el registro"+e.getMessage());
		}
		
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/ConsultaCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaClienteNombreDniUbigeoyEstado(
				@RequestParam(name = "nombre",required = false, defaultValue ="") String nombre,
				@RequestParam(name = "dni",required = false, defaultValue ="") String dni,
				@RequestParam(name = "idUbigeo",required = false, defaultValue  ="-1") int idUbiego,
				@RequestParam(name = "estado",required = false) boolean estado
			){
			Map<String, Object> salida = new HashMap<>();
			try {
				int estad=1;
				if(estado==false) {
					estad=0	;
				}
				List<Cliente> lista = serciceC.listarClienteDniUbigeoEstado("%"+nombre+"%", dni, idUbiego,estad);
				if (CollectionUtils.isEmpty(lista)) {
					salida.put("mensaje", "No existe datos para mostrar");
				}else {
					salida.put("data", lista);
					salida.put("mensaje", "Existen " + lista.size() + " datos para mostrar");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "Error al consultar Cliente");
			}
		return ResponseEntity.ok(salida);

	}
	
}
