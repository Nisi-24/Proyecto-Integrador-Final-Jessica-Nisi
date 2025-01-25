package ar.edu.centro8.proyecto_final.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamo {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_usuario;
    @Column(insertable = false, updatable = false)
    private Long id_libro;

    private String estado;
    private double precio;
    private  int dias;
    private double precioTotal;

    @ManyToOne 
    @JsonIgnore
    @JoinColumn(name = "id_libro")
    private Libro libro;


    public Prestamo() {}

    public Prestamo(Long id, Long id_usuario, Long id_libro, String estado, double precio, int dias,
            double precioTotal) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_libro = id_libro;
        this.estado = estado;
        this.precio = precio;
        this.dias = dias;
        this.precioTotal = precioTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_libro() {
        return id_libro;
    }

    public void setId_libro(Long id_libro) {
        this.id_libro = id_libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((id_usuario == null) ? 0 : id_usuario.hashCode());
        result = prime * result + ((id_libro == null) ? 0 : id_libro.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        long temp;
        temp = Double.doubleToLongBits(precio);
        temp = Double.doubleToLongBits(precioTotal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((libro == null) ? 0 : libro.hashCode());
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
        Prestamo other = (Prestamo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (id_usuario == null) {
            if (other.id_usuario != null)
                return false;
        } else if (!id_usuario.equals(other.id_usuario))
            return false;
        if (id_libro == null) {
            if (other.id_libro != null)
                return false;
        } else if (!id_libro.equals(other.id_libro))
            return false;
        if (estado == null) {
            if (other.estado   != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        if (Double.doubleToLongBits(precioTotal) != Double.doubleToLongBits(other.precio))
            return false;
        if (libro == null) {
            if (other.libro != null)
                return false;
        } else if (!libro.equals(other.libro))
            return false;
        return true;
    }

    
}
