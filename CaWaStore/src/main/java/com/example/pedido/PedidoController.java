package com.example.pedido;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/nuevoPedido")
    public String showProductList(Model model) {
        List<Producto> product_list = productoRepository.findAll();
        model.addAttribute("productos", product_list);
        return "nuevo_pedido";
    }
	
	@PostMapping("/realizado")
	public RedirectView order(Model model, @RequestParam(name="product_list") String[] productNames) {
		List<Usuario> users = usuarioRepository.findAll();
		Usuario user = users.get(0);//Prueba
	    LocalDate localDate = java.time.LocalDate.now();
	    Date fecha = java.sql.Date.valueOf(localDate);

		List<Producto> productos = new ArrayList<>();
		for (String productName : productNames) {
			Producto product = productoRepository.findByNombre(productName);
			productos.add(product);
		}
		
		Pedido pedido = new Pedido(user, fecha, productos);
		pedidoRepository.save(pedido);

		 RedirectView redirectView = new RedirectView("profile");
	        redirectView.setExposeModelAttributes(false);
	        return redirectView;
    }
}
