package com.example.demo.services;

import com.example.demo.model.Proveedor;
import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    List<Proveedor> listarProveedores();
    Optional<Proveedor> buscarProveedorPorId(Long id);
    Proveedor guardarProveedor(Proveedor proveedor);
    Optional<Proveedor> actualizarProveedor(Long id, Proveedor proveedorDetalles);
    void eliminarProveedor(Long id);
}
