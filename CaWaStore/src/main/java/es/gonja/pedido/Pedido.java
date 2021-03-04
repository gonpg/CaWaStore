package es.gonja.pedido;


import es.gonja.usuario.*;
import es.gonja.producto.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "La fecha no puede ser nula")
    private Date fecha;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Producto> productos;

    protected Pedido() {
    }

    public Pedido(Usuario usuario, Date fecha, List<Producto> productos) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.productos = productos;
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

    public List<Producto> getProductso() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void anadirProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        this.productos.remove(producto);
    }
    
    @Override
	public String toString() {
		return "Pedido [Usuario=" + usuario.getNombreUsuario() + ", productos=" + productos + ", Fecha=" + fecha + "]";
	}

}