package com.example.IngSoftware.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventario")
@Data
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    private int cantidad;

    // Método para listar el producto asociado
    public String listarProducto() {
        return "Producto: " + producto.getNombre() + ", Stock: " + cantidad;
    }

    // Método para agregar un nuevo producto al inventario
    public void agregarProducto(Producto producto, int cantidadInicial) {
        this.producto = producto;
        this.cantidad = cantidadInicial;
    }

    // Método para actualizar la cantidad en el inventario
    public void actualizarCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
    }

    // Método para verificar si el producto está disponible
    public boolean verificarDisponibilidad(int cantidadRequerida) {
        return this.cantidad >= cantidadRequerida;
    }
}
