package com.backend.springbootbackendapirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizarionServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    //inyecto la informacion adicional para adjuntar al token
    @Autowired
    private InfoAdicionalToken infoAdicionalToken;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // agrego la informacion al token junto con la adicional
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));

        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // asignamos la llave secreta obtenida del JWTConfig en caso de trabajar con
        // llaves secretas
        // jwtAccessTokenConverter.setSigningKey(JWTConfig.LLAVA_SECRETA);
        // asigna la llave RSA privada para firmar
        jwtAccessTokenConverter.setSigningKey(JWTConfig.RSA_PRIVADA);
        // verifica con la llave publica
        jwtAccessTokenConverter.setVerifierKey(JWTConfig.RSA_PUBLICA);
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient("angularapp")
                // credenciales de la aplicacion
                .secret(passwordEncoder.encode("1234"))
                // permisos
                .scopes("read", "write")
                // tipo de login usuario password
                .authorizedGrantTypes("password", "regresh_token")
                // una hora de token valido
                .accessTokenValiditySeconds(3600)
                // refresca el token luego de una hora
                .refreshTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        // da permisos a cualquir usuario de autenticarse en el endpoint oauth y genera
        // el token
        security.tokenKeyAccess("permitAll()")
                // valida el token
                .checkTokenAccess("isAuthenticated()");
    }

}
