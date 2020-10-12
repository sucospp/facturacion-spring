package com.backend.springbootbackendapirest.services;

import com.backend.springbootbackendapirest.entity.Usuario;

public interface IUsuarioService {
    
    public Usuario findByUsername(String username);

    
}
