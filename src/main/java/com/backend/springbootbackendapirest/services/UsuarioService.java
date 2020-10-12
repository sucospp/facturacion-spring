package com.backend.springbootbackendapirest.services;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.springbootbackendapirest.entity.Usuario;
import com.backend.springbootbackendapirest.repository.IrepositorioUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//en este caso no necesita una interface propia debido a que implementa la interface userdetailsservice de Spring
@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {


    private Logger log = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private IrepositorioUsuario  usuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepo.findByUsername(username);

        if(usuario==null){
            log.error("no existe el usuario en el sistema "+ username );
            throw new UsernameNotFoundException("no existe el usuario en el sistema "+ username);
        }

        //convierto la lilsta de roles al tipo de dato List<GrantedAuthority>
        List<GrantedAuthority> authorities= usuario.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getNombre()))
        //utilizo el peek para mostrar los authorities que se estan convirtiendo
        .peek(authority -> log.info("Role: "+ authority.getAuthority()))
        .collect(Collectors.toList());
        
        return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
    }

    @Override
        @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioRepo.findByUsername(username);
    }
    
}
