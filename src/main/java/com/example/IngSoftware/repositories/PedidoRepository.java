package com.example.IngSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IngSoftware.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
