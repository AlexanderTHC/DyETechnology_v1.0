package com.thcart.dyetechnology.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.ICarritoRepository;
import com.thcart.dyetechnology.model.service.IOrdenService;
import com.thcart.dyetechnology.model.service.IProductoService;
import com.thcart.dyetechnology.model.service.IUsuarioService;

@Controller
public class HomeController {

    // Para visualiar en consola, para un log de todo lo que se ejecuta en el
    // programa::
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    IProductoService productoService;

    @Autowired
    IOrdenService ordenService;

    @Autowired
    IUsuarioService usuarioService;

    // CAMBIAR A SERVICES
    @Autowired
    ICarritoRepository carritoRepository;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        model.addAttribute("titulo", "DyE Technology - Inicio");
        model.addAttribute("subtitle", "Tienda DyE Technology Oficial");
        model.addAttribute("productos", productoService.buscarTodos());

        return "home";
    }

    @GetMapping("detalleproducto/{id}")
    public String detalleProducto(@PathVariable("id") long id, Model model) {
        LOGGER.info("ID producto enviado como parametro {}", id);
        model.addAttribute("titulo", "DyE Technology - Detalles");

        model.addAttribute("productos", productoService.buscarPorId(id));
        return "detalleProducto";
    }

    // Visualizar mis Compras
    @GetMapping("/misCompras")
    public String misCompras(Model model, Principal principal) {

        model.addAttribute("titulo", "DyE Technology - Mis Ordenes");
        // Busca la orden por el Usuario que se encuentra logueado en ese momento.
        model.addAttribute("ordenes", ordenService.buscarOrdenUsuario(getUsuario(principal)));

        return "misCompra";
    }

    // Visualizar Detalles de Compras
    @GetMapping("/detalle/compra/{id}")
    public String detalleCompra(@PathVariable("id") long id, Model model, Principal principal) {

        model.addAttribute("titulo", "DyE Technology - Detalle de la Compra");
        Orden orden = ordenService.buscarPorId(id);

        // Para visualizar el Nro de Orden:
        model.addAttribute("ordenes", ordenService.buscarPorId(id));

        // Para visualizar el detalle de la orden:
        model.addAttribute("detalles", orden.getOrdenItems());

        return "detalleCompra";
    }

    // OBTENER USUARIO LOGUEADO:
    private Usuario getUsuario(Principal principal) {
        Usuario usuario = ordenService.obtenerUsuarioPor(principal.getName());
        return usuario;
    }
}
