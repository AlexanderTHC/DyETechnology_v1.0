package com.thcart.dyetechnology.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Carrito;
import com.thcart.dyetechnology.model.entities.Usuario;


@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer> 
{
    List<Carrito> findByUsuario(Usuario usuario); 

    @Query(value = "SELECT * FROM carritos WHERE usuario_id = :usuarioId AND producto_id = :productId", nativeQuery = true)
    Optional<Carrito> isProductInCart(@Param("usuarioId") Long usuarioId, @Param("productId") Long productId);
}
