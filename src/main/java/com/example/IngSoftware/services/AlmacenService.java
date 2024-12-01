package com.example.IngSoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IngSoftware.model.Almacen;
import com.example.IngSoftware.repositories.AlmacenRepository;
@Service
public class AlmacenService {
    @Autowired
    private AlmacenRepository almacenRepository;

    
    public List<Almacen> listarAlmacenes() {
        return almacenRepository.findAll(); // Retorna todos los almacenes
    }

    
    public Almacen buscarPorId(Long id) {
        return almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + id));
    }

    
    public Almacen crearAlmacen(Almacen almacen) {
        return almacenRepository.save(almacen); // Guarda un nuevo almacén
    }

    
    public Almacen actualizarAlmacen(Long id, Almacen almacen) {
        Almacen almacenExistente = buscarPorId(id);
        almacenExistente.setUbicacion(almacen.getUbicacion());
        almacenExistente.setTipo(almacen.getTipo());
        return almacenRepository.save(almacenExistente); // Actualiza los datos del almacén
    }

    
    public void eliminarAlmacen(Long id) {
        if (almacenRepository.existsById(id)) {
            almacenRepository.deleteById(id); // Elimina el almacén
        } else {
            throw new RuntimeException("Almacén no encontrado con ID: " + id);
        }
    }
}
