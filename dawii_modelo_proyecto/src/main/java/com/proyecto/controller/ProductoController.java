package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/producto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProductoController {
	
	@Autowired
	private ProductoService serciceProducto;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Producto>> ListProducto(){
		List<Producto> lista=serciceProducto.listaProducto();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertCliente(@RequestBody Producto obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Producto>listaSerie= serciceProducto.listaPorSerie(obj.getSerie());
			if (listaSerie.isEmpty()) {
				obj.setIdProducto(0);
								
				String f2 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
				Date fecha2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f2);
								
				obj.setFechaRegistro(fecha2);
				obj.setEstado(1);
				
				Producto objsalida = serciceProducto.insertUpdateProducto(obj);
				
				if(objsalida==null) {
					salida.put("mensaje","Error en el registro");
				}else {
					salida.put("mensaje", "Registro exitosos");
				}
			}else {
				salida.put("mensaje","La serie ya existe:"+obj.getSerie());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro"+e.getMessage());
		}		
		
		return ResponseEntity.ok(salida);
	}
	

}
