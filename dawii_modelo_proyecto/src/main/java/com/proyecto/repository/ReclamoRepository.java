package com.proyecto.repository;

import java.util.List;

import com.proyecto.entidad.Reclamo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    @Query("select r from Reclamo r where (?1 is '' or r.descripcion like ?1) and (?2 is '-1' or r.tipoReclamo.idTipoReclamo = ?2) and (?3 is '-1' or r.cliente.idCliente = ?3) and r.estado = ?4")
    public List<Reclamo> listaParametros(String descripcion, String idTipoReclamo, String idCliente, int estadoReclamo);
}
