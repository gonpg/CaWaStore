package com.example.demo;

import com.example.pedido.Pedido;
import com.example.pedido.PedidoRepository;
import com.example.producto.Producto;
import com.example.producto.ProductoRepository;
import com.example.promocion.Promocion;
import com.example.promocion.PromocionRepository;
import com.example.resena.Resena;
import com.example.resena.ResenaRepository;
import com.example.usuario.Usuario;
import com.example.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatabaseUsage implements CommandLineRunner {

	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ResenaRepository resenaRepository;
    
    @Autowired
	private PedidoRepository pedidoRepository;
	 
    @Autowired
    private PromocionRepository promocionRepository;

   

    @Override
    public void run(String... args) throws Exception {

        Usuario u1 = new Usuario("joseAngel", "jose@hola.com", "12345678");
        Usuario u2 = new Usuario("pepe28", "gonzalo@hola.com", "12345678");
       

        usuarioRepository.save(u1);
        usuarioRepository.save(u2);
       


        Producto p1 = new Producto("Barras paralelas", "Crossfit", 40.5F, 5,
                "Barras paralelas",
                "https://contents.mediadecathlon.com/p1103588/k$5cc7c279f32cbbd44a5abc4ab065cd54/sq/Barras+paralelas+para+dips+cross+training+musculaci+n+training+station+100.jpg");

        Producto p2 = new Producto("Balón medicinal 3KG", "Mancuernas", 10.65F, 3,
                "Balon medicinal 3KG",
                "https://contents.mediadecathlon.com/p1175605/k$26172214da5a9910dd5ab780e42ed8a0/sq/BAL+N+MEDICINAL+3+kg+Di+metro+22+cm+AMARILLO.jpg");

        Producto p3 = new Producto("Chaleco de 5KG", "Mancuernas", 30.5F, 100,
                "Chaleco lastrado con pesas de 5KG",
                "https://contents.mediadecathlon.com/p1643571/k$3c00962c129eb3e87475fd6769961ebd/sq/Chaleco+lastrado+cross+training+musculaci+n+5+kg.jpg");

        
        productoRepository.save(p1);
        productoRepository.save(p2);
        productoRepository.save(p3);
       


        LocalDate localDate = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);

        Resena r1 = new Resena(p1, u1, date, "Buen producto");
        Resena r2 = new Resena(p2, u2, date, "Buen producto");
       
       
        resenaRepository.save(r1);
        resenaRepository.save(r2);
        
        

        List<Producto> l1 = new ArrayList<>();
        l1.add(p1);
        l1.add(p2);

        List<Producto> l2 = new ArrayList<>();
        l2.add(p3);
      

        List<Producto> l3 = new ArrayList<>();
        l3.add(p3);

        Pedido o1 = new Pedido(u1, date, l1);
        Pedido o2 = new Pedido(u2, date, l2);
        Pedido o3 = new Pedido(u1, date, l3);
        Pedido o4 = new Pedido(u2, date, l3);

        pedidoRepository.save(o1);
        pedidoRepository.save(o2);
        pedidoRepository.save(o3);
        pedidoRepository.save(o4);


        Promocion pr1 = new Promocion(30);
        pr1.setProducto(p3);

        promocionRepository.save(pr1);
    }
}


       