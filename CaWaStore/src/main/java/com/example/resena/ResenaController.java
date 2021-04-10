package com.example.resena;

import com.example.producto.Producto;
import com.example.producto.ProductoRepository;
import com.example.usuario.Usuario;
import com.example.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    public String linkTowriteReview(Model model, @PathVariable long id, @RequestParam String reviewText, HttpServletRequest request) {
        Usuario user = usuarioRepository.findByNombreUsuario(request.getUserPrincipal().getName()).get();
        LocalDate localDate = java.time.LocalDate.now();
        Date fecha = java.sql.Date.valueOf(localDate);

        Optional<Producto> producto = productoRepository.findById(id);

        Resena resena = new Resena(producto.get(), user, fecha, reviewText);
        resenaRepository.save(resena);

        model.addAttribute("id", id);
        return "resenaAgregada";
    }

}