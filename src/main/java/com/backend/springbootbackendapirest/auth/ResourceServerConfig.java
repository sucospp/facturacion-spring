package com.backend.springbootbackendapirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // SEGURIDAD POR MEDIO DE CODIGO SIN ANOTACIONES
        // permite acceder a esta pagina a todos los usuarios por peticiones GET

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**",
                        "/images/**").permitAll()
                
       


                /*
                 * SE CAMBIA A LA SEGURIDAD POR ANOTACIONES DE SPRING //la ruta de detalle
                 * escrita necesita autenticacion de usuario USER O ADMIN
                 * .antMatchers(HttpMethod.GET,"/api/clientes/{id}").hasAnyRole("USER","ADMIN")
                 * //la ruta tiene permisos de USER Y ADMIN
                 * .antMatchers(HttpMethod.POST,"/api/clientes/upload").hasAnyRole("USER",
                 * "ADMIN") //el post de la ruta escrita necesita permisos de administrador
                 * .antMatchers(HttpMethod.POST,"/api/clientes").hasRole("ADMIN") //todas las
                 * rutas luego de la ruta escrita necesitan permisos de administrador
                 * .antMatchers("/api/clientes/**").hasRole("ADMIN")
                 */
                // el resto de rutas no especificados son accedidas por todos los usuarios que
                // se autentiquen
                .anyRequest().authenticated()

                // agrego la configuracion del cors
                .and().cors().configurationSource(corsConfigurationSource());

    }

    // configuro el cors para manejar las solicitudes de los clientes externos

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuro los dominios puede ser uno o varios
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","*"));
        // configuto los metodos que van a utilizarse en el backend el options se
        // utiliza por necesidad de algunos navegadores
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // habilito el ingreso de credenciales
        configuration.setAllowCredentials(true);
        // designo las cabeceras permitidas para ingresar al servidor
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        // suscribimos las configuraciones a la url del proyecto spring
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // creo un filtro de corse se le asigna el nivel mas alto con la configuracion
    // realizada en los metodos anteriors
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
                new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
