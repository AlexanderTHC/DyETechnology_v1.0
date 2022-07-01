/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.model.service;


import com.thcart.dyetechnology.model.entities.SubCategoria;
import com.thcart.dyetechnology.model.repository.ISubCategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Micholini
 */
@Service
public class SubCategoriaServiceImpl implements ISubCategoriaService {

    
    @Autowired
    ISubCategoriaRepository subcategoriaRepo;
    
    @Override
    public List<SubCategoria> buscarTodos() {
        return subcategoriaRepo.findAll();
    }

    @Override
    public List<SubCategoria> buscarPor(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubCategoria buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar(SubCategoria subcategoria) {
        subcategoriaRepo.save(subcategoria);
    }

    @Override
    public void borrarPorId(Long id) {
        subcategoriaRepo.deleteById(id);
    }

    @Override
    public void activo(long id) {
        subcategoriaRepo.activo(id);
    }


}
