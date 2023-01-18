package com.thcart.dyetechnology.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Orden;
import com.thcart.dyetechnology.model.entities.Usuario;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Long> {
    
    // @Query("select o from Categoria cg where cg.nombre like %:criterio% and cg.activo = true") // == CONSULTA
    // List<Orden> buscarPorCriterio(@Param("criterio") String criterio);
    
    // BUSCAR POR USUARIO LAS ORDENES:
    public List<Orden> findByUsuario(Usuario usuario);

    public Orden activo(long id);

}
