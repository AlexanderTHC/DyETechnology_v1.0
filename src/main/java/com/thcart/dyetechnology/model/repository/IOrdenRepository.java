package com.thcart.dyetechnology.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thcart.dyetechnology.model.entities.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Long> {
    
}
