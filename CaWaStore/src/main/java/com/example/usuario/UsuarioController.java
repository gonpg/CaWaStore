package com.example.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
@Controller
public class UsuarioController implements Serializable {

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

        Usuario user = usuarioRepository.save(new Usuario(nombreUsuario, email, contrasenya, "USER"));

        model.addAttribute("nombreUsuario", user.getNombreUsuario());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8080/mail/" + user.getId(), HttpMethod.GET, null, Void.class);	
        
        return "registrado";
    }
}
