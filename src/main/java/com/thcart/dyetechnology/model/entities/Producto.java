/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "¡Se requiere del Codigo de Barra!") //MENSAJE DE ERROR Y OBLIGATORIO.
     @Column(length = 13) //NOMBRE EN LA BASE DE DATOS Y LONGITUD DE 13
    private String upc;
    
    @NotBlank(message = "¡El Nombre es requerido!") //MENSAJE DE ERROR Y OBLIGATORIO.
    @Size(max = 65) // => varchar(65)
    private String nombre;
    
    @NotBlank(message = "¡La descripción es requerida!")
    @Size(max = 10000) // => varchar(65)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id") //CLAVE FORANEA
    @JsonIgnore
    private Categoria categoria;
    
    private String imagen;
    
    @NotNull(message = "¡El precio es requerido!") //SOLO PARA VALORES NUMERICOS Y CON SU MENSAJE DE ERROR
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private double precio;
    
    @NotNull(message = "¡El stock es requerido!")
    @Min(value = 0, message = "El Stock minimo es 0")
    private int cantidad;
    
    private boolean activo;

    // 
    @ManyToOne
    private Usuario usuario;
    // 
    
    public Producto() {
        activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", upc=" + upc + ", nombre=" + nombre + ", descripcion=" + descripcion + ", categoria=" + categoria + ", imagen=" + imagen + ", precio=" + precio + ", cantidad=" + cantidad + ", activo=" + activo + ", usuario=" + usuario + '}';
    }
    
}
