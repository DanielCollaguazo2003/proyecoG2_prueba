package com.example.IngSoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.IngSoftware.model.Factura;
import com.example.IngSoftware.services.FacturaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/facturas") // Ruta base para los endpoints de factura
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Endpoint para listar todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> listarFacturas() {
        List<Factura> facturas = facturaService.listarFacturas();
        return ResponseEntity.ok(facturas);
    }

    // Endpoint para obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscarPorId(@PathVariable Long id) {
        Factura factura = facturaService.buscarPorId(id);
        return ResponseEntity.ok(factura);
    }

    // Endpoint para crear una nueva factura
    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        Factura nuevaFactura = facturaService.crearFactura(factura);
        return ResponseEntity.ok(nuevaFactura);
    }

    // Endpoint para eliminar una factura por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }

    // Endpoint para calcular el impuesto de una factura
    @GetMapping("/{id}/impuesto")
    public ResponseEntity<Double> calcularImpuesto(@PathVariable Long id) {
        double impuesto = facturaService.calcularImpuesto(id);
        return ResponseEntity.ok(impuesto);
    }

    //Endpoint para listar las facturas por fechas
    @GetMapping("/filtrar-por-fechas")
    public ResponseEntity<List<Factura>> filtrarPorFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Factura> facturas = facturaService.filtrarPorFechas(inicio, fin);
        return ResponseEntity.ok(facturas);
    }

}
