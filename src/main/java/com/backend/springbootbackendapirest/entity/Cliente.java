package com.backend.springbootbackendapirest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    // me asigna la fecha actual al campo habilitar cuando se necesite
    /*
     * @PrePersist public void prePersist() { createAt = new Date(); }
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede estar vacio")
    @Size(min = 4, max = 15, message = "el tamanio tiene que estar entre 4 y 15")
    @Column(length = 200, nullable = false)
    private String nombre;

    @NotEmpty(message = "no puede estar vacio")
    @Size(min = 4, max = 15, message = "el tamanio tiene que estar entre 4 y 15")
    @Column(length = 200)
    private String apellido;

    @NotEmpty(message = "no puede estar vacio")
    @Email(message = "El Formato del email debe ser correcto")
    // @Column(length = 40, nullable = false, unique = true) activar a que el campo
    // email sea unico
    @Column(length = 40, nullable = false, unique = false)
    private String email;

    @NotNull(message = "No puede ser nulo")
    @Column(name = "createat")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // omito los datos extras innecesarios
                                                                     // proporcionados por lazy e hibernate
    @NotNull(message = "no puede ser nulo")
    private Region region;

    // un cliente tiene muchas facturas, mapeo al objeto cliente de la clase factura
    // y aplico cascada es decir si elimino un cliente elimino todas sus facturas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value={ "cliente", "hibernateLazyInitializer", "handler" },allowSetters = true) // omito los datos extras innecesarios // en este caso de clientes
    private List<Factura> facturas;

    // genero un constructor de cliente para poder inicializar la factura
    public Cliente() {
        this.facturas = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Factura> getFacturas() {
        return this.facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

}
