package com.example.IngSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IngSoftware.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {}
