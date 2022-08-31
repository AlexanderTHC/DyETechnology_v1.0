/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.service.IUsuarioService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Micholini
 */
@Controller
public class UsuarioController {
        
    @Autowired
    IUsuarioService usuarioService;
    
    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal){
        
        Usuario usuario = usuarioService.buscarPorId((long)1);
        model.addAttribute("usuario", usuario);
//        
        principal.getName();

        return"usuario/perfil";
    }
    
}
