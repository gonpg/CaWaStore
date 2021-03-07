package es.gonja.promocion;

import es.gonja.producto.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Promocion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(1)
    @Max(100)
    @NotNull(message = "El valor de promoción no puede ser nulo")
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

    public void setPromocion(int promocion) {
        this.promocion = promocion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}