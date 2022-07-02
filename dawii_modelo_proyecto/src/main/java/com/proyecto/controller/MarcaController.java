package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Marca;
import com.proyecto.service.MarcaService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/marca")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MarcaController {
	// Autor : Miguel Lopez
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Marca>> listaMarca(){
		List<Marca> lista=marcaService.listaMarca();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaMarca(@RequestBody Marca obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Marca> lstMarcas = marcaService.listaMarcaPorNombre(obj.getNombre());
			if (org.springframework.util.CollectionUtils.isEmpty(lstMarcas)) {
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Marca objSalida = marcaService.insertaActualizaMarca(obj);
				
				if (objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso");
				}
			}else {
				salida.put("mensaje", "La Marca ya existe : " + obj.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaMarcaConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaMarcaNombreDescripcionCertificadoEstadoPais(
			@RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(name = "descripcion", required = false, defaultValue = "") String descripcion,
			@RequestParam(name = "certificado", required = false, defaultValue = "") String certificado,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Marca> lista = marcaService.listaMarcaNombreDescripcionCertificadoEstadoPais("%"+nombre+"%",descripcion,certificado,idPais ,estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se filtro, consulte con el administrador."+e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaMarcaPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Marca>> listaMarcaPorNombreLike(@PathVariable("nom") String nom) {
		List<Marca> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = marcaService.listaMarcaPorNombreLike("%");
			}else {
				lista = marcaService.listaMarcaPorNombreLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaMarca2(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdMarca(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Marca objSalida =  marcaService.insertaActualizaMarca(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro ");
			} else {
				salida.put("mensaje", "Registro exitoso");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro ");
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Marca objSalida =  marcaService.insertaActualizaMarca(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se actualizó, consulte con el administrador.");
			} else {
				salida.put("mensaje", "Se actualizó correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se actualizó, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminaMarca/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaMarca(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Marca opt = marcaService.buscaMarcaPorId(id);
			if (opt != null) {
				marcaService.eliminaMarca(id);
				Marca optMarca = marcaService.buscaMarcaPorId(id);
				if (optMarca == null) {
					salida.put("mensaje", "Se eliminó correctamente.");
				} else {
					salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
				}
			}else {
				salida.put("mensaje", "No existe el ID que se desea eliminar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
		}
		return ResponseEntity.ok(salida);
	}

	
	
}
