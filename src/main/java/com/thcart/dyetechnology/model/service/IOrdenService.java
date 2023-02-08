package com.thcart.dyetechnology.model.service;

import java.util.List;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Usuario;

public interface IOrdenService { 
    
    public List<Orden> buscarTodos();
    
    public Orden buscarPorId(Long id);
    
    public void guardar(Orden orden);
    
    public void borrarPorId(Long id);

    public void activo(long id);

    //OBTENER USUARIO POR
    public Usuario obtenerUsuarioPor(String emailUsuario);

    //BUSCAR ORDEN DEL USUARIO 
    public List<Orden> buscarOrdenUsuario(Usuario usuario);


}
