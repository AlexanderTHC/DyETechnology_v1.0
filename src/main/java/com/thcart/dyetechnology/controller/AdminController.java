package com.thcart.dyetechnology.controller;

import java.net.http.HttpClient.Redirect;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.thcart.dyetechnology.model.entities.Orden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("admin/visualizar/{id}")
    public String cambiarEstado(@PathVariable("id") Long id, Model model, RedirectAttributes redirect,
    @RequestParam(name = "estado", required = true) int estado) {

        Orden orden = ordenService.buscarPorId(id);
        switch (estado) {
            case 1:
                orden.setEstado("En espera");
                break;
            case 2:
                orden.setEstado("Aceptado");
                break;
            case 3:
                orden.setEstado("Cancelado"); 
                orden.setActivo(false);
                break;
            default:
                break;
        }
        orden.setFechaRecibida(new Date());
        ordenService.guardar(orden)  ;
        
        redirect.addFlashAttribute("warning", "se modificó con éxito.");
    
        return "redirect:/admin/ordenes";
    }
}
