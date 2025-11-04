package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Empleado;
import com.example.demo.services.IEmpleadoService;
import com.example.demo.services.IObraService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IObraService obraService;

    // Listar todos los empleados
    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("titulo", "Listado de Empleados");
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        return "empleados"; // corresponde a templates/empleados.html
    }

    // Mostrar formulario para crear un nuevo empleado
    @GetMapping("/nuevoEmpleado")
    public String mostrarFormularioNuevoEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("titulo", "Registrar Nuevo Empleado");
        model.addAttribute("obras", obraService.listarTodas());
        return "form-empleado";
    }

    // Guardar o actualizar empleado
    @PostMapping("/guardarEmpleado")
    public String guardarEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", empleado.getId() == null ? "Registrar Nuevo Empleado" : "Editar Empleado");
            model.addAttribute("obras", obraService.listarTodas());
            return "form-empleado";
        }

        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados";
    }

    // Editar empleado existente
    @GetMapping("/editarEmpleado/{id}")
    public String editarEmpleado(@PathVariable Long id, Model model) {
        Optional<Empleado> optEmpleado = empleadoService.buscarEmpleadoPorId(id);

        // ✅ Evitar NullPointer y usar .isPresent()
        if (optEmpleado.isPresent()) {
            model.addAttribute("empleado", optEmpleado.get());
            model.addAttribute("titulo", "Editar Empleado");
            model.addAttribute("obras", obraService.listarTodas());
            return "form-empleado";
        } else {
            return "redirect:/empleados?error=notfound";
        }
    }

    // Eliminar un empleado
    @GetMapping("/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados";
    }

}
