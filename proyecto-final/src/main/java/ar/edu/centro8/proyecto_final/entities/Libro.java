package ar.edu.centro8.proyecto_final.entities;

import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String autor;
    private String editorial;
    private String tipo;

    @OneToOne(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true) // bidireccional fk-pk
    @JsonManagedReference
    // @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JsonManagedReference
    // @JsonIgnore
    @JoinColumn(name = "id_biblioteca", referencedColumnName = "id")
    private Biblioteca biblioteca;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    // @JsonIgnore
    private List<Prestamo> prestamos;

    public Prestamo agregarPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
        return prestamo;
    }

    public Libro() {
    }

    public Libro(Long id, String nombre, String autor, String editorial, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.tipo = tipo;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((autor == null) ? 0 : autor.hashCode());
        result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((biblioteca == null) ? 0 : biblioteca.hashCode());
        result = prime * result + ((prestamos == null) ? 0 : prestamos.hashCode());
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
        Libro other = (Libro) obj;
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
        if (autor == null) {
            if (other.autor != null)
                return false;
        } else if (!autor.equals(other.autor))
            return false;
        if (editorial == null) {
            if (other.editorial != null)
                return false;
        } else if (!editorial.equals(other.editorial))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        if (biblioteca == null) {
            if (other.biblioteca != null)
                return false;
        } else if (!biblioteca.equals(other.biblioteca))
            return false;
        if (prestamos == null) {
            if (other.prestamos != null)
                return false;
        } else if (!prestamos.equals(other.prestamos))
            return false;
        return true;
    }
}
