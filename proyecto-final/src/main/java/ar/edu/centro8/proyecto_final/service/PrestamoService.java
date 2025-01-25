package ar.edu.centro8.proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.proyecto_final.entities.Libro;
import ar.edu.centro8.proyecto_final.entities.Prestamo;
import ar.edu.centro8.proyecto_final.repositories.LibroRepository;
import ar.edu.centro8.proyecto_final.repositories.PrestamoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrestamoService {
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroRepository libroRepository;

    
    public List<Prestamo> obtenerPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        Libro libro = libroRepository.findById(prestamo.getId_libro()).orElse(null);
        if (libro != null) {
            libro.agregarPrestamo(prestamo);
            prestamo.setLibro(libro); 
            return prestamoRepository.save(prestamo);
        }
        return null;
    }

    public boolean eliminarPrestamo(Long id) {
        try {
            prestamoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Prestamo traerPrestamo(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public Prestamo editarPrestamo(Long idOriginal, Long idNueva, String nuevoEstado, double nuevoPrecio, int nuevoDias, double nuevoPrecioTotal) {
        Prestamo prestamo = traerPrestamo(idOriginal);
        if (prestamo != null) {
            prestamo.setId(idNueva);
            prestamo.setEstado(nuevoEstado);
            prestamo.setPrecio(nuevoPrecio);
            prestamo.setDias(nuevoDias);
            prestamo.setPrecioTotal(nuevoPrecioTotal);
            return guardarPrestamo(prestamo);
        }
        return null;
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Prestamo calcularPrecioTotal(Prestamo prestamo){
        double precioTotal=prestamo.getPrecio()*prestamo.getDias();
        prestamo.setPrecioTotal(precioTotal);
        return prestamoRepository.save(prestamo);
    }

    public boolean libroEstaAlquilado(Long libroId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'libroEstaAlquilado'");
    }
}
