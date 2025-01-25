package ar.edu.centro8.proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.proyecto_final.entities.Biblioteca;
import ar.edu.centro8.proyecto_final.entities.Libro;
import ar.edu.centro8.proyecto_final.repositories.BibliotecaRepository;
import ar.edu.centro8.proyecto_final.repositories.LibroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class BibliotecaService {
     @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private LibroRepository libroRepository;

    
    public List<Biblioteca> obtenerBibliotecas() {
        return bibliotecaRepository.findAll();
    }

    public Biblioteca guardarBiblioteca(Biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    public boolean eliminarBiblioteca(Long id) {
        try {
            bibliotecaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Biblioteca traerBiblioteca(Long id) {
        return bibliotecaRepository.findById(id).orElse(null);
    }

    public Biblioteca editarBiblioteca(Long idOriginal, Long idNueva, String nuevoNombre) {
        Biblioteca biblioteca = traerBiblioteca(idOriginal);
        if (biblioteca != null) {
            biblioteca.setId(idNueva);
            biblioteca.setNombre(nuevoNombre);
            return guardarBiblioteca(biblioteca);
        }
        return null;
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }
}
