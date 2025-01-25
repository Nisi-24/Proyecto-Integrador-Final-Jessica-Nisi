package ar.edu.centro8.proyecto_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.proyecto_final.entities.Libro;
import ar.edu.centro8.proyecto_final.entities.Prestamo;
import ar.edu.centro8.proyecto_final.repositories.LibroRepository;
import ar.edu.centro8.proyecto_final.repositories.PrestamoRepository;
import jakarta.persistence.EntityNotFoundException;
import ar.edu.centro8.proyecto_final.service.LibroService;


@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:8080")
public class LibroController {
     @Autowired
    private LibroRepository libroRepository;
     @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private  LibroService libroService;

    @GetMapping("/traer")
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroRepository.findAll();
        return ResponseEntity.ok(libros);
    }
    @PostMapping 
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        return new ResponseEntity<>(libroRepository.save(libro), HttpStatus.CREATED);
    }

   @PutMapping("/editar/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro updatedLibro) {
        Libro updated = libroRepository.save(updatedLibro);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id) {
        Libro existingLibro = libroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));
        libroRepository.delete(existingLibro);
        
        return ResponseEntity.ok("El libro ha sido actualizado correctamente");
    }

    @PostMapping("/{id}/prestamos")
public ResponseEntity<?> createPrestamoForLibro(
    @PathVariable Long id,
    @RequestBody Prestamo prestamo
) {
    Libro libro = libroService.traerLibro(id);
    if (libro == null) {
        return ResponseEntity.notFound().build();
    }
    
    prestamo.setLibro(libro);
    libro.agregarPrestamo(prestamo); 
    libroService.guardarLibro(libro);
    prestamoRepository.save(prestamo);
    
    return ResponseEntity.ok("Prestamo creado exitosamente");
}

@GetMapping("/{id}/prestamos")
public ResponseEntity<Libro> getLibroConPrestamos(@PathVariable Long id) {
    Libro libro = libroService.traerLibro(id);
    if (libro != null) {
        return ResponseEntity.ok(libro);
    }
    return ResponseEntity.notFound().build();
}
}
