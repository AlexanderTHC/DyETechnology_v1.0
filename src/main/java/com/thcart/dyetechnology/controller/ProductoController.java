package com.thcart.dyetechnology.controller;



import javax.validation.Valid;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.service.IProductoService;
import com.thcart.dyetechnology.model.service.ISubCategoriaService;


@Controller
@RequestMapping("/productos")
@SessionAttributes({ "producto" })
public class ProductoController {

    // Para visualiar en consola, para un log de todo lo que se ejecuta en el
    // programa::
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    //

   /* SIN UTILIZAR (NO FUNCIONA!)  
   @Autowired
    private UploadFileService upload;
    //
    */

    @Autowired
    IProductoService productoService;
    @Autowired
    ISubCategoriaService subcategoriaService;

    @GetMapping("/listado")
    public String verListadoProductos(Model model) {

        model.addAttribute("titulo", "DyE Technology - Productos");

        model.addAttribute("productos", productoService.buscarTodos());

        model.addAttribute("subcategorias", subcategoriaService.buscarTodos());

        return "productos/show";
    }

    @GetMapping("/nuevo") // NUEVO
    public String nuevoProducto(Model model) {

        model.addAttribute("titulo", "DyE Technology - Productos");

        model.addAttribute("subtitulo", "Nuevo Producto");

        model.addAttribute("producto", new Producto());

        model.addAttribute("subcategorias", subcategoriaService.buscarTodos());

        return "productos/form";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") long id, Model model) {

        Producto producto = (Producto) productoService.buscarPorId(id);

        model.addAttribute("subtitulo", "Editar Producto");

        model.addAttribute("producto", producto);

        model.addAttribute("subcategorias", subcategoriaService.buscarTodos());

        model.addAttribute("productAct", true);

        return "productos/form";
    }

    @PostMapping("/nuevo") // AGREGAR "" sin utilizar no funciona ---> @RequestParam("img") MultipartFile file
    public String guardarProducto(@Valid Producto producto, BindingResult result, Model model,
            RedirectAttributes redirect, SessionStatus status) {

        // VERIFICAR ERRORES EN LOS ATRIBUTOS:
        if (result.hasErrors()) {

            model.addAttribute("titulo", "DyE Technology - Productos");

            model.addAttribute("subtitulo", "Corrija los Errores");

            model.addAttribute("danger", "¡Datos erróneos!");

            model.addAttribute("subcategorias", subcategoriaService.buscarTodos());

            return "productos/form";
        }

        if (producto.getId() == null) {
            redirect.addFlashAttribute("success", "¡Producto añadido con éxito!");
        } else {
            redirect.addFlashAttribute("warning", "¡Producto modificado con éxito!");
        }

        /* SIN UTILIZAR POR EL MOMENTO (NO FUNCIONA!!)  
       if (producto.getId() == null) {
            redirect.addFlashAttribute("success", "¡Producto añadido con éxito!");
            // PERMITE CARGAR Y GUARDAR LA IMAGENES
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {
            if (file.isEmpty()) { // EDITAMOS EL PRODUCTO SIN CAMBIAR LA IMAGEN.
                Producto p = new Producto();
                p = productoService.get(producto.getId()).get();
                producto.setImagen(p.getImagen());
                redirect.addFlashAttribute("warning", "¡Producto modificado con éxito!");
            } else {
                // PERMITE CARGAR Y GUARDAR LA IMAGENES
                String nombreImagen = upload.saveImage(file);
                producto.setImagen(nombreImagen);
                redirect.addFlashAttribute("warning", "¡Producto modificado con éxito!");
            }

        }
        */

        LOGGER.info("Este es el objeto producto {}", producto);
        producto.setUsuario(producto.getUsuario()); // No funca
        productoService.guardar(producto);
        status.isComplete();
        redirect.addFlashAttribute("success", " Articulo Guardado con Éxitos...");

        return "redirect:/productos/listado";
    }


    @GetMapping("/activo/{id}")
    public String activo(@PathVariable("id") Long id) {

        Producto p = productoService.buscarPorId(id);
        p.setActivo(!p.isActivo());
        productoService.guardar(p);

        return "redirect:/productos/listado";
    }

}
