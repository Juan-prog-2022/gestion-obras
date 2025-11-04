package com.example.demo.services;

import com.example.demo.model.Empleado;
import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {
    List<Empleado> listarEmpleados();
    Optional<Empleado> buscarEmpleadoPorId(Long id);
    Empleado guardarEmpleado(Empleado empleado);
    Optional<Empleado> actualizarEmpleado(Long id, Empleado empleadoDetalles);
    void eliminarEmpleado(Long id);
}
