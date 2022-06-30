package com.thcart.dyetechnology.model.service;

import java.util.List;

import com.thcart.dyetechnology.model.entities.Categoria;

public interface ICategoriaService { 
    
    public List<Categoria> buscarTodos();
    
    public List<Categoria> buscarPor(String criterio);
    
    public Categoria buscarPorId(Long id);
    
    public void guardar(Categoria Categoria);
    
    public void borrarPorId(Long id);

    public void activo(long id);
}
