package com.example.IngSoftware.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;

    // Método para comprar un producto (representado como String aquí)
    public void comprarProducto(String producto) {
        System.out.println(nombre + " ha comprado el producto: " + producto);
    }

    // Método para ver el historial de compras (esto puede interactuar con una base de datos más adelante)
    public void verHistorialCompras() {
        System.out.println("Historial de compras de " + nombre + " (simulación): Producto 1, Producto 2...");
    }

    // Método para solicitar devolución
    public void solicitarDevolucion(String producto) {
        System.out.println(nombre + " ha solicitado la devolución del producto: " + producto);
    }
}
