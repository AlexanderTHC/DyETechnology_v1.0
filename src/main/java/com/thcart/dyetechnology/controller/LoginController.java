package com.thcart.dyetechnology.controller;

import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    
    @GetMapping("/login")
    public String iniciarSesion(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        Model model, Principal principal, RedirectAttributes attribute){

        model.addAttribute("titulo", "DyE Technology - Login");

        if(error != null){
            model.addAttribute("error", "ERROR");
        }

        if(principal != null){
            attribute.addFlashAttribute("sesionIniciada", "INICIADO");
            return "redirect:/home";
        }

        if(logout != null){
            attribute.addFlashAttribute("sesionFinalizada", "FINALIZADO");
            return "redirect:/";
        }

        return "login";
    }
       
}
