package com.example.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/perfil")
    public String userProfileView(Model model) {

        List<Usuario> users = usuarioRepository.findAll();
        Usuario user = users.get(0);
        model.addAttribute("usuario", user);
        return "perfil_usuario";
    }

    @PostMapping("/nuevoUsuario")
    public String registrarse(Model model, @RequestParam String username,
                              @RequestParam String password, @RequestParam String email) {

        Usuario user = new Usuario(username, email, password);
        usuarioRepository.save(user);
        model.addAttribute("nombreUsuario", user.getNombreUsuario());

        return "registrado";
    }
}
