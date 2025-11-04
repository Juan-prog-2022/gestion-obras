package com.example.demo.services;

import com.example.demo.model.Role;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 🔹 Método para registrar un usuario desde el formulario
    @Transactional
    public void registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Role rolUser = rolRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role nuevoRol = new Role();
                    nuevoRol.setName("ROLE_USER");
                    return rolRepository.save(nuevoRol);
                });

        usuario.setRoles(new HashSet<>());
        usuario.getRoles().add(rolUser);

        usuarioRepository.save(usuario);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    // ✅ Métodos de la interfaz IUserService
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long idUser) {
        return usuarioRepository.findById(idUser).orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

    @Override
    public void guardarUsuario(Usuario user) {
        // Encriptar si la contraseña no está codificada
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        usuarioRepository.save(user);
    }

    @Override
    public void eliminarUsuario(Usuario user) {
        usuarioRepository.delete(user);
    }
}
