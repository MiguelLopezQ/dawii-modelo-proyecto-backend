package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proyecto.entidad.Reclamo;
import com.proyecto.service.ReclamoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/reclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReclamoController {
    @Autowired
    private ReclamoService servicio;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Reclamo>> listarReclamos() {
        return ResponseEntity.ok(servicio.listarReclamos());
    }

    @GetMapping("/id/{id}") @ResponseBody
    public ResponseEntity<Optional<Reclamo>> listaPorId(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(servicio.listaPorId(id));
    }

    @GetMapping("/parametros") @ResponseBody
    public ResponseEntity<Map<String, Object>> listarPorParametros(
        @RequestParam(name = "descripcionReclamo", required = false, defaultValue = "") String descripcion,
        @RequestParam(name = "idTipoReclamo", required = false, defaultValue = "-1") String idTipoReclamo,
        @RequestParam(name = "idCliente", required = false, defaultValue = "-1") String idCliente,
        @RequestParam(name = "estadoReclamo", required = false, defaultValue = "1") int estadoReclamo
    ) {
        Map<String, Object> salida = new HashMap<>();

        try {
            List<Reclamo> lista = servicio.listaParametros("%"+descripcion+"%", idTipoReclamo, idCliente, estadoReclamo);

            if(CollectionUtils.isEmpty(lista)) {
                salida.put("mensaje", "No hay datos para mostrar");
            } else {
                salida.put("data", lista);
                salida.put("mensaje", "Existen " + lista.size() + " datos para mostrar");
            }
        }

        catch(Exception e) {
            e.printStackTrace();
            salida.put("mensaje", e.getMessage());
        }

        return ResponseEntity.ok(salida);
    }

    @PostMapping @ResponseBody
    public ResponseEntity<HashMap<String, Object>> InsertReclaim(@RequestBody Reclamo obj) {
        HashMap<String, Object> salida = new HashMap<String, Object>();
        try {
            obj.setIdReclamo(0);
            obj.setFechaRegistro(new Date());
            obj.setEstado(1);
            
            Reclamo objSalida = servicio.insertarActualizar(obj);
            
            if(objSalida == null)
                salida.put("mensaje", "Error al insertar");
            else
                salida.put("mensaje", "Insertado correcto");
        }

        catch(Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "Error " + e.getMessage());
        }

        return ResponseEntity.ok(salida);
    }
}
