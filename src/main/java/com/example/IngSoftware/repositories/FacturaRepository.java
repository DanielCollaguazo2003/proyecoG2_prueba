package com.example.IngSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IngSoftware.model.Factura;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Consulta a la BD
    @Query("SELECT f FROM Factura f WHERE f.fechaEmision BETWEEN :inicio AND :fin")
    List<Factura> findByFechaEmisionBetween(LocalDate inicio, LocalDate fin);
}
