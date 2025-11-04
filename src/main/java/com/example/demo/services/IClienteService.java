package com.example.demo.services;

import com.example.demo.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> listarClientes();
    Optional<Cliente> buscarClientePorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Optional<Cliente> actualizarCliente(Long id, Cliente clienteDetalles);
    void eliminarCliente(Long id);
}
