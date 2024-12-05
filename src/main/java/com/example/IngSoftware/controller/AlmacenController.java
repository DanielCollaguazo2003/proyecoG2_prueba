package com.example.IngSoftware.controller;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.IngSoftware.model.Almacen;
import com.example.IngSoftware.services.AlmacenService;

@RestController
@RequestMapping("/api/almacenes") // Ruta base para los endpoints
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    // Endpoint para listar todos los almacenes
    @GetMapping
    public ResponseEntity<List<Almacen>> listarAlmacenes() {
        List<Almacen> almacenes = almacenService.listarAlmacenes();
        return ResponseEntity.ok(almacenes);
    }

    // Endpoint para buscar un almacén por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAlmacenPorId(@PathVariable("id") Long id) {
        Almacen almacen = almacenService.buscarPorId(id);
        if (almacen != null) {
            return ResponseEntity.ok(almacen);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El almacén con ID " + id + " no fue encontrado");
        }
    }


    // Endpoint para crear un nuevo almacén
    @PostMapping
    public ResponseEntity<Almacen> crearAlmacen(@RequestBody Almacen almacen) {
        Almacen nuevoAlmacen = almacenService.crearAlmacen(almacen);
        return ResponseEntity.ok(nuevoAlmacen);
    }

    // Endpoint para actualizar un almacén existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlmacen(@PathVariable Long id, @RequestBody Almacen almacen) {
        try {
            Almacen almacenActualizado = almacenService.actualizarAlmacen(id, almacen);
            return ResponseEntity.ok(almacenActualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El almacén con ID " + id + " no fue encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al intentar actualizar el almacén: " + e.getMessage());
        }
    }


    // Endpoint para eliminar un almacén por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlmacen(@PathVariable Long id) {
        almacenService.eliminarAlmacen(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }
}
