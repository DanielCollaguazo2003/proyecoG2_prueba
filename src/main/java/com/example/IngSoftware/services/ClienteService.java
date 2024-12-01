package com.example.IngSoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Cliente;
import com.example.IngSoftware.repositories.ClienteRepository;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll(); // Retorna todos los clientes
    }

    
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente); // Guarda un nuevo cliente
    }

    
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = buscarPorId(id);
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setDireccion(cliente.getDireccion());
        return clienteRepository.save(clienteExistente); // Actualiza los datos del cliente
    }

    
    public void eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id); // Elimina el cliente
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }
}
