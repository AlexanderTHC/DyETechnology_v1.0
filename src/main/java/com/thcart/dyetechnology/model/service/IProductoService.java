package com.thcart.dyetechnology.model.service;

import java.util.List;
import java.util.Optional;

import com.thcart.dyetechnology.model.entities.Producto;

public interface IProductoService {
    public List<Producto> buscarTodos();
    
    public List<Producto> buscarPor(String criterio);

    // Nos da la opcion de validar si el objeto que obtenemos si existe o no, dentro de la base de datos
    public Optional<Producto> get(Long id);
    // 

    public List<Producto> buscarPorSubCategoria(Long id);
    
    public Producto buscarPorId(Long id);
    
    public void guardar(Producto producto);

    public void actualizar(Producto producto);
    
    public void borrarPorId(Long id);

    // CAMBIAR ESTADO ACTIVO -> INACTIVO
    public void activo(long id);
}
