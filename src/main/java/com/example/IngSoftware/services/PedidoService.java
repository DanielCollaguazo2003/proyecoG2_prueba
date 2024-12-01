package com.example.IngSoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Pedido;
import com.example.IngSoftware.repositories.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll(); // Retorna todos los pedidos
    }

    
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    
    public Pedido crearPedido(Pedido pedido) {
        // Lógica adicional, como validar el pedido o asignar estado inicial, si es necesario
        pedido.setEstado("Pendiente"); // Estado inicial por defecto
        return pedidoRepository.save(pedido); // Guarda el pedido en la base de datos
    }

    
    public Pedido actualizarEstado(Long id, String estado) {
        Pedido pedidoExistente = buscarPorId(id);
        pedidoExistente.setEstado(estado); // Actualiza el estado del pedido
        return pedidoRepository.save(pedidoExistente); // Guarda los cambios
    }

    
    public void eliminarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id); // Elimina el pedido
        } else {
            throw new RuntimeException("Pedido no encontrado con ID: " + id);
        }
    }
}
