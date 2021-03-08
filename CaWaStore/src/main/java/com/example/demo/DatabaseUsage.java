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
	private PedidoRepository pedidoRepository;
	 
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private PromocionRepository promocionRepository;

   

    @Override
    public void run(String... args) throws Exception {

        Usuario u1 = new Usuario("bustamante_", "liz@sen.ma.gov", "12345678");
        Usuario u2 = new Usuario("pepe28", "pepe@gmail.com", "12345678");
        Usuario u3 = new Usuario("juan98", "juan98@gmail.com", "12345678");
        Usuario u4 = new Usuario("santi_ago", "santi00@outlook.es", "12345678");
        Usuario u5 = new Usuario("alonso12", "alonso12@gmail.es", "12345678");

        usuarioRepository.save(u1);
        usuarioRepository.save(u2);
        usuarioRepository.save(u3);
        usuarioRepository.save(u4);
        usuarioRepository.save(u5);


        Producto p1 = new Producto("Solán de Cabras", "alimentación", 1.2F, 5,
                "Agua mineral natural.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201901/21/00110237800353____1__640x640.jpg");

        Producto p2 = new Producto("Chocolate Milka", "alimentación", 0.99F, 3,
                "Puede contener Gluten y trazas de frutos secos.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201808/10/00120646700896____22__600x600.jpg");

        Producto p3 = new Producto("BUSTAMANTE Muy Mío", "higiene", 6.95F, 111,
                "Un seductor natural que despierta pasiones. Confianza en sí mismo, entrega, emperamento, carisma, naturalidad, espíritu de lucha? concentrados en una fragancia.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/23/00155760200737____1__600x600.jpg");

        Producto p4 = new Producto("DODOT Activity toallitas infantiles", "higiene", 8.63F, 56,
                "Máxima comodidad y protección en la piel, su capa absorvente cora-soft es suave como una pluma.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201905/10/00155763100421____15__600x600.jpg");

        Producto p5 = new Producto("ASTURIANA leche semidesnatada", "alimentación", 0.89F, 23,
                "Leche UHT semidesnatada sin lactosa",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201902/07/00120912000260____23__600x600.jpg");

        Producto p6 = new Producto("Vino blanco", "alimentación", 13.90F, 45,
                "Color amarillo verdoso pálido con destellos alimonados, brillante, limpio y cristalino. Aromas de intensidad media con claras notas minerales.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/24/00118775700711____2__600x600.jpg");

        productoRepository.save(p1);
        productoRepository.save(p2);
        productoRepository.save(p3);
        productoRepository.save(p4);
        productoRepository.save(p5);
        productoRepository.save(p6);


        LocalDate localDate = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);

        Resena r1 = new Resena(p1, u1, date, "Cumple su funcion");
        Resena r2 = new Resena(p2, u2, date, "Cumple su funcion");
        Resena r3 = new Resena(p3, u3, date, "Cumple su funcion");
        Resena r4 = new Resena(p4, u4, date, "Cumple su funcion");
        Resena r5 = new Resena(p5, u5, date, "Cumple su funcion");

        resenaRepository.save(r1);
        resenaRepository.save(r2);
        resenaRepository.save(r3);
        resenaRepository.save(r4);
        resenaRepository.save(r5);


        List<Producto> l1 = new ArrayList<>();
        l1.add(p1);
        l1.add(p2);

        List<Producto> l2 = new ArrayList<>();
        l2.add(p3);
        l2.add(p4);
        l2.add(p5);

        List<Producto> l3 = new ArrayList<>();
        l3.add(p3);

        Pedido o1 = new Pedido(u1, date, l1);
        Pedido o2 = new Pedido(u3, date, l2);
        Pedido o3 = new Pedido(u4, date, l3);
        Pedido o4 = new Pedido(u3, date, l3);

        pedidoRepository.save(o1);
        pedidoRepository.save(o2);
        pedidoRepository.save(o3);
        pedidoRepository.save(o4);


        Promocion pr1 = new Promocion(20);
        pr1.setProducto(p3);

        promocionRepository.save(pr1);


        List<Producto> query = productoRepository.findAll();
        for (Producto p : query) {
            System.out.println("PRODUCTO= " + p.getNombre());
        }

        List<Resena> query2 = resenaRepository.findAll();
        for (Resena r : query2) {
            System.out.println("REVIEW= " + "producto: " + r.getProducto().getNombre() + " | " + r.getTexto());
        }

        List<Promocion> query3 = promocionRepository.findAll();
        for (Promocion r : query3) {
            System.out.println("PROMOTION= " + "producto: " + r.getProducto().getNombre() + " | " + r.getPromocion());
        }
    }
}
