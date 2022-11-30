package com.thcart.dyetechnology.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;
import com.thcart.dyetechnology.model.service.ProductoServiceImpl;


@Controller
@RequestMapping(value = "/carrito")
public class CarritoController
{
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ProductoServiceImpl productService;

    
    @GetMapping(value = {"", "/"})
    public String index(Model model, Principal principal)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()); // Obtener el Usuario a través del email

        model.addAttribute("carrito", usuario.getCarrito()); // Enviar el carrito del usuario
        return "carrito";
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}) // Añadir un producto al carrito del usuario
    public String add(@RequestParam Long productoId, @RequestParam Integer cantidad, Principal principal)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()); // Obtener el Usuario a través del email
        Producto producto = productService.buscarPorId(productoId); // Obtener el producto que se añadirá al carrito
        List<Carrito> carrito = usuario.getCarrito(); // Obtener el carrito actual del usuario (lista)

        Carrito item = new Carrito(); // Crear nuevo item de Carrito para añadirlo al carrito del usuario
        item.setCantidad(cantidad);
        item.setProducto(producto);
        carrito.add(item); // Añadir item creado al carrito del usuario

        usuario.setCarrito(carrito); // Actualizar carrito del usuario con el nuevo producto añadido
        usuarioRepository.save(usuario); // Guardar

        return "redirect:/carrito/"; // Redireccionar a la vista de carrito
    }

    @GetMapping("/quitar/{productoId}") // Quitar un producto del carrito del usuario
    public String quitar(@PathVariable Long productoId, Principal principal)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()); // Obtener el Usuario a través del email
        List<Carrito> carrito = usuario.getCarrito(); // Obtener el carrito del usuario

        carrito.removeIf(item -> item.getProducto().getId().equals(productoId)); // Buscar entre el carrito si algún producto coincide con el productoId y quitarlo de la lista
        usuarioRepository.save(usuario); // Guardar datos del usuario

        return "redirect:/carrito";
    }
}
