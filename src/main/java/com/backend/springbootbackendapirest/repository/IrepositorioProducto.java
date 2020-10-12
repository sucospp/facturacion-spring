package com.backend.springbootbackendapirest.repository;

import java.util.List;

import com.backend.springbootbackendapirest.entity.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IrepositorioProducto extends CrudRepository<Producto,Long>{

    @Query("select p from Producto p where p.nombre like %?1%")
    public List<Producto> findByNombre(String term);


    public List<Producto> findByNombreContainingIgnoreCase(String term); //utiliza el metodo containing de springboot de palabras clave

    public List<Producto> findByNombreStartingWithIgnoreCase(String term); //utiliza el metodo containing de springboot de palabras clave


    
}
