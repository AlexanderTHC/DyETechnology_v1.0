package com.thcart.dyetechnology.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Rol;
import com.thcart.dyetechnology.model.repository.IRolRepository;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    IRolRepository rolRepo;

    @Override
    public List<Rol> buscarTodos() {
        return rolRepo.findAll();
    }

    @Override
    public List<Rol> buscarPor(String criterio) {
        return rolRepo.buscarPorCriterio(criterio);
    }

    @Override
    public Rol buscarPorId(Long id) {
        return rolRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Rol rol) {
        rolRepo.save(rol);
    }

    @Override
    public void borrarPorId(Long id) {
        rolRepo.deleteById(id);        
    }

    @Override
    public void activo(long id) {
      rolRepo.activo(id);
        
    }
    
}
