package com.thcart.dyetechnology.controller;

import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.service.UsuarioServiceImpl;
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
            Model model, Principal principal, RedirectAttributes attribute) 
    {

        model.addAttribute("titulo", "DyE Technology - Login");

        if (error != null) {
            model.addAttribute("error", "ERROR");
        }

        if (principal != null) {
            attribute.addFlashAttribute("sesionIniciada", "INICIADO");
            return "redirect:/home";
        }

        if (logout != null) {
            attribute.addFlashAttribute("sesionFinalizada", "FINALIZADO");
            return "redirect:/";
        }

        return "login";
    }
    
    @GetMapping("/login_success")
    public String sesionIniciada(HttpSession session, Authentication auth, Principal principal){
        
        auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        session.setAttribute("usuario.id", usuario.getId());
                
        return"redirect:/";
    }
    

       
}
