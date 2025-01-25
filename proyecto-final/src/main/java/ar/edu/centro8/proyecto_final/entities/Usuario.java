package ar.edu.centro8.proyecto_final.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private Integer telefono;
    private String email;

    @OneToOne //bidireccional fk-pk
    @JsonIgnore
    @JoinColumn(name = "id_libro", unique = true)
    private Libro libro;
    
    @Column(name = "nombre_libro")
    private String nombreLibro;
    
    private Double precioTotal;
    
     public Usuario() {}

     public Usuario(Long id, String nombre, String apellido, int dni, int telefono, String email, Libro libro, String nombreLibro, Double precioTotal) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.libro = libro;
        this.nombreLibro = nombreLibro;
        this.precioTotal = precioTotal;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }


    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}
