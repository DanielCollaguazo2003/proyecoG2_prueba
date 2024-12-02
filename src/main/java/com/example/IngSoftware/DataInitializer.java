package com.example.IngSoftware;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.IngSoftware.model.Cliente;
import com.example.IngSoftware.model.Producto;
import com.example.IngSoftware.repositories.ClienteRepository;
import com.example.IngSoftware.repositories.ProductoRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    // Inyectamos el repositorio para interactuar con la base de datos
    public DataInitializer(ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existen clientes en la base de datos
        if (clienteRepository.count() == 0) {
            // Crear clientes si no existen
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan Hidalgo Pérez");
            cliente1.setDireccion("Calle Ficticia 98765432");

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("María Eduarda Lopez");
            cliente2.setDireccion("Avenida Ejemplo 456123456");

            // Guardar clientes en la base de datos
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);

            System.out.println("Clientes inicializados.");
        } else {
            System.out.println("Clientes ya existen en la base de datos.");
        }

        //
        // Borrar todos los productos antes de agregar los nuevos
        productoRepository.deleteAll();

        if (productoRepository.count() == 0) {
            // Crear productos iniciales
            Producto producto1 = new Producto();
            producto1.setNombre("Producto A");
            producto1.setPrecio(99.99);
            producto1.setStock(100);
        
            Producto producto2 = new Producto();
            producto2.setNombre("Producto B");
            producto2.setPrecio(149.99);
            producto2.setStock(200);
        
            Producto producto3 = new Producto();
            producto3.setNombre("Producto C");
            producto3.setPrecio(49.99);
            producto3.setStock(50);
        
            // Guardar los productos en la base de datos
            productoRepository.save(producto1);
            productoRepository.save(producto2);
            productoRepository.save(producto3);
        
            System.out.println("Productos inicializados.");
        } else {
            System.out.println("Productos ya existen en la base de datos.");
        }
    }
}