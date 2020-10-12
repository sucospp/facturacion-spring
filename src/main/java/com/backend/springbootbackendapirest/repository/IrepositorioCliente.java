package com.backend.springbootbackendapirest.repository;

import java.util.List;

import com.backend.springbootbackendapirest.entity.Cliente;
import com.backend.springbootbackendapirest.entity.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositorioCliente extends JpaRepository<Cliente,Long> {
    
    @Query("from Region")
    public List<Region> findAllRegiones ();
}
