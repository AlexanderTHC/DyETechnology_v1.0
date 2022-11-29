package com.thcart.dyetechnology.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.DetalleOrden;
import com.thcart.dyetechnology.model.entities.Usuario;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {
    
    //CONSULTA Y TRAER LOS DATOS DEL DETALLE ORDEN DEL USUARIO.
    @Query("select do from DetalleOrden do where do.usuario like %:usuario% and do.activo = true") // == CONSULTA
    List<DetalleOrden> buscarPor(@Param("usuario") Usuario usuario);
    
    public DetalleOrden activo(long id);

    @Query("select case when count(us)> 0 then true else false end from DetalleOrden us where lower(us.usuario) like lower(:nomUsuario)")
    public boolean existeUsuario(@Param("nomUsuario") Usuario nomUsuario);

}
