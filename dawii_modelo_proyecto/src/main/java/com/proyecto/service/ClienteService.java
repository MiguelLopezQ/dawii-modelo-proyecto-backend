package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Cliente;

public interface ClienteService{

	public abstract List<Cliente> listaCliente();
	public abstract Cliente insertUpdateCliente(Cliente obj);
	public abstract List<Cliente> listDniCliient(String dni);
	public abstract List<Cliente> listarClienteDniUbigeoEstado(String nombres,String dni,int idUbigeo,int estado);
	public abstract List<Cliente> listaPoNombre(String nombre);
	public abstract void eliminaCliente(int id);
	public abstract Cliente buscaCliente(int id);
	
}
