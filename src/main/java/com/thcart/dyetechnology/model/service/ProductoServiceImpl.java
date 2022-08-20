package com.thcart.dyetechnology.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Producto;
import com.thcart.dyetechnology.model.repository.IProductoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    IProductoRepository productoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarTodos() {
      return productoRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPor(String criterio) {
        return productoRepo.buscarPorCriterio(criterio);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
       productoRepo.save(producto);      
    }

    @Override
    @Transactional
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
