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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thcart.dyetechnology.model.entities.Categoria;
import com.thcart.dyetechnology.model.service.ICategoriaService;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    // Para visualiar en consola, para un log de todo lo que se ejecuta en el programa::
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    //

    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/listado")
    public String listadoCategorias(Model model){

        model.addAttribute("titulo", "DyE Technology - Categorias");

        model.addAttribute("categorias", categoriaService.buscarTodos());

        return "categorias/show";
    }

    @GetMapping("/nuevo") //NUEVO
    public String nuevaCategoria(Model model){

        model.addAttribute("titulo", "DyE Technology - Categorias");

        model.addAttribute("subtitulo", "Nueva Categoria");
        
        model.addAttribute("categoria", new Categoria());
        
        return "categorias/form";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable("id") long id, Model model){
        
    Categoria categoria = (Categoria) categoriaService.buscarPorId(id);
        
    model.addAttribute("subtitulo", "Editar Categoria");
    
    model.addAttribute("departamento", categoria);
    
    
    return "categorias/form";
    }

    @PostMapping("/nuevo") //AGREGAR
    public String guardarCategoria(@Valid Categoria categoria, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {

        //VERIFICAR ERRORES EN LOS ATRIBUTOS:
        if (result.hasErrors()) {

            model.addAttribute("titulo", "DyE Technology - Categorias");

            model.addAttribute("subtitulo", "Corrija los Errores");

            model.addAttribute("danger", "¡Datos erróneos!");

            return "categorias/form";
        }

      if (categoria.getId() == null) {
            redirect.addFlashAttribute("success", "¡Categoria añadido con éxito!");
        } else {
            redirect.addFlashAttribute("warning", "¡Categoria modificado con éxito!");
        } 

        LOGGER.info("Este es el objeto categoria {}",categoria);
        categoriaService.guardar(categoria);
        status.isComplete();
        redirect.addFlashAttribute("success", " Categoria Guardado con Éxitos...");

        return "redirect:/categorias/listado";
    }  

    @GetMapping("/activo/{id}")
    public String activo(@PathVariable("id") Long id) {

        Categoria dp = categoriaService.buscarPorId(id);
        dp.setActivo(!dp.isActivo());
        categoriaService.guardar(dp);

        return "redirect:/categorias/listado";
    }
}
