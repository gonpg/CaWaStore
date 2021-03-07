package es.gonja.producto;

import es.gonja.promocion.Promocion;
import es.gonja.promocion.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.gonja.producto.Producto;
import es.gonja.producto.ProductoRepository;
import es.gonja.promocion.Promocion;
import es.gonja.promocion.PromocionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PromocionRepository promocionRepository;

    @GetMapping("/productos")
    public String tienda(Model model) {
        List<Producto> productos = productoRepository.findAll();
        productos.sort(Comparator.comparing(Producto::getNombre));

        model.addAttribute("productos", productos);

        return "tienda";
    }

    @GetMapping("/productos/{id}")
    public String verProducto(Model model, @PathVariable long id) {
        Optional<Producto> producto = productoRepository.findById(id);

        model.addAttribute("producto", producto.get());

        return "detalle_producto";
    }

    @PostMapping("/busqueda")
    public String findByName(Model model, @RequestParam String nombre) {
        List<Producto> lista_producto = productoRepository.findByNameIsLike(nombre);
        model.addAttribute("productos", lista_producto);
        return "tienda";
    }

    @PostMapping("/productos/nuevoProducto")
    public String createProduct(Model model, @RequestParam String nombre, @RequestParam float precio, @RequestParam int promocion,
                                @RequestParam int stock, @RequestParam String categoria,
                                @RequestParam String url, @RequestParam String descripcion) {

        Producto nuevoProducto = new Producto(nombre, categoria, precio, stock, descripcion, url);
        productoRepository.save(nuevoProducto);

        if (promocion > 0) {
            Promocion nuevaPromocion = new Promocion(promocion);
            nuevaPromocion.setProducto(nuevoProducto);
            promocionRepository.save(nuevaPromocion);
        }

        model.addAttribute("id", nuevoProducto.getId());

        return "producto_anadido";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProduct(Model model, @PathVariable long id) {
        productoRepository.deleteById(id);
        model.addAttribute("id", id);
        return "producto_eliminado";
    }
}

