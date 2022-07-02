package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Sede;
import com.proyecto.repository.SedeRepository;

@Service
public class SedeServiceImpl implements SedeService {
  //---
	@Autowired
	private SedeRepository repository;

	@Override
	public List<Sede> listaSede(){

		return repository.findAll();
	}



	
//pc02 servImp
@Override
public List<Sede> listaSedeNombreDireccionCodigoPostalPais(String nombre, String direccion, String codigoPostal,
		int idPais, int estado) {
	
	return repository.listaSedeNombreDireccionCodigoPostalPais(nombre, direccion, codigoPostal, idPais, estado);
}


@Override
public List<Sede> listaSedePorDireccion(String direccion) {

	return repository.listaPorDireccion(direccion);
}

//pc03 serIm

@Override
public Sede insertaActualizaSede(Sede obj) {

	return repository.save(obj);
}
@Override
public List<Sede> listaSedePorNombreLike(String nombre) {

	return repository.listaPorNombreLike(nombre);
}

@Override
public void eliminaSede(int id) {
	repository.deleteById(id);
	
}

@Override
public Optional<Sede> buscaSede(int id) {

	return repository.findById(id);
}


}
