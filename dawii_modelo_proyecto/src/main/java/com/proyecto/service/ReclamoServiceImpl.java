package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Reclamo;
import com.proyecto.repository.ReclamoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamoServiceImpl implements ReclamoService {

    @Autowired
    private ReclamoRepository repositorio;

    @Override
    public Reclamo insertarActualizar(Reclamo obj) {
        return repositorio.save(obj);
    }

    @Override
    public List<Reclamo> listarReclamos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Reclamo> listaPorId(int id) {
        return repositorio.findById(id);
    }

    @Override
    public void eliminarPorId(int id) {
        repositorio.deleteById(id);        
    }

    @Override
    public List<Reclamo> listaParametros(String descripcion, String idTipoReclamo, String idCliente, int estadoReclamo) {
        return repositorio.listaParametros(descripcion, idTipoReclamo, idCliente, estadoReclamo);
    }
    
}
