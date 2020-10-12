package com.backend.springbootbackendapirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//anotacion para implementar seguridad
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // sobreescribo el metodo configure del metodo al que extiendo
    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    @Bean("authenticationManager")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // permite acceder a esta pagina a todos los usuarios por peticiones GET
        http.authorizeRequests().anyRequest().authenticated().and()
                // desabilito la proteccion al ataque proteccion de solicitud de sitio cruzado
                // porque no se necesita en este caso
                .csrf().disable()
                // desabilito el manejo de sesiones por el lado de spring debido que nos vamos a
                // manejar por tokens
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}
