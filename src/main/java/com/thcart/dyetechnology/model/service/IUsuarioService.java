package com.thcart.dyetechnology.model.service;

import java.util.List;

import com.thcart.dyetechnology.model.entities.Usuario;

public interface IUsuarioService { 
    
    public List<Usuario> buscarTodos();
    
    public List<Usuario> buscarPor(String criterio);
    
    public Usuario buscarPorId(Long id);
    
    public void guardar(Usuario usuario);
    
    public void borrarPorId(Long id);

    public void activo(long id);

}
