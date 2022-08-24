package com.thcart.dyetechnology.controller;

import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.service.IUsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registrarse")
//@SessionAttributes({"registro"})
public class RegisterController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/nuevo")
    public String nuevoRegistro(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "registrarse/formRegister";
    }

    @PostMapping("/formRegister")
    public String guardarRegistro(@Valid Usuario usuario, BindingResult result,
            Model model, RedirectAttributes redirect) {

        usuarioService.guardar(usuario);
        return "/home";
    }

}
