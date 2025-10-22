package com.example.demo;

import com.example.demo.model.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionObrasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionObrasApplication.class, args);
	}

    // Este runner se ejecuta al iniciar la app
    @Bean
    public CommandLineRunner crearAdminPorDefecto(UsuarioRepository usuarioRepository,
                                                  PasswordEncoder passwordEncoder) {
        return args -> {
            String username = "admin";
            if (usuarioRepository.findByUsername(username).isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername(username);
                admin.setRole("ROLE_ADMIN");
                admin.setPassword(passwordEncoder.encode("admin123")); // contraseña por defecto
                usuarioRepository.save(admin);
                System.out.println("Usuario ADMIN creado: admin / admin123");
            }
        };
    }

}
