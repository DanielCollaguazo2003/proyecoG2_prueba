package com.example.IngSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IngSoftware.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {}
