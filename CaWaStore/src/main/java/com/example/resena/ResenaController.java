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
    public String linkTowriteResena(Model model, @PathVariable long id, @RequestParam String reviewText) {
        List<Usuario> users = usuarioRepository.findAll();
        Usuario user = users.get(0);//Prueba
        LocalDate localDate = java.time.LocalDate.now();
        Date fecha = java.sql.Date.valueOf(localDate);

        Optional<Producto> producto = productoRepository.findById(id);

        Resena resena = new Resena(producto.get(), user, fecha, reviewText);
        resenaRepository.save(resena);

        model.addAttribute("id", id);
        return "resenaAgregada";
    }

}