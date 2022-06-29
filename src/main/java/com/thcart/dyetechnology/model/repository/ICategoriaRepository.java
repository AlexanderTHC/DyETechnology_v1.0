package com.thcart.dyetechnology.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thcart.dyetechnology.model.entities.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long>{

    @Query("select cg from Categoria cg where cg.nombre like %:criterio% and cg.activo = true") // == CONSULTA
    List<Categoria> buscarPorCriterio(@Param("criterio") String criterio);
    
    public Categoria activo(long id);

}
