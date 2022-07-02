package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyecto.entidad.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query("select e from Marca e where e.nombre = ?1")
	public List<Marca> listaPorNombre(String nombre);
	
	@Query("select x from Marca x where (?1 is '' or x.nombre like ?1) and (?2 is '' or x.descripcion = ?2) and (?3 is '' or x.certificado = ?3) and (?4 is -1 or x.pais.idPais = ?4) and x.estado = ?5")
	public List<Marca> listaMarcaNombreDescripcionCertificadoEstadoPais(String nombre,String descripcion,String certificado,int idPais,int estado);

	@Query("select e from Marca e where e.nombre like ?1")
	public List<Marca> listaPorNombreLike(String nombre);
	
	@Query("select e from Marca e where e.idMarca = ?1")
	public Marca buscaMarcaPorId(int idMarca);
	
	
}
