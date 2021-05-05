package com.example.promocion;

import com.example.producto.Producto;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Promocion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    private int promocion;

    @OneToOne
    private Producto producto;

    protected Promocion() {
    }

    public Promocion(int promocion) {
        this.promocion = promocion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPromocion() {
        return promocion;
    }

    public void setPromocion(int promotion) {
        this.promocion = promotion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
