package com.example.IngSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IngSoftware.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
