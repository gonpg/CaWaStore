package com.example;

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
import org.springframework.dao.DataIntegrityViolationException;

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
    public void run(String... args){
 /*   	try {
        Usuario u1 = new Usuario("joseAngel", "gonzalo.perez.1f@gmail.com", "12345678" , "USER");
        Usuario u2 = new Usuario("gonzalo", "gonzalo.perez.1f@gmail.com", "12345678", "USER");
        Usuario u3 = new Usuario("paco", "gonzalo.perez.1f@gmail.com", "12345678", "USER");
        Usuario administrador = new Usuario("admin", "gonzalo.perez.1f@gmail.com", "1234", "USER", "ADMIN");
       

        usuarioRepository.save(u1);
        usuarioRepository.save(u2);
        usuarioRepository.save(u3);
        usuarioRepository.save(administrador);


        Producto p1 = new Producto("BARRAS PARALELAS", "Crossfit", 40.5F, 5,
                "Barras paralelas",
                "https://contents.mediadecathlon.com/p1103588/k$5cc7c279f32cbbd44a5abc4ab065cd54/sq/Barras+paralelas+para+dips+cross+training+musculaci+n+training+station+100.jpg");

        Producto p2 = new Producto("BALON MEDICINAL 3KG", "Mancuernas", 10.65F, 3,
                "Balon medicinal 3KG",
                "https://contents.mediadecathlon.com/p1175605/k$26172214da5a9910dd5ab780e42ed8a0/sq/BAL+N+MEDICINAL+3+kg+Di+metro+22+cm+AMARILLO.jpg");

        Producto p3 = new Producto("CHALECO DE 5KG", "Mancuernas", 30.5F, 100,
                "Chaleco lastrado con pesas de 5KG",
                "https://contents.mediadecathlon.com/p1643571/k$3c00962c129eb3e87475fd6769961ebd/sq/Chaleco+lastrado+cross+training+musculaci+n+5+kg.jpg");
        
        Producto p4 = new Producto("SET DE MANCUERNAS", "Mancuernas", 40.0F, 50,
                "Set de mancuernas roscadas de 20KG",
                "https://contents.mediadecathlon.com/p1183101/k$28bf0f41482fecbf3eb6a50bb405059a/sq/Kit+mancuernas+musculaci+n+20+kg+de+rosca.jpg");
        
        Producto p5 = new Producto("MUÑEQUERA", "Material Crossfit", 5.0F, 5,
                "Muñequeras capaces de mantener rigidez en la muñeca para ejercicios de pino o barra",
                "https://contents.mediadecathlon.com/p1748366/k$2313dfd5c5758634ef14b43b1f2fa52b/sq/MU+EQUERAS+MUSCULACI+N+CROSSTRAINING+AZUL.jpg");
        
        Producto p6 = new Producto("BARRA DE TRACCIÓN CROSS ", "Material Crossfit", 50.0F, 4,
                "Se fija en la pared y permite practicar las tracciones utilizando varios agarres.",
                "https://contents.mediadecathlon.com/p1134412/k$a7f08ac52151c148b9158f09396e9968/sq/barra-de-traccion-cross-training-musculacion-pull-up-bar-900.jpg");
       
        Producto p7 = new Producto("AGARRADERAS PARA FLEXIONES 4 EN 1 REFUERZO MUSCULAR", "Material Crossfit", 13.0F, 5,
                "Agarraderas para poder ir aumentando la dificultad de los ejercicios y trabajar todo el cuerpo.",
                "https://contents.mediadecathlon.com/p1913686/k$465992033b03aaff648093fd4bf6dcc3/sq/agarraderas-para-flexiones-4-en-1-refuerzo-muscular.jpg");
      
        Producto p8 = new Producto("JUMP BOX CAJON PLIOMÉTRICO CROSS TRAINING MUSCULACIÓN MADERA", "Material Crossfit", 45.0F, 10,
                "Cajón de madera para realizar el trabajo de pliometría durante los WODs de crosstraining. Un uso regular te permitirá mejorar fácilmente los estiramientos y la condición física.",
                "https://contents.mediadecathlon.com/p1334431/k$642faf379181d25eba9cde603e5f9840/sq/jump-box-cajon-pliometrico-cross-training-musculacion-madera.jpg");
        
        Producto p9 = new Producto("BANDA ELÁSTICA CROSS-TRAINING MUSCULACIÓN - TRAINING BAND 45 KG", "Material Crossfit", 14.99F, 5,
                "El Training Band permite a la vez tonificar, asistirte durante las sesiones y estirar. Este accesorio polivalente será tu mejor aliado durante las sesiones.",
                "https://contents.mediadecathlon.com/p1834326/k$55eb976ad3c473afb210136402a1be08/sq/banda-elastica-cross-training-musculacion-training-band-45-kg.jpg");
        
        Producto p10 = new Producto("MAGNESIO LÍQUIDO DE ESCALADA SOFT GRIP", "Material Crossfit", 5.0F, 5,
                "Con poco porcentaje de alcohol (30%) para cuidar la piel de las manos, seca menos las manos después de su uso. Se puede utilizar como complemento del magnesio en polvo.",
                "https://contents.mediadecathlon.com/p1235916/k$0efe57953a4647a45b7001f524d01624/sq/magnesio-liquido-de-escalada-soft-grip.jpg");

        
        productoRepository.save(p1);
        productoRepository.save(p2);
        productoRepository.save(p3);
        productoRepository.save(p4);
        productoRepository.save(p5);
        productoRepository.save(p6);
        productoRepository.save(p7);
        productoRepository.save(p8);
        productoRepository.save(p9);
        productoRepository.save(p10);
       


     LocalDate localDate = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);

        Resena r1 = new Resena(p1, u1, date, "Buen producto");
        Resena r2 = new Resena(p2, u2, date, "Buen producto");
        Resena r3 = new Resena(p1, u2, date, "Bueno, pero no mucho");
       
        resenaRepository.save(r1);
        resenaRepository.save(r2);
        resenaRepository.save(r3);
        

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
        
        Promocion pr2 = new Promocion(20);
        pr2.setProducto(p1);
        
        Promocion pr3 = new Promocion(15);
        pr3.setProducto(p5);

        promocionRepository.save(pr1);
        promocionRepository.save(pr2);
        promocionRepository.save(pr3);
        
    	} catch (DataIntegrityViolationException ex) {}
*/
    }


}

       