package com.example;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class Seguridad extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Páginas públicacs:
    	
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/productos").permitAll();
        http.authorizeRequests().antMatchers("/productos/{id}").permitAll();
        http.authorizeRequests().antMatchers("/busqueda").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/signup.html").permitAll();

        // Páginas privadas(el resto)
        http.authorizeRequests().anyRequest().authenticated();

        // Login
        http.formLogin().defaultSuccessUrl("/productos");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/productos");

        // Inhabilitar CSRF
        http.csrf().disable();
    }
} 