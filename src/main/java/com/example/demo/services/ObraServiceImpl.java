package com.example.demo.services;

import com.example.demo.model.Obra;
import com.example.demo.repositories.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraServiceImpl implements IObraService {

    @Autowired
    private ObraRepository obraRepository;

    @Override
    public List<Obra> listarTodas() {
        return obraRepository.findAll();
    }

    @Override
    public Optional<Obra> buscarPorId(Long id) {
        return obraRepository.findById(id);
    }

    @Override
    public Obra guardar(Obra obra) {
        return obraRepository.save(obra);
    }

    @Override
    public void eliminar(Long id) {
        obraRepository.deleteById(id);
    }
}
