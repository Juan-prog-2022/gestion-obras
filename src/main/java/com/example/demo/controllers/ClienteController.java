package com.example.demo.controllers;

import com.example.demo.model.Cliente;
import com.example.demo.services.IClienteService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    // ✅ Listar todas los clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientes"; // corresponde a templates/clientes.html
    }

    // ✅ Mostrar formulario para crear un nuevo cliente
    @GetMapping("/nuevoCliente")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("titulo", "Registrar Nuevo Cliente");
        return "form-cliente";
    }

    // ✅ Guardar un cliente (nuevo o editado)
    @PostMapping("/guardarCliente")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", cliente.getId() == null ? "Registrar Nuevo Cliente" : "Editar Cliente");
            return "form-cliente"; // <-- corregido, coincide con tu plantilla
        }

        clienteService.guardarCliente(cliente);
        model.addAttribute("mensaje", "Cliente registrado con éxito");
        return "redirect:/clientes";
    }

    // ✅ Editar un cliente existente
    @GetMapping("/editarCliente/{id}")
    public String editarObra(@PathVariable Long id, Model model) {
        Optional<Cliente> clienteOpt = clienteService.buscarClientePorId(id);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            model.addAttribute("titulo", "Editar Cliente");
            return "form-cliente";
        } else {
            return "redirect:/clientes";
        }
    }

    // ✅ Eliminar un cliente
    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id, Model model) {
        clienteService.eliminarCliente(id);
        model.addAttribute("mensaje", "Cliente eliminado con éxito");
        return "redirect:/clientes";
    }

}
