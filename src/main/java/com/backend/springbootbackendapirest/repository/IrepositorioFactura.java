package com.backend.springbootbackendapirest.repository;

import com.backend.springbootbackendapirest.entity.Factura;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositorioFactura extends CrudRepository<Factura,Long>{


    
}
