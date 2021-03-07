package es.gonja.usuario;

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
    public String VistaPerfilUsuario(Model model) {

        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario usuario = usuarios.get(0);
        model.addAttribute("usuario", usuario);
        return "perfil_usuario";
    }

    @PostMapping("/nuevoUsuario")
    public String registrarse(Model model, @RequestParam String nombre, @RequestParam String nombreUsuario,
                              @RequestParam String contrasenya, @RequestParam String email) {

        Usuario usuario = new Usuario(nombre, nombreUsuario, email, contrasenya);
        usuarioRepository.save(usuario);
        model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

        return "registrado";
    }
}
