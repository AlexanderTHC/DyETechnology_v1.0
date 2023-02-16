package com.thcart.dyetechnology.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{

    @Query("select prd from Producto prd where prd.upc like %:criterio% or prd.nombre like %:criterio% or prd.precio like %:criterio%")
    List<Producto> buscarPorCriterio(@Param("criterio") String criterio);

    @Query("SELECT prd FROM Producto prd WHERE prd.categoria.id = :id")
    List<Producto> buscarPorSubCategoria(@Param("id") Long id);

    // CAMBIAR ESTADO ACTIVO -> INACTIVO
    public Producto activo(long id);
}
