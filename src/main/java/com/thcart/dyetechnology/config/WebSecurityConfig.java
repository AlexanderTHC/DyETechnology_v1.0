/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thcart.dyetechnology.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author joaqu
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {

    //PERMITE INGRESAR Y VISUALIZAR LAS IMAGENES QUE SE ENCUENTRAN DENTRO DE ESE DIRECTORIO EN EL HOME
/*     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:img/");
    } */
    //INYECTAR EL ORIGEN DE DATOS LA CONEXIÓN A LA DB
    @Autowired
    DataSource dataSource;

    @Bean //CADA OBJETO DE JAVA EN UN BEAN, ES PARA QUE CREE AUTOMATICAMENTE ESE METODO Y CREA LA INSTANCIA DEL INCRIPTADOR
    BCryptPasswordEncoder getEnconder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/layout/**", "/home/**", "/**", "/css/**", "/js/**", "/img/**").hasAuthority("Admin")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/error_403")
                .and().csrf().disable().cors();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/layout/**", "/home/**", "/**", "/css/**", "/js/**", "/img/**");
    }
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {

        //AUTENTICACIÓN CON JDBC:
        build.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select nombre, clave, activo from usuarios where nombre = ?")
                .authoritiesByUsernameQuery("select u.nombre, r.nombre from roles r "
                        + "inner join usuarios u on "
                        + "u.id_rol = r.id where u.nombre = ?");
    }

}
