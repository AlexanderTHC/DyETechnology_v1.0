/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thcart.dyetechnology.model.entities.DetalleOrden;
import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.ICarritoRepository;
import com.thcart.dyetechnology.model.service.IDetalleOrdenService;
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
    IDetalleOrdenService detalleOrdenService;

    @Autowired
    IUsuarioService usuarioService;

    //CAMBIAR A SERVICES
    @Autowired
    ICarritoRepository carritoRepository;

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
    public String addCarrito(@RequestParam Long productId, @RequestParam Integer cantidad, Model model,RedirectAttributes redirect, Principal principal) {

        model.addAttribute("titulo", "DyE Technology - Carrito");
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(productId);
        LOGGER.info("Producto agrego al Carrito: {}", optionalProducto.get());
        LOGGER.info("Cantidad: {}", cantidad);

        
        if(principal == null) {
            redirect.addFlashAttribute("errorCarrito", "Debe iniciar sesión para continuar");
            LOGGER.info("Debe iniciar sesión");
            //AQUI COLOCAR UN MENSAGE DE SWEET ALERT !!
            return "redirect:/detalleproducto/"+productId;
         }
        producto = optionalProducto.get();
        //
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        detalleOrden.setActivo(true);
        detalleOrden.setUsuario(getUsuario(principal));
        

        // validar que le producto no se añada 2 veces
        Long idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        detalleOrdenService.guardar(detalleOrden);
        //prueba de z4x.
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "carrito";
    }

    @GetMapping("/verCarrito")
	public String verCarrito(Model model, Principal principal) {

        model.addAttribute("titulo", "DyE Technology - Carrito");
            Usuario usuario = getUsuario(principal);
            System.out.println(""); //
            System.out.println(""); //
            System.out.println("Usuario encontrado: "); //
            System.out.println(usuario);    
            model.addAttribute("carrito", carritoRepository.findAll()); //carritoRepository.findByUsuario(usuario));
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


    //ver orden
	@GetMapping("/orden")
	public String verOrden(Model model) {
	
		model.addAttribute("carrito", detalles);
		model.addAttribute("orden", orden);
		
		return "resumenorden";
	}
	
	 //Guardar la compra
	@GetMapping("/guardarOrden")
	public String guardarOrden(Principal principal,RedirectAttributes attribute) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//Obtener usuario y guardar los datos en la Orden
		orden.setUsuario(getUsuario(principal));
		ordenService.guardar(orden);
		
		//Guardar los detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
            dt.setActivo(false); //prueba 1
			detalleOrdenService.guardar(dt);
		}
		
		//Limpiar lista y orden, para luego visualizarlo limpio.
		orden = new Orden();
		detalles.clear();

		attribute.addFlashAttribute("ordenGenerada", "ORDEN GENERADA");
		return "redirect:/";
	}

    //Visualizar mis Compras
	@GetMapping("/misCompras")
	public String misCompras(Model model, Principal principal) {

    model.addAttribute("titulo", "DyE Technology - Mis Ordenes");
    //Busca la orden por el Usuario que se encuentra logueado en ese momento.
    model.addAttribute("ordenes", ordenService.buscarOrdenUsuario(getUsuario(principal)));

		return "misCompra";
	}

    //Visualizar Detalles de Compras
	@GetMapping("/detalle/compra/{id}")
	public String detalleCompra(@PathVariable("id") long id,Model model, Principal principal) {

    model.addAttribute("titulo", "DyE Technology - Detalle de la Compra");
    Orden orden = ordenService.buscarPorId(id);

    //Para visualizar el Nro de Orden:
    model.addAttribute("ordenes", ordenService.buscarPorId(id));

    //Para visualizar el detalle de la orden:
    model.addAttribute("detalles", orden.getDetalle());

		return "detalleCompra";
	}


    //OBTENER USUARIO LOGUEADO:
    private Usuario getUsuario(Principal principal) {
        Usuario usuario = ordenService.obtenerUsuarioPor(principal.getName());
        return usuario;
    }

    //
    private Long getUserId(Principal principal) {
        String userString = principal.toString();
        int startIndex = userString.indexOf("id=");
        int endIndex = userString.indexOf(",", startIndex);
        return Long.valueOf(userString.substring(startIndex + 3, endIndex));
    }
}
