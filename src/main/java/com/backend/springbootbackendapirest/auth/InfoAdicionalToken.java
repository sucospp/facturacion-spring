package com.backend.springbootbackendapirest.auth;

import java.util.HashMap;
import java.util.Map;

import com.backend.springbootbackendapirest.entity.Usuario;
import com.backend.springbootbackendapirest.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

//permite agregar mas informacion al token de autenticacion
@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Usuario usuario = iUsuarioService.findByUsername(authentication.getName());
        Map<String, Object> info = new HashMap<>();

        info.put("nombre", usuario.getNombre());
        info.put("apellido", usuario.getApellido());
        info.put("email", usuario.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
