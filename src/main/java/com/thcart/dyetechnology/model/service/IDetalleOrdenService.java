package com.thcart.dyetechnology.model.service;

import java.util.List;

import com.thcart.dyetechnology.model.entities.DetalleOrden;
import com.thcart.dyetechnology.model.entities.Usuario;

public interface IDetalleOrdenService { 
    
    public List<DetalleOrden> buscarTodos();
    
    public List<DetalleOrden> buscarPor(Usuario usuario);
    
    public DetalleOrden buscarPorId(Long id);
    
    public void guardar(DetalleOrden detalleOrden);
    
    public void borrarPorId(Long id);

    public void activo(long id);

    public boolean existeUsuario(Usuario nomUsuario);

}
