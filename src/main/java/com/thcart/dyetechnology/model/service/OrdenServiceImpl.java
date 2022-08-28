package com.thcart.dyetechnology.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.repository.IOrdenRepository;


@Service
public class OrdenServiceImpl implements IOrdenService {

    @Autowired
    IOrdenRepository ordenRepo;

    @Override
    public List<Orden> buscarTodos() {
        return ordenRepo.findAll();
    }

    @Override
    public List<Orden> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Orden buscarPorId(Long id) {
        return ordenRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Orden orden) {
        ordenRepo.save(orden);
        
    }

    @Override
    public void borrarPorId(Long id) {
        ordenRepo.deleteById(id);
    }

    @Override
    public void activo(long id) {
        ordenRepo.activo(id);
        
    }


    
}
