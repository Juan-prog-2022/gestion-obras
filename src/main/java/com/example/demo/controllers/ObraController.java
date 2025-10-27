package com.example.demo.controllers;

import com.example.demo.model.Obra;
import com.example.demo.services.ObraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/obras")
public class ObraController {

        @Autowired
        private ObraService obraService;

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

        @PostMapping("/guardar")
        public String guardarObra(@Valid @ModelAttribute("obra") Obra obra,
                                  BindingResult result,
                                  Model model) {
            if (result.hasErrors()) {
                model.addAttribute("titulo", obra.getId() == null ? "Registrar Nueva Obra" : "Editar Obra");
                return "form-obra"; // <-- corregido, coincide con tu plantilla
            }

            obraService.guardar(obra);
            model.addAttribute("mensaje", "Obra registrada con éxito");
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
