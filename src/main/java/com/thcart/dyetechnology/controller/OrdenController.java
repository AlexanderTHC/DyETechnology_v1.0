package com.thcart.dyetechnology.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.entities.OrdenItem;
import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.IOrdenRepository;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;


@Controller
@RequestMapping("/orden")
public class OrdenController 
{
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IOrdenRepository ordenRepository;


    @GetMapping(value = {"", "/"})
    public String index(Model model, Principal principal)
    {  
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()); // Obtener el Usuario a través del email

        model.addAttribute("usuario", usuario);
        return "orden";
    }

    @GetMapping("/generar") // Generar una nueva orden
    public String generate(Principal principal, RedirectAttributes redirect,
    @ModelAttribute("observacion") String observacion)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());

        Orden orden = new Orden(); // Nueva orden
        List<OrdenItem> detalles = new ArrayList<>(); // Lista de detalles
        double total = 0.0d; // Almacena el total de la orden

        // Iterar sobre el carrito del usuario
        for(Carrito item: usuario.getCarrito())
        {
            OrdenItem detalle = new OrdenItem(); // Detalle de orden
            detalle.setProducto(item.getProducto());
            detalle.setPrecio(item.getProducto().getPrecio());
            detalle.setCantidad(item.getCantidad());
            detalle.setTotal(item.getProducto().getPrecio() * item.getCantidad());
            detalles.add(detalle); // Añadir detalle a la orden

            total += detalle.getTotal(); // Sumar al total de la orden
        }

        orden.setActivo(false);
        orden.setFechaCreacion(new Date());
        orden.setUsuario(usuario);
        orden.setTotal(total);
        orden.setOrdenItems(detalles);
        orden.setEstado("En espera");
        //OBSERVACIONES
        orden.setObservaciones(observacion);
        ordenRepository.save(orden); // Crear nueva orden

        // Vaciar carrito del usuario
        usuario.setCarrito(null);
        usuarioRepository.save(usuario);

        // Redireccionar
        redirect.addFlashAttribute("ordenGenerada", true);
        return "redirect:/";
    }
}
