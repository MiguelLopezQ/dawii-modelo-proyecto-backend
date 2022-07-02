package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	@Query(value = "select e.* from Cliente e where e.dni = ?1",nativeQuery = true)
	public List<Cliente> listaPorDni(String dni);
	
	@Query(value="select d.* from Cliente as d where (length(?1)=2 or d.nombres like ?1 )and(length(?2)=0 or d.dni=?2 )and( ?3=-1 or d.idUbigeo=?3) and  d.estado=?4",nativeQuery = true)
	public List<Cliente> listarClienteDniUbigeoEstado(String nombres,String dni,int idUbigeo,int estado);

	@Query(value = "select e.* from Cliente as e where e.nombres like ?1",nativeQuery = true)
	public List<Cliente> listaPoNombre(String nombre);
	
	@Query(value = "select e.* from Cliente as e where e.idCliente = ?1",nativeQuery = true)
	public Cliente buscarCliente( int id );

}
