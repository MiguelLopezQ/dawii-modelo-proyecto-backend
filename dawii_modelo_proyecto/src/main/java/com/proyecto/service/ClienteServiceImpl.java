package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente() {
		return repository.findAll();
	}

	@Override
	public Cliente insertUpdateCliente(Cliente obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Cliente> listDniCliient(String dni) {
		// TODO Auto-generated method stub
		return repository.listaPorDni(dni);
	}

	@Override
	public List<Cliente> listarClienteDniUbigeoEstado(String nombres, String dni, int idUbigeo, int estado) {
		
		return repository.listarClienteDniUbigeoEstado(nombres, dni, idUbigeo, estado);
	}

	@Override
	public List<Cliente> listaPoNombre(String nombre) {
		
		return repository.listaPoNombre(nombre);
	}

	@Override
	public void eliminaCliente(int id) {
		repository.deleteById(id);
		
		
	}

	@Override
	public Cliente buscaCliente(int id) {
		
		return repository.buscarCliente(id);
	}

}
