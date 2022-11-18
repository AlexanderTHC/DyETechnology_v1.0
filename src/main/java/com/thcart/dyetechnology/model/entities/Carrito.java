package com.thcart.dyetechnology.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "carritos")
public class Carrito 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Producto producto;
    
    private Integer cantidad;

    @ManyToOne
    private Usuario usuario;

    
    public Carrito() 
    {
        this.cantidad = 1;
    }

    public Carrito(Integer id, Producto producto, Integer cantidad, Usuario usuario) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + ", usuario=" + usuario + "]";
    }
}
