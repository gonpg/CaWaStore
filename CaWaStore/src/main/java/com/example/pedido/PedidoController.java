package com.example.pedido;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.example.producto.Producto;
import com.example.producto.ProductoRepository;
import com.example.usuario.Usuario;
import com.example.usuario.UsuarioRepository;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/nuevo_pedido")
    public String showProductList(Model model) {
        List<Producto> product_list = productoRepository.findAll();
        model.addAttribute("productos", product_list);
        return "nuevo_pedido";
    }
	
	@PostMapping("/realizado")
    public RedirectView order(@RequestParam(name = "product_list") String[] productNames, HttpServletRequest request) {
        Usuario user = usuarioRepository.findByNombreUsuario(request.getUserPrincipal().getName()).get();
	    LocalDate localDate = java.time.LocalDate.now();
	    Date fecha = java.sql.Date.valueOf(localDate);

		List<Producto> productos = new ArrayList<>();
		for (String productName : productNames) {
			Producto product = productoRepository.findByNombre(productName);
			productos.add(product);
		}
		
		Pedido pedido = new Pedido(user, fecha, productos);
		pedidoRepository.save(pedido);

		 RestTemplate restTemplate = new RestTemplate();
		 restTemplate.exchange("http://localhost:9999/realizado/" + pedido.getId(), HttpMethod.GET, null, Void.class);

		
		 RedirectView redirectView = new RedirectView("perfil");
	        redirectView.setExposeModelAttributes(false);
	        return redirectView;
    }
	
    @GetMapping("/factura/{id}")
    public ResponseEntity<?> invoice(@PathVariable long id, HttpServletRequest request) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (!request.getUserPrincipal().getName().equalsIgnoreCase(pedido.get().getUsuario().getNombreUsuario())) {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://localhost:9999/generarFactura/" + id, HttpMethod.GET, entity, byte[].class);
    }
	
}
