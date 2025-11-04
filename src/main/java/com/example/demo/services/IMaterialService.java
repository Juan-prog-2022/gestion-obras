package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Material;

public interface IMaterialService {
    List<Material> listarMateriales();
    Optional<Material> buscarMaterialPorId();
    Material guardarMaterial(Material material);
    Optional<Material> actualizarMaterial(Long id, Material materialDetalles);
    void eliminarMaterial(Long id);
}
