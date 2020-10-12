package com.backend.springbootbackendapirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.backend.springbootbackendapirest.entity.Cliente;
import com.backend.springbootbackendapirest.entity.Region;
import com.backend.springbootbackendapirest.services.IClienteService;
import com.backend.springbootbackendapirest.services.IUploadFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteServ;

    @Autowired
    private IUploadFileService uploadService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteServ.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return clienteServ.findAll(pageable);
    }


//APLICO LAS ANOTACIONES DE SEGURIDAD
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.OK) // opcional en lecturas
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        // Utilizo la clase Map para enviar objetos dinamicos en los webervices

        try {
            cliente = clienteServ.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        // valido que el cliente exista
        if (cliente == null) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/clientes")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        // manejo erros con las validaciones de las anotaciones @ de la clase Cliente de
        // la entity
        if (result.hasErrors()) {

            List<String> errors = new ArrayList<>();
            /*
             * 
             * 
             * for (FieldError error : result.getFieldErrors()) { errors.add("El campo ''" +
             * error.getField() + "''  " + error.getDefaultMessage());
             * 
             * }
             */

            // equivale al for de arriba
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "'  " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        // Utilizo la clase Map para enviar objetos dinamicos en los webervices
        try {
            clienteNew = clienteServ.save(cliente);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "El cliente a sido ingresado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }


        @Secured("ROLE_ADMIN")
    @PutMapping("/clientes/{id}")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = new ArrayList<>();

            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "'  " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        Cliente clienteActual = clienteServ.findById(id);
        Cliente clienteActualizado = null;

        // valido que el cliente exista
        if (clienteActual == null) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        // Utilizo la clase Map para enviar objetos dinamicos en los webervices

        try {

            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setRegion(cliente.getRegion());

            clienteActualizado = clienteServ.save(clienteActual);


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el update en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El cliente a sido modificado con exito");
        response.put("cliente", clienteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Cliente cliente = clienteServ.findById(id);
            String nombreFotoAnterior = cliente.getFoto();

            // elimino la foto
            uploadService.eliminar(nombreFotoAnterior);

            clienteServ.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Eliminar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente a sido Eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    // metodo para subir archivos al servidor
//APLICO LAS ANOTACIONES DE SEGURIDAD
        @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        // busco el cliente al que quiero agregar la foto
        Cliente cliente = clienteServ.findById(id);

        if (!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                // copio el archivo del cliente al servidor
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen ");
                response.put("error", e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }

            // si esque el cliente ya tiene una foto subida la reemplazo por la nueva
            String nombreFotoAnterior = cliente.getFoto();

            // elimino la foto
            uploadService.eliminar(nombreFotoAnterior);

            // actualizo el campo foto de la base de datos
            cliente.setFoto(nombreArchivo);
            clienteServ.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamente la imagen : " + nombreArchivo);

        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // metodo para ver las fotos subidas en el servidor, estas se descargan por
    // medio del avegador
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Resource recurso = null;

        // cargo la foto

        try {
            recurso = uploadService.cargar(nombreFoto);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        // creo la cabecera que me permite retornar la informacion del servicio
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        // retorno el archivo deseado
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/clientes/regiones")
    public List<Region> listarRegiones() {
        return clienteServ.findAllRegiones();
    }

}
