/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.thcart.dyetechnology.model.repository;


import com.thcart.dyetechnology.model.entities.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Micholini
 */
public interface IRolRepository extends JpaRepository<Rol, Long> {

    @Query("select rl from Rol rl where rl.nombre like %:criterio% and rl.activo = true") // == CONSULTA
    List<Rol> buscarPorCriterio(@Param("criterio") String criterio);
    
    public Rol activo(long id);
}
