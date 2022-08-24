package com.thcart.dyetechnology.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thcart.dyetechnology.model.entities.Usuario;
import com.thcart.dyetechnology.model.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    IUsuarioRepository usuarioRepo;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Usuario> buscarPor(String criterio) {
        return usuarioRepo.buscarPorCriterio(criterio);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepo.save(usuario);        
    }

    @Override
    public void borrarPorId(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public void activo(long id) {
        usuarioRepo.activo(id);
    }

    
}
