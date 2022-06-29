/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @GetMapping({"/home"})
    public String home(Model model){

        model.addAttribute("titulo", "DyE Technology - Administrador");
        model.addAttribute("subtitle", "Tienda DyE Technology Oficial");
        
        return "/administrador/home";
    }

}
