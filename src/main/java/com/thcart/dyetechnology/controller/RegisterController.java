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
//@SessionAttributes({"registro"})
public class RegisterController {

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IRolService rolService;

    @Autowired
    private PasswordEncoder passEncode;

    @GetMapping("/nuevo")
    public String nuevoRegistro(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "registrarse/formRegister";
    }

    @PostMapping("/formRegister")
    public String guardarRegistro(@Valid Usuario usuario, BindingResult result,
            Model model, RedirectAttributes redirect) {

                usuario.setClave(passEncode.encode(usuario.getClave()));
                usuario.setRol(rolService.buscarPorId((long) 3));
                usuarioService.guardar(usuario);
                return "redirect:/login";
    }

}
