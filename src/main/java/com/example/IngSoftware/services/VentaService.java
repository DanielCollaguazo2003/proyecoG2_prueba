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

    /**
     * Lista todas las ventas registradas en el sistema.
     * 
     * @return Lista de ventas.
     */
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    /**
     * Busca una venta por su ID.
     * 
     * @param id ID de la venta a buscar.
     * @return Objeto Venta encontrado.
     */
    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    /**
     * Crea y guarda una nueva venta en la base de datos.
     * 
     * @param venta Objeto Venta a guardar.
     * @return Venta guardada.
     */
    public Venta crearVenta(Venta venta) {
        // Validación adicional: asegurarse de que el cliente y los datos sean válidos.
        if (venta.getCliente() == null) {
            throw new RuntimeException("La venta debe estar asociada a un cliente.");
        }
        return ventaRepository.save(venta);
    }

    /**
     * Elimina una venta por su ID.
     * 
     * @param id ID de la venta a eliminar.
     */
    public void eliminarVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
    }

    /**
     * Calcula el total de una venta sumando los montos de las facturas asociadas.
     * 
     * @param id ID de la venta.
     * @return Total de la venta.
     */
    public double calcularTotal(Long id) {
        Venta venta = buscarPorId(id);
        // Calcula el total sumando los montos de todas las facturas asociadas.
        return venta.getFacturas().stream()
                .mapToDouble(factura -> factura.getMonto())
                .sum();
    }
}
