package com.proyecto.service;

import java.util.List;
import java.util.Optional;


import com.proyecto.entidad.Sede;

public interface SedeService {
  //continuando
	public abstract List<Sede> listaSede();
	public abstract List<Sede> listaSedePorDireccion(String direccion);

    
  //pc02 service
  	public abstract List<Sede> listaSedeNombreDireccionCodigoPostalPais(String nombre, String direccion, String codigoPostal, int idPais, int estado);

//pc03 crud in
	public abstract Sede insertaActualizaSede(Sede obj);
  	public abstract List<Sede> listaSedePorNombreLike(String nombre);
  	public abstract void eliminaSede(int id);
	public abstract Optional<Sede> buscaSede(int id);
}
