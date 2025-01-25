package ar.edu.centro8.proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.proyecto_final.entities.Libro;
import ar.edu.centro8.proyecto_final.repositories.LibroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    
    public List<Libro> obtenerLibros() {
        return  libroRepository.findAll();
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public boolean eliminarLibro(Long id) {
        try {
            libroRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Libro traerLibro(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro editarLibro(Long idOriginal, Long idNueva, String nuevaNombre, String nuevoAutor, String nuevaEditorial, String nuevoTipo) {
        Libro libro = traerLibro(idOriginal);
        if (libro != null) {
            libro.setId(idNueva);
            libro.setNombre(nuevaNombre);
            libro.setAutor(nuevoAutor);
            libro.setEditorial(nuevaEditorial);
            libro.setTipo(nuevoTipo);
            return guardarLibro(libro);
        }
        return null;
    }

    public List<Libro> getAllLibros() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllLibros'");
    }
}
