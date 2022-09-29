package com.thcart.dyetechnology.model.service;

import java.util.List;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Usuario;

public interface IOrdenService { 
    
    public List<Orden> buscarTodos();
    
    public List<Orden> buscarPorUsuarioOrden(String criterio);
    
    public Orden buscarPorId(Long id);
    
    public void guardar(Orden orden);
    
    public void borrarPorId(Long id);

    public void activo(long id);

    public String generarNumeroOrden();

    //OBTENER USUARIO POR
    public Usuario obtenerUsuarioPor(String emailUsuario);


}
