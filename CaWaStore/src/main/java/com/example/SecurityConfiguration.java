package com.example;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Páginas públicas:
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/productos").permitAll();
        http.authorizeRequests().antMatchers("/productos/{id}").permitAll();
        http.authorizeRequests().antMatchers("/busqueda").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/signup").permitAll();
        http.authorizeRequests().antMatchers("/nuevoUsuario").permitAll();
        http.authorizeRequests().antMatchers("/subir_producto").permitAll();
        // Páginas privadas(el resto)
        http.authorizeRequests().anyRequest().authenticated();
        // Login
        http.formLogin().defaultSuccessUrl("/productos");
        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/productos");
       
    }
} 