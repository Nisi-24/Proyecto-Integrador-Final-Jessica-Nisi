package ar.edu.centro8.proyecto_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.proyecto_final.entities.Prestamo;
import ar.edu.centro8.proyecto_final.repositories.PrestamoRepository;
import ar.edu.centro8.proyecto_final.service.PrestamoService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
     @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private PrestamoService prestamoService;


    @GetMapping("/traer")
    public ResponseEntity<List<Prestamo>> getAllPrestamo() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        if (prestamos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(prestamos); // o un mensaje personalizado
        }
        return ResponseEntity.ok(prestamos);
    }
    @PostMapping 
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        return new ResponseEntity<>(prestamoRepository.save(prestamo), HttpStatus.CREATED);
    }

    @PostMapping("/calcular")
    public ResponseEntity<Prestamo> calcularPrecioTotal(@RequestBody Prestamo prestamo){
        try {
            Prestamo resultado = prestamoService.calcularPrecioTotal(prestamo);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    

   @PutMapping("/editar/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo updatedPrestamo) {
        Prestamo updated = prestamoRepository.save(updatedPrestamo);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable Long id) {
        Prestamo existingPrestamo = prestamoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Prestamo no encontrado"));
        prestamoRepository.delete(existingPrestamo);
        
        return ResponseEntity.ok("El prestamo ha sido actualizado correctamente");
    }
}
