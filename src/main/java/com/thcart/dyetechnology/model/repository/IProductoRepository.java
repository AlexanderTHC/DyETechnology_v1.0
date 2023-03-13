package com.thcart.dyetechnology.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>
{
    @Query("select prd from Producto prd where prd.upc like %:criterio% or prd.nombre like %:criterio%")
    List<Producto> buscarPorCriterio(@Param("criterio") String criterio);

    @Query(
        "SELECT producto FROM Producto producto " +
        "WHERE (producto.nombre LIKE %:query% OR producto.upc LIKE %:query%) " +
        "AND (:minPrice IS NULL OR producto.precio >= :minPrice) " +
        "AND (:maxPrice IS NULL OR producto.precio <= :maxPrice) " +
        "AND (:categoryId IS NULL OR producto.categoria.id = :categoryId) " +
        "ORDER BY " +
        "CASE WHEN :priceOrder='ASC' THEN producto.precio END ASC, " +
        "CASE WHEN :priceOrder='DESC' THEN producto.precio END DESC")
    List<Producto> buscarProductos(
                                    @Param("query") String query,
                                    @Param("categoryId") Long categoryId, 
                                    @Param("minPrice") Double minPrice,
                                    @Param("maxPrice") Double maxPrice,
                                    @Param("priceOrder") String priceOrder);

    @Query("SELECT prd FROM Producto prd WHERE prd.categoria.id = :id")
    List<Producto> buscarPorSubCategoria(@Param("id") Long id);

    // CAMBIAR ESTADO ACTIVO -> INACTIVO
    public Producto activo(long id);
}
