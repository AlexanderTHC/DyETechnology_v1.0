package com.thcart.dyetechnology.model.service;

import java.util.List;

import com.thcart.dyetechnology.model.entities.Rol;

public interface IRolService { 
    
    public List<Rol> buscarTodos();
    
    public List<Rol> buscarPor(String criterio);
    
    public Rol buscarPorId(Long id);
    
    public void guardar(Rol rol);
    
    public void borrarPorId(Long id);

    public void activo(long id);
}
