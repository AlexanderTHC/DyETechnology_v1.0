package com.thcart.dyetechnology.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.ICarritoRepository;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;
import com.thcart.dyetechnology.model.service.CarritoServiceImpl;
import com.thcart.dyetechnology.model.service.ProductoServiceImpl;
import com.thcart.dyetechnology.model.service.UsuarioServiceImpl;


/*@Controller
@RequestMapping(value = "/carrito", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarritoController
{
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ProductoServiceImpl productService;

    @Autowired
    private ICarritoRepository carritoRepository;
*/
    
   /* @GetMapping("/verCarrito")
	public String verCarrito(Model model) 
    {
        Usuario usuario = usuarioRepository.findByEmail(getEmail());

        model.addAttribute("titulo", "DyE Technology - Carrito");
		model.addAttribute("carrito", carritoRepository.findByUsuario(usuario));
		return "carrito2";
	}

   
    @PostMapping("/añadir")
    public String add(@RequestParam Long productId, @RequestParam Integer cantidad)
    {
        Usuario usuario = usuarioRepository.findByEmail(getEmail());
        Producto producto = productService.buscarPorId(productId);
        //usuario.setRol(null); // Sino tira error xd

        if(carritoRepository.isProductInCart(usuario.getId(), producto.getId()) == null)
        {
            Carrito carrito = new Carrito();
            carrito.setProducto(producto);
            carrito.setUsuario(usuario);
            carrito.setCantidad(cantidad);
            carritoRepository.save(carrito);
        }
        
        return "redirect:/carrito/verCarrito";
    }


    @PostMapping("/sumarcantidaddenasheeeee")
    public @ResponseBody Carrito sumarcantidaddenasheeeee(Carrito fromJs)
    {
        Carrito carrito = carritoRepository.findById(fromJs.getId()).get();

        carrito.setCantidad(fromJs.getCantidad());
        carritoRepository.save(carrito);

        //LOGGER.info("CARRITO: {}", carrito);
        return carrito;
    }*/

    private String getEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    
}
