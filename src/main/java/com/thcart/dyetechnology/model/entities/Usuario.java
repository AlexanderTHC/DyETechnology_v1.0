package com.thcart.dyetechnology.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Size(max = 30)
    private String nombre;
    
    @NotEmpty
    @Size(max = 30)
    private String apellido;
    
    @NotEmpty
    @Size(max = 30)
    private String username;
    
    @NotEmpty
    private String email;
    
    private String direccion;
    
    private String dni;
    
    private String telefono;
   
    @NotEmpty
    @Size(max = 110, min = 1, message = "La contraseña debe tener al menos 6 caracteres")
    private String clave;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", referencedColumnName = "id") //CLAVE FORANEA
    private Rol rol;
    
    private boolean activo;

    // 
    @OneToMany(mappedBy = "usuario")
    private List<Producto> producto;
    
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
    // 

    // Carrito del usuario
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Carrito> carrito = new ArrayList<>();

    
    public Usuario() {
        activo = true;
    }

    public Usuario(Long id, @NotEmpty @Size(max = 30) String nombre, @NotEmpty @Size(max = 30) String apellido,
            @NotEmpty @Size(max = 30) String username, @NotEmpty String email, String direccion, String dni,
            String telefono,
            @NotEmpty @Size(max = 110, min = 1, message = "La contraseña debe tener al menos 6 caracteres") String clave,
            Rol rol, boolean activo, List<Producto> producto, List<Orden> ordenes, List<Carrito> carrito) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.dni = dni;
        this.telefono = telefono;
        this.clave = clave;
        this.rol = rol;
        this.activo = activo;
        this.producto = producto;
        this.ordenes = ordenes;
        this.carrito = carrito;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public List<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Carrito> carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", username=" + username
                + ", email=" + email + ", direccion=" + direccion + ", dni=" + dni + ", telefono=" + telefono
                + ", clave=" + clave + ", rol=" + rol + ", activo=" + activo + ", producto=" + producto + ", ordenes="
                + ordenes + ", carrito=" + carrito + "]";
    }
}
