package com.thcart.dyetechnology.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.service.IRolService;
import com.thcart.dyetechnology.model.service.IUsuarioService;

@Controller
@RequestMapping("/registrarse")
public class RegisterController {

    @Autowired
    IUsuarioService userService;
    @Autowired
    IRolService rolService;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping()
    public String agregarUsuarios(Model model){
        
    Usuario usuario = new Usuario();   
    
    model.addAttribute("subtitulo", "Nuevo Cajero");
    
    model.addAttribute("usuario", usuario);
    
    model.addAttribute("roles", rolService.buscarTodos());
        
    return "registrarse/formRegister";
    }


    @PostMapping("/nuevo")
    public String nuevoUser(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes redirect){

        if(result.hasErrors()){
            
            model.addAttribute("subtitulo", "Corrija los Errores");
          
            model.addAttribute("danger", "¡Datos erróneos!");
            
            return "register";
        }

        usuario.setClave(encoder.encode(usuario.getClave()));
        usuario.setRol(rolService.buscarPorId((long) 3));
       
       userService.guardar(usuario);

        return "redirect:/";
    }
}
