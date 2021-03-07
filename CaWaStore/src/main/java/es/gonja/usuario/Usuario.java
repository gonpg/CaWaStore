package es.gonja.usuario;


import es.gonja.pedido.*;
import es.gonja.resena.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Usuario implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Size(min = 2, max = 16)
    @Column(unique = true)
    @NotBlank(message = "Nombre de usuario no puede estar en blanco, campo obligatorio")
    private String NombreUsuario;

    @Column(unique = true)
    @NotBlank(message = "Email no puede estar en blanco, campo obligatorio")
    private String email;

    @NotBlank(message = "Contraseña no puede estar en blanco, campo obligatorio")
    private String contrasena;

    @Column(unique = true)
    private String TarjetaCredito;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> resenas;

	protected Usuario() {
    }

    public Usuario( String nombreusuario, String email, String contrasena, String... roles) {
        this.NombreUsuario = nombreusuario;
        this.email = email;
        this.roles = new ArrayList<>(Arrays.asList(roles));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTarjetaCredito() {
        return TarjetaCredito;
    }

    public void setTarjetaCredito(String TarjetaCredito) {
        this.TarjetaCredito = TarjetaCredito;
    }

    public List<Pedido> getPedido() {
        return pedidos;
    }

    public void setPedidoss(List<Pedido> pedido) {
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

    public void anadirResena(Resena resena) {
        this.resenas.add(resena);
    }

    public void eliminarResena(Resena resena) {
        this.resenas.remove(resena);
    }
}
