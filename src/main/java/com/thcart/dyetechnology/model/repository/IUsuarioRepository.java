/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.thcart.dyetechnology.model.repository;

import com.thcart.dyetechnology.model.entities.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Micholini
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.nombre like %:criterio% and u.activo = true") // == CONSULTA
    List<Usuario> buscarPorCriterio(@Param("criterio") String criterio);
    
    @Query("select u from Usuario u where u.email = :email")
    Usuario buscarPorEmail(@Param("email") String email);
    
    public Usuario activo(long id);
}
