package com.example.demo.controllers;

import com.example.demo.model.Obra;
import com.example.demo.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/obras")
public class ObraController {

    private final ObraService obraService;

    @Autowired
    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    // ✅ Listar todas las obras
    @GetMapping
    public String listarObras(Model model) {
        model.addAttribute("titulo", "Listado de Obras");
        model.addAttribute("obras", obraService.listarTodas());
        return "obras"; // corresponde a templates/obras.html
    }

    // ✅ Mostrar formulario para crear una nueva obra
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaObra(Model model) {
        model.addAttribute("obra", new Obra());
        model.addAttribute("titulo", "Registrar Nueva Obra");
        return "form-obra";
    }

    // ✅ Guardar o actualizar una obra
    @PostMapping("/guardar")
    public String guardarObra(@ModelAttribute("obra") Obra obra) {
        obraService.guardar(obra);
        return "redirect:/obras";
    }

    // ✅ Editar una obra existente
    @GetMapping("/editar/{id}")
    public String editarObra(@PathVariable Long id, Model model) {
        Optional<Obra> obraOpt = obraService.buscarPorId(id);
        if (obraOpt.isPresent()) {
            model.addAttribute("obra", obraOpt.get());
            model.addAttribute("titulo", "Editar Obra");
            return "form-obra";
        } else {
            return "redirect:/obras";
        }
    }

    // ✅ Eliminar una obra
    @GetMapping("/eliminar/{id}")
    public String eliminarObra(@PathVariable Long id) {
        obraService.eliminar(id);
        return "redirect:/obras";
    }
}
