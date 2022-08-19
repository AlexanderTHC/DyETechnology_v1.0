/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.thcart.dyetechnology.model.service.IProductoService;




@Controller
public class HomeController {

    @Autowired
    IProductoService productoService;
    
    @GetMapping({"/", "/home"})
    public String home(Model model){

        model.addAttribute("titulo", "DyE Technology - Inicio");
        model.addAttribute("subtitle", "Tienda DyE Technology Oficial");
        model.addAttribute("productos", productoService.buscarTodos());
        
        return "home";
    }


}
