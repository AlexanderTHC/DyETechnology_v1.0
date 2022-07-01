/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.thcart.dyetechnology.model.repository;


import com.thcart.dyetechnology.model.entities.SubCategoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Micholini
 */
public interface ISubCategoriaRepository extends JpaRepository<SubCategoria, Long> {
    @Query("select cg from SubCategoria cg where cg.nombre like %:criterio% and cg.activo = true") // == CONSULTA
    List<SubCategoria> buscarPorCriterio(@Param("criterio") String criterio);
    
    public SubCategoria activo(long id);
}
