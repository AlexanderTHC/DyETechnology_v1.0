/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    //PERMITE INGRESAR Y VISUALIZAR LAS IMAGENES QUE SE ENCUENTRAN DENTRO DE ESE DIRECTORIO EN EL HOME
/*     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:img/");
    } */
    
    //INYECTAR EL ORIGEN DE DATOS LA CONEXIÓN A LA DB
    @Autowired
    DataSource dataSource;
    
    @Autowired
    private BCryptPasswordEncoder passEncode;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     
        http.authorizeRequests()
                .antMatchers("/layout/**", "/home/**","/buscar/**", "/", "/assets/**", "/css/**", "/js/**", "/img/**", "/registrarse/**").permitAll()
                .antMatchers("/administrador/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").usernameParameter("email")
                .permitAll()
                .and()
                .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/error_403")
                .and().csrf().disable().cors();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
        
        //AUTENTICACIÓN CON JDBC:
        build.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passEncode)
                .usersByUsernameQuery("select email, clave, activo from usuarios where email = ?")
                .authoritiesByUsernameQuery("select u.email, r.nombre from roles r "
                + "inner join usuarios u on "
                + "u.id_rol = r.id where u.email = ?");
    } 
   
}