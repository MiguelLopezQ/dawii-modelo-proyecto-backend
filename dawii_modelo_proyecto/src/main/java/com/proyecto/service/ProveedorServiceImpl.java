package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Proveedor;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	
    //PC1-REGISTRO PROVEEDOR
	@Autowired
	private ProveedorRepository repositorio;

	
	
	@Override
	public List<Proveedor> listaProveedor() {
		return repositorio.findAll();
	}

	@Override
	public List<Proveedor> listRucProveedor(String ruc) {
		// TODO Auto-generated method stub
		return repositorio.listaPorRuc(ruc);
	}

	//PC2-CONSULTA
	@Override
	public List<Proveedor> listaProveedorPorRazonSocialRucUbigeo(String razonsocial, String ruc, int idUbigeo, int estado) {
		return repositorio.listaProveedorPorRazonSocialRucUbigeo(razonsocial, ruc, idUbigeo, estado);
	}

	//PC3-CRUD PROVEEDOR
	@Override
	public List<Proveedor> listaProveedorPorRazonSocialLike(String razonsocial) {
		return repositorio.listaPorRazonSocialLike(razonsocial);
	}

	@Override
	public Proveedor insertaActualizaProveedor(Proveedor obj) {
		return repositorio.save(obj);
	}

	@Override
	public void eliminaProveedor(int id) {
		repositorio.deleteById(id);	
	}

	@Override
	public Optional<Proveedor> buscaProveedor(int id) {
        return repositorio.findById(id);
	}
	
}
