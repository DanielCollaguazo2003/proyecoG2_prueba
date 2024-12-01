package com.example.IngSoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Venta;
import com.example.IngSoftware.repositories.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll(); // Lista todas las ventas
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta); // Guarda una nueva venta
    }

    public void eliminarVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id); // Elimina la venta
        } else {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
    }

    public double calcularTotal(Long id) {
        Venta venta = buscarPorId(id);
        // Asumiendo que la entidad Venta tiene una lista de productos relacionados
        return venta.getFacturas().stream()
                .mapToDouble(factura -> factura.getMonto())
                .sum(); // Calcula el total sumando el monto de las facturas
    }
}
