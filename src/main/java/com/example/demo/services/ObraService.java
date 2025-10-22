package com.example.demo.services;

import com.example.demo.model.Obra;
import java.util.List;
import java.util.Optional;

public interface ObraService {
    List<Obra> listarTodas();
    Optional<Obra> buscarPorId(Long id);
    Obra guardar(Obra obra);
    void eliminar(Long id);
}
