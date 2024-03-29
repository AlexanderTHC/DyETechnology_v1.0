/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.controller;

import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;
import com.thcart.dyetechnology.model.service.IUsuarioService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Micholini
 */
@Controller
@SessionAttributes({"usuario"})
public class UsuarioController {
        
    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IUsuarioRepository usuarioRepository;
    
    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal, HttpSession session)
    {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        
        model.addAttribute("usuario", usuario);
        return"usuario/perfil";
    }
    
    @PostMapping("/guardar")
    public String guardarCambios(@Valid Usuario usuario, BindingResult result, Model model) {
        
        if(result.hasErrors()){
            return "usuario/form";
        }

        usuarioService.guardar(usuario);
        return "redirect:/perfil";
    }
    
    @GetMapping("/editar/{id}")
    public String editarPerfil(@PathVariable("id") long id, Model model){
        
        Usuario usuario = (Usuario) usuarioService.buscarPorId(id);
            
        model.addAttribute("usuario", usuario);    
        return "usuario/form";
    }

}
