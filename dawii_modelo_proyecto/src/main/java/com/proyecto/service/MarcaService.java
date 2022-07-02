package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Marca;

public interface MarcaService {

	public abstract Marca insertaActualizaMarca(Marca obj);
	public abstract List<Marca> listaMarca();
	public abstract List<Marca> listaMarcaPorNombre(String nombre); 
	
	public abstract List<Marca> listaMarcaNombreDescripcionCertificadoEstadoPais(String nombre,String descripcion,String certificado,int idPais,int estado);

	public abstract List<Marca> listaMarcaPorNombreLike(String nombre); 
	public abstract void eliminaMarca(int id);
	public abstract Marca buscaMarcaPorId(int id);
	

}
