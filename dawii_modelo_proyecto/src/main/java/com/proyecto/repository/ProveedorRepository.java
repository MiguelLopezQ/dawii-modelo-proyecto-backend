package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor,Integer>{

	@Query(value = "select e.* from Proveedor e where e.ruc = ?1",nativeQuery = true)
	public List<Proveedor> listaPorRuc(String ruc);
	
	//JPQL
    //Query no con tablas sino con clases que tienen @Entity
	@Query("select p from Proveedor p where (?1 is '' or p.razonsocial like ?1) and (?2 is '' or p.ruc = ?2) and (?3 is -1 or p.ubigeo.idUbigeo = ?3) and p.estado = ?4")       
	public List<Proveedor> listaProveedorPorRazonSocialRucUbigeo(String razonsocial, String ruc, int idUbigeo, int estado);
	
	//PC3_Chavez
	@Query("select p from Proveedor p where p.razonsocial like ?1")
	public List<Proveedor> listaPorRazonSocialLike(String razonsocial);

	
	
}
