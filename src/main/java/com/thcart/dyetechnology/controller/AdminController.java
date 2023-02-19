package com.thcart.dyetechnology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AdminController {
    

    @GetMapping("admin/inicio")
    public String inicio(Model model) {

        model.addAttribute("titulo", "DyE Technology - Administración");
        model.addAttribute("subtitulo", "Bienvenido a DyE Technology - Administración");

        return "administrador/inicio";
    }
    
    @GetMapping("admin/ordenes")
    public String ordenes(Model model) {

        model.addAttribute("titulo", "DyE Technology - Administración");
        model.addAttribute("subtitulo", "Lista de Ordenes de Clientes");
        return "administrador/listOrdenes";
    }
}
