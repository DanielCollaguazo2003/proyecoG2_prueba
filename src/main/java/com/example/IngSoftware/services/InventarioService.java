package com.example.IngSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Inventario;
import com.example.IngSoftware.repositories.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;


    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll(); // Retorna todos los registros de inventario
    }

 
    public Inventario agregarProducto(Inventario inventario) {
        return inventarioRepository.save(inventario); // Guarda el producto en el inventario
    }


    public Inventario actualizarExistencias(Long id, int cantidad) {
        Optional<Inventario> inventarioOptional = inventarioRepository.findById(id);
        if (inventarioOptional.isPresent()) {
            Inventario inventario = inventarioOptional.get();
            inventario.setCantidad(cantidad); // Actualiza la cantidad del producto
            return inventarioRepository.save(inventario); // Guarda los cambios
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }


    public void eliminarProducto(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id); // Elimina el producto del inventario
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }


    public Inventario buscarPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }
    
}
