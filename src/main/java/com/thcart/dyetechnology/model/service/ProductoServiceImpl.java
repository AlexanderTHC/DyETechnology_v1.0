package com.thcart.dyetechnology.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    IProductoRepository productoRepo;

    @Override
    public List<Producto> buscarTodos() {
      return productoRepo.findAll();
    }

    @Override
    public List<Producto> buscarPor(String criterio) {
        return productoRepo.buscarPorCriterio(criterio);
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Producto producto) {
       productoRepo.save(producto);      
    }

    @Override
    public void borrarPorId(Long id) {
       productoRepo.deleteById(id);
        
    }

    @Override
    public Optional<Producto> get(Long id) {
        return productoRepo.findById(id);
    }

    @Override
    public void activo(long id) {
    productoRepo.activo(id);    
    }
    
}
