package com.thcart.dyetechnology.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.ICarritoRepository;
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

    @Autowired
    private ICarritoRepository carritoRepository;

    
    @GetMapping(value = {"", "/"})
    public String index(Model model, Principal principal)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()); // Obtener el Usuario a través del email

        model.addAttribute("usuario", usuario); // Enviar el carrito del usuario
        return "carrito";
    }

    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}) // Añadir un producto al carrito del usuario
    public String add(@RequestParam Long productoId, @RequestParam Integer cantidad, Principal principal)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()); // Obtener el Usuario a través del email
        Producto producto = productService.buscarPorId(productoId); // Obtener el producto que se añadirá al carrito
        List<Carrito> carrito = usuario.getCarrito(); // Obtener el carrito actual del usuario (lista)

        // Verificar si el producto ya está en el carrito del usuario
        for(Carrito i: carrito) // Iterar sobre el carrito del usuario
        {
            if(i.getProducto().getId() == productoId) // Verificar si el productoId del item coincide con el productoId que quiere añadir el usuario
            {
                Carrito item = carritoRepository.findById(i.getId()).get(); // Obtener el item (carrito)
                item.setCantidad(Math.min(item.getCantidad() + cantidad, producto.getCantidad())); // Sumar cantidad
                carritoRepository.save(item); // Guardar informacion del item
                
                return "redirect:/carrito/";
            }
        }

        // El producto no existe en el carrito del usuario, crear uno nuevo...
        Carrito item = new Carrito(); // Crear nuevo item de Carrito para añadirlo al carrito del usuario
        item.setCantidad(Math.min(cantidad, producto.getCantidad()));
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

        return "redirect:/carrito/";
    }

    @PostMapping(value = "/cambiar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Cambiar la cantidad de productos
    public @ResponseBody Map<String, Object> change(@RequestParam Integer id, @RequestParam Integer cantidad, Principal principal)
    {
        Carrito item = carritoRepository.findById(id).get(); // Obtener el item
        item.setCantidad(Math.min(cantidad, item.getProducto().getCantidad())); // Cambiar cantidad
        carritoRepository.save(item); // Guardar item

        // Obtener el usuario
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        double total = usuario.getCarrito().stream().mapToDouble(i -> (i.getProducto().getPrecio() * i.getCantidad())).sum(); // Calcular el total del carrito
        
        Map<String, Object> json = new HashMap<>();
        json.put("subTotal", item.getProducto().getPrecio() * item.getCantidad()); // Devolver el nuevo subtotal que tendrá el item
        json.put("total", total); // Devolver el total
        return json;
    }
}
