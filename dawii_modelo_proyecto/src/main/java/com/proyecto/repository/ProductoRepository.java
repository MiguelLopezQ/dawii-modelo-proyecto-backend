package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query(value = "select e.* from Producto e where e.serie = ?1",nativeQuery = true)
	public List<Producto> listaPorSerie(String serie);

}
