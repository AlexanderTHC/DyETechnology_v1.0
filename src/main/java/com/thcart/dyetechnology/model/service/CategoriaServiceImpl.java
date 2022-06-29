package com.thcart.dyetechnology.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Categoria;
import com.thcart.dyetechnology.model.repository.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    ICategoriaRepository categoriaRepo;

    @Override
    public List<Categoria> buscarTodos() {
      return categoriaRepo.findAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return categoriaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Categoria categoria) {
       categoriaRepo.save(categoria);      
    }

    @Override
    public void borrarPorId(Long id) {
       categoriaRepo.deleteById(id);
        
    }

    @Override
    public List<Categoria> buscarPor(String criterio) {
        return null;
    }

    @Override
    public void activo(long id) {
        categoriaRepo.activo(id);        
    }
    
}
