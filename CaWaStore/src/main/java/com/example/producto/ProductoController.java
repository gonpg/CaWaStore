package com.example.producto;

import com.example.promocion.Promocion;
import com.example.promocion.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PromocionRepository promocionRepository;
    
    @GetMapping("/")
    public String redirectRoot() {
    	return "forward:/productos";
    }

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

        return "detalles_producto";
    }

    @PostMapping("/busqueda")
    public String findByNombre(Model model, @RequestParam String nombre) {
        List<Producto> producto_list = productoRepository.findByNombreIsLike(nombre);
        model.addAttribute("productos", producto_list);
        return "tienda";
    }

    @PostMapping("/productos/nuevoProducto")
    public String createProducto(Model model, @RequestParam String nombre, @RequestParam float precio, @RequestParam (defaultValue= "0") int promocion,
                                @RequestParam int stock, @RequestParam String categoria,
                                @RequestParam (defaultValue="https://www.acegal.org/wp-content/uploads/2017/08/sin-imagen12.jpg")String url, @RequestParam String descripcion) {

        Producto newProducto = new Producto(nombre, categoria, precio, stock, descripcion, url);
        productoRepository.save(newProducto);

        if (promocion > 0) {
            Promocion newPromocion = new Promocion(promocion);
            newPromocion.setProducto(newProducto);
            promocionRepository.save(newPromocion);
        }

        model.addAttribute("id", newProducto.getId());

        return "producto_agregado";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProducto(Model model, @PathVariable long id) {
        productoRepository.deleteById(id);
        model.addAttribute("id", id);
        return "producto_eliminado";
    }
    @GetMapping("/subir_producto")
    public String createProductView() {
        return "subir_producto";
    }
}