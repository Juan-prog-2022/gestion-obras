package com.example.demo.controllers;

import com.example.demo.model.Proveedor;
import com.example.demo.services.IProveedorService;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    // Listar los proveedores
    @GetMapping
    public String listarProveedores(Model model){
        model.addAttribute("titulo", "Listado de Proveedores");
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        return "proveedores";
    }

    // Mostrar formulario para crear un nuevo proveedor
    @GetMapping("/nuevoProveedor")
    public String mostrarFormularioNuevoProveedor(Model model){
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("titulo", "Registrar Nuevo Proveedor");
        return "form-proveedores";
    }

    // Guardar un proveedor
    @PostMapping("/guardarProveedor")
    public String guardarProveedor(@Valid @ModelAttribute("proveedor") Proveedor proveedor,
                                  BindingResult result,
                                  Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", proveedor.getId() == null ? "Registrar Nuevo Proveedor" : "Editar Proveedor");
            return "form-proveedores";
        }
        proveedorService.guardarProveedor(proveedor);
        model.addAttribute("mensaje", "Proveedor registrado con éxito");
        return "redirect:/proveedores";
    }

    // Editar un proveedor existente
    @GetMapping("/editarProveedor/{id}")
    public String editarProveedor(@PathVariable Long id, Model model){
        Optional<Proveedor> proveedorOpt = proveedorService.buscarProveedorPorId(id);
        if(proveedorOpt.isPresent()){
            model.addAttribute("proveedor", proveedorOpt.get());
            model.addAttribute("titulo", "Editar Proveedor");
            return "form-proveedores";
        } else {
            return "redirect:/proveedores";
        }
    }

    // Eliminar un proveedor
    @GetMapping("/eliminarProveedor/{id}")
    public String eliminarProveedor(@PathVariable Long id){
        proveedorService.eliminarProveedor(id);
        return "redirect:/proveedores";
    }

}
