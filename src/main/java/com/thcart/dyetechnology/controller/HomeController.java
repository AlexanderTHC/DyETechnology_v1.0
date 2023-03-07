package com.thcart.dyetechnology.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thcart.dyetechnology.model.entities.Categoria;
import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.entities.SubCategoria;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.ICarritoRepository;
import com.thcart.dyetechnology.model.repository.IOrdenRepository;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;
import com.thcart.dyetechnology.model.service.CategoriaServiceImpl;
import com.thcart.dyetechnology.model.service.IOrdenService;
import com.thcart.dyetechnology.model.service.IProductoService;
import com.thcart.dyetechnology.model.service.IUsuarioService;
import com.thcart.dyetechnology.model.service.SubCategoriaServiceImpl;

@Controller
public class HomeController {

    // Para visualiar en consola, para un log de todo lo que se ejecuta en el
    // programa::
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    IProductoService productoService;

    @Autowired
    IOrdenRepository ordenRepository;

    @Autowired
    IOrdenService ordenService;

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IUsuarioRepository usuarioRepository;

    // CAMBIAR A SERVICES
    @Autowired
    ICarritoRepository carritoRepository;

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private SubCategoriaServiceImpl subCategoriaService;



    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        model.addAttribute("titulo", "DyE Technology - Inicio");
        model.addAttribute("subtitle", "Tienda DyE Technology Oficial");
        model.addAttribute("productos", productoService.buscarTodos());

        return "home";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("titulo", "DyE Technology - Inicio");
        return "contacto";
    }

    @GetMapping("detalleproducto/{id}")
    public String detalleProducto(@PathVariable("id") long id, Model model) {
        LOGGER.info("ID producto enviado como parametro {}", id);
        model.addAttribute("titulo", "DyE Technology - Detalles");

        model.addAttribute("productos", productoService.buscarPorId(id));
        return "detalleProducto";
    }

    // Visualizar mis Compras
    @GetMapping("/compras")
    public String misCompras(Model model, Principal principal) {

        model.addAttribute("titulo", "DyE Technology - Mis Ordenes");

        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        List<Orden> ordenes = ordenRepository.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);

        

        return "misCompra";
    }

    // Visualizar Detalles de Compras
    @GetMapping("/compras/{id}")
    public String detalleCompra(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("titulo", "DyE Technology - Detalle de la Compra");

        Orden orden = ordenRepository.findById(id).get();
        model.addAttribute("ordenes", orden);

        LOGGER.info("Compras: {}", orden);

        return "detalleCompra";
    }


    // Buscar producto
    @GetMapping(value = "/_fetch-products/{query}", produces = {"application/json"})
    private @ResponseBody List<Producto> searchProducts(@PathVariable String query)
    {
        return productoService.buscarPor(query);
    }

    @GetMapping("/buscar/")
    public String searchProducts(@RequestParam(required = false) String query, @RequestParam(required = false) Long subcategoria, Model model)
    {
        List<Producto> productos = (subcategoria == null) ? productoService.buscarPor(query) : productoService.buscarPorSubCategoria(subcategoria);
        List<Categoria> categorias = categoriaService.buscarTodos();
        List<SubCategoria> subCategorias = subCategoriaService.buscarTodos();

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("subcategorias", subCategorias);
        model.addAttribute("query", query);
        return "buscarProductos";
    }
}
