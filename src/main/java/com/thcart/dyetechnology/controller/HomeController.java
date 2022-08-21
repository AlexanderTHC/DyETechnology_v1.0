/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thcart.dyetechnology.model.entities.DetalleOrden;
import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.service.IProductoService;

@Controller
public class HomeController {

    // Para visualiar en consola, para un log de todo lo que se ejecuta en el
    // programa::
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    IProductoService productoService;

    // Para almacenar los detalles de la Orden.
    // Y utilizar como variable Global...
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    // Almacena la Orden: Datos de la orden.
    Orden orden = new Orden();

    @GetMapping({ "/", "/home" })
    public String home(Model model) {//
        model.addAttribute("carrito", detalles);
		model.addAttribute("orden", orden);
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

    // CAMBIAR ESTE METODO POR ALGUNO NUEVO UTILIZADO EN LAS CLASES - ESTO ES PARA
    // PRUEBA!!!
    // AGREGAR PRODUCTOS AL CARRITO
    @PostMapping("/carrito")
    public String addCarrito(@RequestParam Long id, @RequestParam Integer cantidad, Model model) {

        model.addAttribute("titulo", "DyE Technology - Carrito");
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);
        LOGGER.info("Producto agrego al Carrito: {}", optionalProducto.get());
        LOGGER.info("Cantidad: {}", cantidad);

        producto = optionalProducto.get();
        //
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        // validar que le producto no se añada 2 veces
        Long idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "carrito";
    }

    @GetMapping("/verCarrito")
	public String verCarrito(Model model) {

        model.addAttribute("titulo", "DyE Technology - Carrito");
		
		model.addAttribute("carrito", detalles);
		model.addAttribute("orden", orden);
		

		return "carrito";
	}

    // PARA QUITAR PRODUCTOS DE LA LISTA DE CARRITO
    @GetMapping("/delete/carrito/{id}")
    public String delProdCarrito(@PathVariable("id") long id, Model model) {

        // lista nueva de prodcutos
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }

        // poner la nueva lista con los productos restantes
        detalles = ordenesNueva;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "carrito";
    }


    //VERORDEN
	@GetMapping("/orden")
	public String verOrden(Model model) {
		
		model.addAttribute("carrito", detalles);
		model.addAttribute("orden", orden);
		
		return "resumenorden";
	}
	
	/* guardar la orden
	@GetMapping("/guardarOrden")
	public String guardarOrden() {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
		
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		//guardar detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		///limpiar lista y orden
		orden = new Orden();
		detalles.clear();
		
		return "redirect:/";
	}*/
}
