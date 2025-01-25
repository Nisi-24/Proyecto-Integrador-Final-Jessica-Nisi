package ar.edu.centro8.proyecto_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.centro8.proyecto_final.entities.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}


