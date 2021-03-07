package es.gonja.producto;

import es.gonja.pedido.*;
import es.gonja.promocion.*;
import es.gonja.resena.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 2, max = 50)
    @NotBlank(message = "Nombre del producto no puede estar en blanco, campo obligatorio")
    private String nombre;

    @Size(min = 2, max = 25)
    @NotBlank(message = "La categoría del producto no puede estar en blanco, campo obligatorio")
    private String categoria;

    @DecimalMin("0.01")
    private float precio;

    @Min(0)
    @NotNull(message = "Stock no puede ser nulo")
    private int stock;

    private String descripcion;
    private String enlace_imagen;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> resenas;

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Promocion promocion;

    protected Producto() {
    }

    public Producto(String nombre, String categoria, float precio, int stock, String descripcion, String enlace_imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.enlace_imagen = enlace_imagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace_imagen() {
        return enlace_imagen;
    }

    public void setEnlace_imagen(String enlace_imagen) {
        this.enlace_imagen = enlace_imagen;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedidos = pedido;
    }

    public void AnadirPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void EliminarPedido(Pedido pedido) {
        this.pedidos.remove(pedido);
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public void AnadirResena(Resena resena) {
        resenas.add(resena);
    }

    public void EliminarRsena(Resena resena) {
        resenas.remove(resena);
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

}