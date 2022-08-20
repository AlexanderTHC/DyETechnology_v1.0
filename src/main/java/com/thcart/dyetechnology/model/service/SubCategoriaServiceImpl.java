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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Micholini
 */
@Service
public class SubCategoriaServiceImpl implements ISubCategoriaService {

    
    @Autowired
    ISubCategoriaRepository subcategoriaRepo;
    
    @Override
    @Transactional(readOnly = true)
    public List<SubCategoria> buscarTodos() {
        return subcategoriaRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategoria> buscarPor(String criterio) {
        return subcategoriaRepo.buscarPorCriterio(criterio);
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoria buscarPorId(Long id) {
        return subcategoriaRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(SubCategoria subcategoria) {
        subcategoriaRepo.save(subcategoria);
    }

    @Override
    @Transactional
    public void borrarPorId(Long id) {
        subcategoriaRepo.deleteById(id);
    }

    @Override
    public void activo(long id) {
        subcategoriaRepo.activo(id);
    }


}
