package com.example.IngSoftware.services;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Factura;
import com.example.IngSoftware.repositories.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    private static final double IMPUESTO_PORCENTAJE = 0.15; // 15% de impuesto

    /**
     * Lista todas las facturas disponibles.
     * 
     * @return Lista de facturas.
     */
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    /**
     * Busca una factura por su ID.
     * 
     * @param id ID de la factura.
     * @return Factura encontrada.
     * @throws RuntimeException si la factura no se encuentra.
     */
    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    /**
     * Crea y guarda una nueva factura en la base de datos.
     * 
     * @param factura Objeto Factura a guardar.
     * @return Factura guardada.
     */
    public Factura crearFactura(Factura factura) {
        if (factura.getMonto() == null || factura.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto de la factura debe ser mayor a 0.");
        }
        return facturaRepository.save(factura);
    }

    /**
     * Elimina una factura por su ID.
     * 
     * @param id ID de la factura a eliminar.
     * @throws RuntimeException si la factura no existe.
     */
    public void eliminarFactura(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
    }

    /**
     * Calcula el impuesto sobre el monto de la factura.
     * 
     * @param id ID de la factura.
     * @return Valor del impuesto calculado.
     * @throws RuntimeException si la factura no existe.
     */
    public double calcularImpuesto(Long id) {
        Factura factura = buscarPorId(id);
        return factura.getMonto() * IMPUESTO_PORCENTAJE;
    }

    /**
     * Calcula el monto total de la factura (monto base + impuesto).
     * 
     * @param id ID de la factura.
     * @return Total de la factura.
     * @throws RuntimeException si la factura no existe.
     */
    public double calcularTotalConImpuesto(Long id) {
        Factura factura = buscarPorId(id);
        double impuesto = calcularImpuesto(id);
        return factura.getMonto() + impuesto;
    }

    public List<Factura> filtrarPorFechas(LocalDate inicio, LocalDate fin) {
        return facturaRepository.findByFechaEmisionBetween(inicio, fin);
    }

    // Generar PDF - DEMO (PRUEBA)
    public byte[] generarFacturaPDF(Long id) {
        // Buscar la factura
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));

        // Crear el PDF en memoria
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Título
            document.add(new Paragraph("Factura ID: " + factura.getId()));
            document.add(new Paragraph("Fecha de Emisión: " + factura.getFechaEmision()));
            document.add(new Paragraph("Monto: $" + factura.getMonto()));

            // Tabla (opcional si tienes más datos)
            if (factura.getVenta() != null) {
                document.add(new Paragraph("Información de la Venta"));
                Table table = new Table(2); // 2 columnas
                table.addCell("Campo");
                table.addCell("Valor");

                table.addCell("ID Venta");
                table.addCell(String.valueOf(factura.getVenta().getId()));

                // Si `Venta` tiene más campos, agrégalos aquí
                document.add(table);
            } else {
                document.add(new Paragraph("No hay venta asociada"));
            }

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF: " + e.getMessage(), e);
        }

        return byteArrayOutputStream.toByteArray();
    }

}
