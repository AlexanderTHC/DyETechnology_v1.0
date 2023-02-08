package com.thcart.dyetechnology.model.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.IOrdenRepository;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;


@Service
public class OrdenServiceImpl implements IOrdenService {

    @Autowired
    IOrdenRepository ordenRepo;

    @Autowired
    IUsuarioRepository usuarioRepo;

    @Override
    public List<Orden> buscarTodos() {
        return ordenRepo.findAll();
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

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPor(String emailUsuario) {
        return usuarioRepo.findByEmail(emailUsuario);
    }

    @Override
    public List<Orden> buscarOrdenUsuario(Usuario usuario) {
        return ordenRepo.findByUsuario(usuario);
    }
    

    
}
