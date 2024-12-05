package com.example.IngSoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.IngSoftware.model.Almacen;
import com.example.IngSoftware.model.Cliente;
import com.example.IngSoftware.model.Inventario;
import com.example.IngSoftware.model.Pedido;
import com.example.IngSoftware.model.Producto;
import com.example.IngSoftware.model.Venta;
import com.example.IngSoftware.repositories.AlmacenRepository;
import com.example.IngSoftware.repositories.ClienteRepository;
import com.example.IngSoftware.repositories.InventarioRepository;
import com.example.IngSoftware.repositories.PedidoRepository;
import com.example.IngSoftware.repositories.ProductoRepository;
import com.example.IngSoftware.repositories.VentaRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final PedidoRepository pedidoRepository;
    private final InventarioRepository inventarioRepository;
    private final AlmacenRepository almacenRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataInitializer(
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository,
            VentaRepository ventaRepository,
            PedidoRepository pedidoRepository,
            InventarioRepository inventarioRepository,
            AlmacenRepository almacenRepository) {
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.pedidoRepository = pedidoRepository;
        this.inventarioRepository = inventarioRepository;
        this.almacenRepository = almacenRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        inicializarClientes();
        inicializarProductos();
        inicializarInventarios();
        inicializarPedidos();
        inicializarVentas();
        inicializarAlmacenes();
    }

    private void inicializarClientes() {
        clienteRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE cliente_id_seq RESTART WITH 1");

        if (clienteRepository.count() == 0) {
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan Hidalgo Pérez");
            cliente1.setDireccion("Calle Ficticia 98765432");

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("María Eduarda Lopez");
            cliente2.setDireccion("Avenida Ejemplo 456123456");

            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);

            System.out.println("Clientes inicializados.");
        }
    }

    private void inicializarProductos() {
        productoRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE producto_id_seq RESTART WITH 1");

        if (productoRepository.count() == 0) {
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

            productoRepository.save(producto1);
            productoRepository.save(producto2);
            productoRepository.save(producto3);

            System.out.println("Productos inicializados.");
        }
    }

    private void inicializarInventarios() {
        inventarioRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE inventario_id_seq RESTART WITH 1");

        if (inventarioRepository.count() == 0) {
            Producto producto = productoRepository.findAll().get(0);
            Inventario inventario = new Inventario();
            inventario.setProducto(producto);
            inventario.setCantidad(500);

            inventarioRepository.save(inventario);

            System.out.println("Inventarios inicializados.");
        }
    }

    private void inicializarPedidos() {
        pedidoRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE pedido_id_seq RESTART WITH 1");

        if (pedidoRepository.count() == 0) {
            Producto producto = productoRepository.findAll().get(0);
            Pedido pedido = new Pedido();
            pedido.setFecha("2024-12-04");
            pedido.setEstado("En Proceso");
            pedido.getProductos().add(producto);

            pedidoRepository.save(pedido);

            System.out.println("Pedidos inicializados.");
        }
    }

    private void inicializarVentas() {
        ventaRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE venta_id_seq RESTART WITH 1");

        if (ventaRepository.count() == 0) {
            Cliente cliente = clienteRepository.findAll().get(0);
            Venta venta = new Venta();
            venta.setFecha("2024-12-04");
            venta.setTotal(199.99);
            venta.setCliente(cliente);

            ventaRepository.save(venta);

            System.out.println("Ventas inicializadas.");
        }
    }

    private void inicializarAlmacenes() {
        almacenRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE almacen_id_seq RESTART WITH 1");

        if (almacenRepository.count() == 0) {
            Almacen almacen = new Almacen();
            almacen.setUbicacion("Sector Norte");
            almacen.setTipo("Principal");

            almacenRepository.save(almacen);

            System.out.println("Almacenes inicializados.");
        }
    }
}