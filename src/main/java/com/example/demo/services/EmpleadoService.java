package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> listarEmpleados (){
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> obtenerEmpleadoPorId(Long id){
        return empleadoRepository.findById(id);
    }

    public Empleado crearEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }
}
