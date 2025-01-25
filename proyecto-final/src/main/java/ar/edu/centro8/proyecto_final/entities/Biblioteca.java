package ar.edu.centro8.proyecto_final.entities;

import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "biblioteca")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //lo probe pero no funcionaba la lista de libros del front
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;

    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonBackReference
    @JsonIgnore
    private List<Libro> libros;

    public Biblioteca() {}

    public Biblioteca(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Libro agregarLibro(Libro libro) {
        this.libros.add(libro);
        libro.setBiblioteca(this); 
        return libro;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((libros == null) ? 0 : libros.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Biblioteca other = (Biblioteca) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (libros == null) {
            if (other.libros != null)
                return false;
        } else if (!libros.equals(other.libros))
            return false;
        return true;
    }

}
