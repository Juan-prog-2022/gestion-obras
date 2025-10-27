package com.example.demo.controllers;

import com.example.demo.model.Usuario;
import com.example.demo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // 🔹 Mostrar página de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 🔹 Mostrar formulario de registro
    @GetMapping("/register")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    // 🔹 Procesar registro de usuario
    @PostMapping("/register")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                   BindingResult result,
                                   Model model) {

        // Validación de errores de formulario
        if (result.hasErrors()) {
            return "register";
        }

        // Validar si el usuario ya existe
        if (usuarioService.existsByUsername(usuario.getUsername())) {
            model.addAttribute("error", "El usuario ya existe. Elige otro.");
            return "register";
        }

        // Guardar el usuario con contraseña encriptada y rol por defecto
        usuarioService.registrarUsuario(usuario);

        // Redirigir al login con parámetro para mensaje de éxito
        return "redirect:/login?registroExitoso";
    }
}
