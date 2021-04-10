package com.example.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/perfil")
    public String userProfileView(Model model, HttpServletRequest request) {
        Usuario user = usuarioRepository.findByNombreUsuario(request.getUserPrincipal().getName()).get();
        model.addAttribute("usuario", user);
        return "perfil_usuario";
    }
    

    @GetMapping("/signup")
    public String registerView() {
        return "signup";
    }
    
    @PostMapping("/nuevoUsuario")
    public String registrarse(Model model, @RequestParam String nombreUsuario,
                              @RequestParam String email,@RequestParam String contrasenya) {

        Usuario usuario = new Usuario(nombreUsuario, email, contrasenya);
        usuarioRepository.save(usuario);
        model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

        return "registrado";
    }
}
