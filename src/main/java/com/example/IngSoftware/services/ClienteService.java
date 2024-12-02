package com.example.IngSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Cliente;
import com.example.IngSoftware.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null); // Retorna el cliente o null si no existe
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id); // Asegura que el cliente tiene el ID correcto
            return clienteRepository.save(cliente);
        }
        return null; // Si no existe, se puede manejar de otra forma
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}