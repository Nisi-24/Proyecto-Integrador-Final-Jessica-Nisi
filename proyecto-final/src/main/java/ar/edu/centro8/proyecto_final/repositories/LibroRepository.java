package ar.edu.centro8.proyecto_final.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.proyecto_final.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByNombreContainingIgnoreCase(String nombre);
}
