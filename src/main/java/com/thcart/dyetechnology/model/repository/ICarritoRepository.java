package com.thcart.dyetechnology.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Carrito;


@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer> 
{
}
