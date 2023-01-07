package com.thcart.dyetechnology.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.repository.ICarritoRepository;


@Service
public class CarritoServiceImpl implements ICarritoService
{
    @Autowired
    private ICarritoRepository carritoRepository;

    @Override
    public List<Carrito> buscarTodos()
    {
        return carritoRepository.findAll(); 
    }
    
    @Override
    public Optional<Carrito> buscarPorId(Integer id)
    {
        return carritoRepository.findById(id);
    }
    
    @Override
    public void guardar(Carrito carrito)
    {
        carritoRepository.save(carrito);
    }
    
    @Override
    public void borrarPorId(Integer id)
    {
        carritoRepository.deleteById(id);
    }
}
