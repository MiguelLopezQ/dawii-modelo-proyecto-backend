package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {
    public abstract Reclamo insertarActualizar(Reclamo obj);
    public abstract List<Reclamo> listarReclamos();
    public abstract Optional<Reclamo> listaPorId(int id);
    public abstract void eliminarPorId(int id);

    public abstract List<Reclamo> listaParametros(String descripcion, String idTipoReclamo, String idCliente, int estadoReclamo);
}
