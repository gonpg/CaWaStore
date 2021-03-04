package es.gonja.resena;


import es.gonja.producto.*;
import es.gonja.usuario.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Resena implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Fecha no puede ser nula")
    private Date fecha;

    @Size(min = 10, max = 144)
    private String TextoResena;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Producto producto;

    protected Resena() {
    }

    public Resena(Producto producto, Usuario usuario, Date fecha, String TextoResena) {
        this.producto = producto;
        this.usuario = usuario;
        this.fecha = fecha;
        this.TextoResena = TextoResena;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProduct(Producto producto) {
        this.producto = producto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDate() {
        return fecha;
    }

    public void setDate(Date fecha) {
        this.fecha = fecha;
    }

    public String getTextoResena() {
        return TextoResena;
    }

    public void setTextoResena(String resena) {
        this.TextoResena = resena;
    }

}