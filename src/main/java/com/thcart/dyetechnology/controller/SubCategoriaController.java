/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import com.thcart.dyetechnology.model.entities.Categoria;
import com.thcart.dyetechnology.model.entities.SubCategoria;
import com.thcart.dyetechnology.model.service.ICategoriaService;
import com.thcart.dyetechnology.model.service.ISubCategoriaService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 *
 * @author Micholini
 */

@Controller
@RequestMapping("/subcategorias")
@SessionAttributes({"subcategoria"})
public class SubCategoriaController {
    
    @Autowired
    ISubCategoriaService subcategoriaService;
    
    @Autowired
    ICategoriaService categoriaService;
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @GetMapping("/listado")
    public String listadoSubCategorias(Model model) {
        
        model.addAttribute("subcategorias", subcategoriaService.buscarTodos());
        
        model.addAttribute("categorias", categoriaService.buscarTodos());
        
        return "subcategorias/show";
    } 
    
    @GetMapping("/nuevo") //NUEVO
    public String nuevaSubCategoria(Model model){

        model.addAttribute("titulo", "DyE Technology - SubCategorias");

        model.addAttribute("subtitulo", "Nueva Sub Categoria");
        
        model.addAttribute("subcategoria", new SubCategoria());
        
        model.addAttribute("categorias", categoriaService.buscarTodos());
        
        model.addAttribute("Subcategorias", subcategoriaService.buscarTodos());
        
        return "subcategorias/form";
    } 
    
    @GetMapping("/editar/{id}")
    public String editarSubCategoria(@PathVariable("id") long id, Model model, Categoria categoria){
        
        SubCategoria subcategoria = (SubCategoria) subcategoriaService.buscarPorId(id);

        model.addAttribute("subcategorias", subcategoriaService.buscarTodos());

        model.addAttribute("categorias", categoriaService.buscarTodos());

        model.addAttribute("subtitulo", "Editar Sub Categoria");

        model.addAttribute("subcategorias", subcategoria);
        
        model.addAttribute("subcategoriaAct", true);
        
        return "subcategorias/form";
    } 
    
    @PostMapping("/nuevo") //AGREGAR
    public String guardarSubCategoria(@Valid SubCategoria subcategoria, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {

        //VERIFICAR ERRORES EN LOS ATRIBUTOS:
        if (result.hasErrors()) {

            model.addAttribute("titulo", "DyE Technology - Sub Categorias");

            model.addAttribute("subtitulo", "Corrija los Errores");

            model.addAttribute("danger", "¡Datos erróneos!");
            
            model.addAttribute("subcategorias", subcategoriaService.buscarTodos());
            
            model.addAttribute("categorias", categoriaService.buscarTodos());

            return "subcategorias/form";
        }

      if (subcategoria.getId() == null) {
            redirect.addFlashAttribute("success", "¡Sub Categoria añadido con éxito!");
        } else {
            redirect.addFlashAttribute("warning", "¡Sub Categoria modificado con éxito!");
        } 

        LOGGER.info("Este es el objeto sub categoria {}",subcategoria);
        subcategoriaService.guardar(subcategoria);
        status.isComplete();
        redirect.addFlashAttribute("success", "Sub Categoria Guardado con Éxitos...");

        return "redirect:/subcategorias/listado";
    }  

    @GetMapping("/activo/{id}")
    public String activo(@PathVariable("id") Long id) {

        SubCategoria dp = subcategoriaService.buscarPorId(id);
        dp.setActivo(!dp.isActivo());
        subcategoriaService.guardar(dp);

        return "redirect:/subcategorias/listado";
    }
    
}
