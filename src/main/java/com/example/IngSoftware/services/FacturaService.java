package com.example.IngSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Factura;
import com.example.IngSoftware.repositories.FacturaRepository;
@Service
public class FacturaService {
     @Autowired
    private FacturaRepository facturaRepository;

    private static final double IMPUESTO_PORCENTAJE = 0.15; 

    public List<Factura> listarFacturas() {
        return facturaRepository.findAll(); 
    }


    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura); 
    }


    public void eliminarFactura(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id); 
        } else {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
    }


    public double calcularImpuesto(Long id) {
        Optional<Factura> facturaOptional = facturaRepository.findById(id);
        if (facturaOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            return factura.getMonto() * IMPUESTO_PORCENTAJE; 
        } else {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
    }
}
