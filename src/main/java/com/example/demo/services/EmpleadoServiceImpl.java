package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados (){
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarEmpleadoPorId(Long id){
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<Empleado> actualizarEmpleado(Long id, Empleado empleadoDetalles) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre(empleadoDetalles.getNombre());
            empleado.setApellido(empleadoDetalles.getApellido());
            empleado.setPuesto(empleadoDetalles.getPuesto());
            empleado.setSalario(empleadoDetalles.getSalario());
            return empleadoRepository.save(empleado);
        });
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
