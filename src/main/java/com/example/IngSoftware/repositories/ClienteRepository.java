package com.example.IngSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IngSoftware.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
