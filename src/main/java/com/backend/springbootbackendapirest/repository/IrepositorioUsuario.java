package com.backend.springbootbackendapirest.repository;

import com.backend.springbootbackendapirest.entity.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IrepositorioUsuario extends CrudRepository<Usuario, Long> {
    

    //me reliza el select automaticamente por medio del nombre finByUsername
    public Usuario  findByUsername(String username);



    //me realiza el select por medio de un query en donde ?1 es el parametro si se quisiera agregar otro se agrega ?2
    @Query("select u from Usuario u where u.username=?1")
    public Usuario  findByUsernameQuery(String username);

}
