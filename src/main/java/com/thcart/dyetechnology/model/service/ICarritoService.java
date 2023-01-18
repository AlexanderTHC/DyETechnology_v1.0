package com.thcart.dyetechnology.model.service;

import java.util.List;
import java.util.Optional;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.entities.Usuario;


public interface ICarritoService
{
    public List<Carrito> buscarTodos();
    
    public Optional<Carrito> buscarPorId(Integer id);
    
    public void guardar(Carrito carrito);
    
    public void borrarPorId(Integer id);  
}
