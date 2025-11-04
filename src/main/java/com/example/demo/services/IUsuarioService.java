package com.example.demo.services;

import com.example.demo.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();

    Usuario buscarPorId(Long idUser);

    boolean existsByUsername(String username);

    void guardarUsuario(Usuario user);

    void eliminarUsuario(Usuario user);
}