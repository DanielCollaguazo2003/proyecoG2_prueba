package com.example.IngSoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Producto;
import com.example.IngSoftware.repositories.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll(); // Lista todos los productos
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto); // Guarda un nuevo producto
    }

    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = buscarPorId(id);
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setStock(producto.getStock());
        return productoRepository.save(productoExistente); // Actualiza y guarda el producto
    }

    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id); // Elimina el producto
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }
}
