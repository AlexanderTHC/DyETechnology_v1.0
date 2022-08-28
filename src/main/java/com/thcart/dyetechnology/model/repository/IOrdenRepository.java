package com.thcart.dyetechnology.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Long> {
    
    // @Query("select o from Categoria cg where cg.nombre like %:criterio% and cg.activo = true") // == CONSULTA
    // List<Orden> buscarPorCriterio(@Param("criterio") String criterio);
    
    public Orden activo(long id);

}
