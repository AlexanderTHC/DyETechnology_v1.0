package com.thcart.dyetechnology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Bean
    public BCryptPasswordEncoder passEncoder(){

        return new BCryptPasswordEncoder();
    }
    }
