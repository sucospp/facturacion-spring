package com.backend.springbootbackendapirest.services;

import java.util.List;

import com.backend.springbootbackendapirest.entity.Cliente;
import com.backend.springbootbackendapirest.entity.Factura;
import com.backend.springbootbackendapirest.entity.Producto;
import com.backend.springbootbackendapirest.entity.Region;
import com.backend.springbootbackendapirest.repository.IrepositorioCliente;
import com.backend.springbootbackendapirest.repository.IrepositorioFactura;
import com.backend.springbootbackendapirest.repository.IrepositorioProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IrepositorioCliente clienteRepo;

	@Autowired
	IrepositorioFactura facturaRepo;

	@Autowired
	IrepositorioProducto productoRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteRepo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteRepo.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {

		return facturaRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {

		return facturaRepo.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaRepo.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductoByNombre(String term) {

		return productoRepo.findByNombreContainingIgnoreCase(term);
	}





	
}