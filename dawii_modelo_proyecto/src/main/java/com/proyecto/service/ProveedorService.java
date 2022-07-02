package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Proveedor;

public interface ProveedorService {
	
	//PC1-Registro Proveedor
	//public Proveedor insertaActualizaProveedor(Proveedor obj);
	public List<Proveedor> listaProveedor();
	public abstract List<Proveedor> listRucProveedor(String ruc);

	//PC2-CHAVEZ-CONSULTA PROVEEDOR
	public List<Proveedor> listaProveedorPorRazonSocialRucUbigeo(String razonsocial, String ruc, int idUbigeo, int estado);

	//PC3-CHAVEZ-CRUD PROVEEDOR
	public abstract Proveedor insertaActualizaProveedor(Proveedor obj);
	public abstract List<Proveedor> listaProveedorPorRazonSocialLike(String razonsocial);
    public abstract void eliminaProveedor(int id);
    public abstract Optional<Proveedor> buscaProveedor(int id);

}
