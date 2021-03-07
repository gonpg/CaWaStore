package es.gonja.resena;

import es.gonja.producto.Producto;
import es.gonja.producto.ProductoRepository;
import es.gonja.usuario.Usuario;
import es.gonja.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ResenaController {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/productos/{id}/nuevaResena")
    public String linkTowriteResena(Model model, @PathVariable long id, @RequestParam String TextoResena, @RequestParam Long IDproducto) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario usuario = usuarios.get(0);
        LocalDate localDate = java.time.LocalDate.now();
        Date fecha = java.sql.Date.valueOf(localDate);

        Optional<Producto> producto = productoRepository.findById(IDproducto);

        Resena resena = new Resena(producto.get(), usuario, fecha, TextoResena);
        resenaRepository.save(resena);

        model.addAttribute("id", id);
        return "reviewAdded";
    }

}