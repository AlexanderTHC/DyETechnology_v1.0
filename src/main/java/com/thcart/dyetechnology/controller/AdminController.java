package com.thcart.dyetechnology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import com.thcart.dyetechnology.model.entities.Orden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thcart.dyetechnology.model.service.IOrdenService;



@Controller
public class AdminController {
    
    @Autowired
    private IOrdenService ordenService;
    

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
        model.addAttribute("ordenes", ordenService.buscarTodos());
        return "administrador/listOrdenes";
    }

    @GetMapping("admin/visualizar/{id}")
    public String visualizar(@PathVariable("id") Long id, Model model) {
    
        model.addAttribute("titulo", "DyE Technology - Detalle de la Compra");

        Orden orden = ordenService.buscarPorId(id);
        model.addAttribute("ordenes", orden);

        return "administrador/visualizarOrden";
    }
}
