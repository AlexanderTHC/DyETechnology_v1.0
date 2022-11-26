package com.thcart.dyetechnology.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.DetalleOrden;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.IDetalleOrdenRepository;


@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

    @Autowired
    IDetalleOrdenRepository detalleRepo;
    
    @Override
    public List<DetalleOrden> buscarTodos() {
        
        return detalleRepo.findAll();
    }

    @Override
    public List<DetalleOrden> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DetalleOrden buscarPorId(Long id) {
        return detalleRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(DetalleOrden detalleOrden) {
        detalleRepo.save(detalleOrden);
        
    }

    @Override
    public void borrarPorId(Long id) {
        detalleRepo.deleteById(id);
        
    }

    @Override
    public void activo(long id) {
       detalleRepo.activo(id);
        
    }

    @Override
    public boolean existeUsuario(Usuario nomUsuario) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
