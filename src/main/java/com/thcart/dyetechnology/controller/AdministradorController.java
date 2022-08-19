/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.service.IProductoService;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private IProductoService productoService;

    @GetMapping({"/home"})
    public String home(Model model){

        List<Producto> productos = productoService.buscarTodos();

        model.addAttribute("productos", productos);

        model.addAttribute("titulo", "DyE Technology - Administrador");
        model.addAttribute("subtitle", "Tienda DyE Technology Oficial");
        
        return "/administrador/home";
    }

}
