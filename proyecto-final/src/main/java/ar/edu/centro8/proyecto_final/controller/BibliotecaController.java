package ar.edu.centro8.proyecto_final.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.centro8.proyecto_final.entities.Biblioteca;
import ar.edu.centro8.proyecto_final.entities.Libro;
import ar.edu.centro8.proyecto_final.repositories.BibliotecaRepository;
import ar.edu.centro8.proyecto_final.service.BibliotecaService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;
    @Autowired
    private  BibliotecaService bibliotecaService;

    @GetMapping("/traer")
    public ResponseEntity<List<Biblioteca>> getAllBibliotecas() {
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
        return ResponseEntity.ok(bibliotecas);
    }
    @PostMapping 
    public ResponseEntity<Biblioteca> createBiblioteca(@RequestBody Biblioteca biblioteca) {
        return new ResponseEntity<>(bibliotecaRepository.save(biblioteca), HttpStatus.CREATED);
    }

   @PutMapping("/editar/{id}")
    public ResponseEntity<Biblioteca> updateBiblioteca(@PathVariable Long id, @RequestBody Biblioteca updatedBiblioteca) {
        Biblioteca updated = bibliotecaRepository.save(updatedBiblioteca);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable Long id) {
        Biblioteca existingBiblioteca = bibliotecaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Biblioteca not found"));
        bibliotecaRepository.delete(existingBiblioteca);
        
        return ResponseEntity.ok("La biblioteca ha sido actualizada correctamente");
    }

    @PostMapping("/{id}/libros")
    public ResponseEntity<?> createLibroForBiblioteca(
        @PathVariable Long id,
        @RequestBody Libro libro
    ) {
        Biblioteca biblioteca = bibliotecaService.traerBiblioteca(id);
        if (biblioteca == null) {
            return ResponseEntity.notFound().build();
        }
        
        libro.setBiblioteca (biblioteca);
        bibliotecaService.guardarLibro(libro);
        
        return ResponseEntity.ok("Libro creado exitosamente");
    }
    
}

