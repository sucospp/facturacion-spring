package com.backend.springbootbackendapirest.services;

import java.util.List;

import com.backend.springbootbackendapirest.entity.Cliente;
import com.backend.springbootbackendapirest.entity.Factura;
import com.backend.springbootbackendapirest.entity.Producto;
import com.backend.springbootbackendapirest.entity.Region;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {
    
    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente save(Cliente cliente);

    public void delete(Long id);

    public Cliente findById(Long id);

    public List<Region> findAllRegiones ();

    public Factura findFacturaById(Long id);

    public Factura saveFactura(Factura factura);

    public void deleteFacturaById(Long id);

    public List<Producto> findProductoByNombre(String term);
}
