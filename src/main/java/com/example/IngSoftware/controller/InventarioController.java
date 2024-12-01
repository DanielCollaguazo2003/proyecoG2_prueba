package com.example.IngSoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.IngSoftware.model.Inventario;
import com.example.IngSoftware.services.InventarioService;

import java.util.List;

@RestController
@RequestMapping("/api/inventario") // Ruta base para los endpoints de inventario
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Endpoint para listar todos los productos en el inventario
    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        List<Inventario> inventarios = inventarioService.listarInventario();
        return ResponseEntity.ok(inventarios);
    }

    // Endpoint para buscar un producto en el inventario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        Inventario inventario = inventarioService.buscarPorId(id);
        return ResponseEntity.ok(inventario);
    }

    // Endpoint para agregar un producto al inventario
    @PostMapping
    public ResponseEntity<Inventario> agregarProducto(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.agregarProducto(inventario);
        return ResponseEntity.ok(nuevoInventario);
    }

    // Endpoint para actualizar las existencias de un producto
    @PutMapping("/{id}/cantidad")
    public ResponseEntity<Inventario> actualizarExistencias(@PathVariable Long id, @RequestParam int cantidad) {
        Inventario inventarioActualizado = inventarioService.actualizarExistencias(id, cantidad);
        return ResponseEntity.ok(inventarioActualizado);
    }

    // Endpoint para eliminar un producto del inventario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        inventarioService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}