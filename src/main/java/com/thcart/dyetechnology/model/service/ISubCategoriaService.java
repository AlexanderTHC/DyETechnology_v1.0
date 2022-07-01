/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.thcart.dyetechnology.model.service;

import com.thcart.dyetechnology.model.entities.Categoria;
import com.thcart.dyetechnology.model.entities.SubCategoria;
import java.util.List;

/**
 *
 * @author Micholini
 */
public interface ISubCategoriaService { 
    
    public List<SubCategoria> buscarTodos();
    
    public List<SubCategoria> buscarPor(String criterio);
    
    public SubCategoria buscarPorId(Long id);
    
    public void guardar(SubCategoria subcategoria);
    
    public void borrarPorId(Long id);

    public void activo(long id);
    
}
