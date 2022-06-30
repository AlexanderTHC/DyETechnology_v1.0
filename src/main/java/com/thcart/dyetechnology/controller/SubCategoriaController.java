/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import com.thcart.dyetechnology.model.service.ISubCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Micholini
 */

@Controller
@RequestMapping("/subcategorias")
public class SubCategoriaController {
    
    @Autowired
    ISubCategoriaService subcategoriaService;
    
    @GetMapping("/listado")
    public String listadoSubCategorias(Model model) {
        
        model.addAttribute("subcategorias", subcategoriaService.buscarTodos());
        
        return "subcategorias/show";
    }
}
