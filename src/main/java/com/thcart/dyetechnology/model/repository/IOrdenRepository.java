package com.thcart.dyetechnology.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Long> {
    
    // @Query("select o from Categoria cg where cg.nombre like %:criterio% and cg.activo = true") // == CONSULTA
    // List<Orden> buscarPorCriterio(@Param("criterio") String criterio);
    
    // BUSCAR POR USUARIO LAS ORDENES:
    @Query("select ord from Orden ord where ord.usuario like %:criterio%") // == CONSULTA
    public List<Orden> buscarPorUsuarioOrden(@Param("criterio") String criterio);

    public Orden activo(long id);

}
