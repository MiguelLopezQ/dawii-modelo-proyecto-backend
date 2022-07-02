package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Marca;
import com.proyecto.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository Repository;

	@Override
	public List<Marca> listaMarca() {
		return Repository.findAll();
	}

	@Override
	public Marca insertaActualizaMarca(Marca obj) {
		return Repository.save(obj);
	}

	@Override
	public List<Marca> listaMarcaPorNombre(String nombre) {
		return Repository.listaPorNombre(nombre);
	}
	
	@Override
	public List<Marca> listaMarcaNombreDescripcionCertificadoEstadoPais(String nombre, String descripcion,
			String certificado, int idPais, int estado) {
		return Repository.listaMarcaNombreDescripcionCertificadoEstadoPais(nombre, descripcion, certificado, idPais, estado);
	}

	@Override
	public void eliminaMarca(int id) {
		Repository.deleteById(id);		
	}

	@Override
	public List<Marca> listaMarcaPorNombreLike(String nombre) {
		return Repository.listaPorNombreLike(nombre);
	}

	@Override
	public Marca buscaMarcaPorId(int id) {
		return Repository.buscaMarcaPorId(id);
	}


	

}
