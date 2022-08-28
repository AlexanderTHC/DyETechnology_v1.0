package com.thcart.dyetechnology.model.service;

import com.thcart.dyetechnology.model.entities.Orden;

public interface IOrdenService { 
    
    public List<Orden> buscarTodos();
    
    public List<Orden> buscarPor(String criterio);
    
    public Orden buscarPorId(Long id);
    
    public void guardar(Orden orden);
    
    public void borrarPorId(Long id);

    public void activo(long id);

}
