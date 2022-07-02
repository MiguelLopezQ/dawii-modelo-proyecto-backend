package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyecto.entidad.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer>{
	//empezando
    @Query("Select e from Sede e where e.direccion = ?1")
	public List<Sede> listaPorDireccion(String direccion);
    
    
// Cambios PC02:Reposi Empesando Query no con tablas sino con clases que tienen @Entity 
	
  	@Query("select x from Sede x where (?1 is '' or x.nombre like ?1) and (?2 is '' or x.direccion = ?2) and (?3 is '' or x.codigoPostal = ?3) and (?4 is -1 or x.pais.idPais = ?4) and  x.estado = ?5")       
  	public List<Sede> listaSedeNombreDireccionCodigoPostalPais(String nombre, String direccion,String codigoPostal, int idPais, int estado);

  //pc03
  	@Query("select x from Sede x where x.nombre like ?1")
	public List<Sede> listaPorNombreLike(String nombre);
}


