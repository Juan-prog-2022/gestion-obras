package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Proveedor;
import com.example.demo.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> buscarProveedorPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Optional<Proveedor> actualizarProveedor(Long id, Proveedor proveedorDetalles) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNombre(proveedorDetalles.getNombre());
            proveedor.setDireccion(proveedorDetalles.getDireccion());
            proveedor.setTelefono(proveedorDetalles.getTelefono());
            return proveedorRepository.save(proveedor);
        });
    }

    @Override
    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }

    

}
