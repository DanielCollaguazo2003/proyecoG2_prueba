package com.example.IngSoftware.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.IngSoftware.model.Venta;
import com.example.IngSoftware.services.VentaService;
@RestController
@RequestMapping("/api/ventas") // Ruta base para los endpoints de ventas
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Endpoint para listar todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        return ResponseEntity.ok(ventas);
    }

    // Endpoint para buscar una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {
        Venta venta = ventaService.buscarPorId(id);
        return ResponseEntity.ok(venta);
    }

    // Endpoint para crear una nueva venta
    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.crearVenta(venta);
        return ResponseEntity.ok(nuevaVenta);
    }

    // Endpoint para eliminar una venta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }

    // Endpoint para obtener el total de la venta
    @GetMapping("/{id}/total")
    public ResponseEntity<Double> obtenerTotalVenta(@PathVariable Long id) {
        double total = ventaService.calcularTotal(id);
        return ResponseEntity.ok(total);
    }
}
