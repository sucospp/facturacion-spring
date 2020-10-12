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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Factura
 */
@Entity
@Table(name="facturas")
public class Factura implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String observacion;

    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    //UN CLIENTE PUEDE TENER MUCHAS FACTURAS,   lazy me carga por partes y eager todo a la vez
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value={ "facturas" ,"hibernateLazyInitializer", "handler"},allowSetters=true) 
    private Cliente cliente;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // omito los datos extras innecesarios
    @JoinColumn(name="factura_id")   //agrego el join column debido a que no es una relacion doble sentido y no me geera automaticamente la llava foranea, el nombre factura_id se genera automaticamente en la clase factura
    private List<ItemFactura> items;



    //creo el constructor para inicializar los items
    public Factura(){
        this.items =new ArrayList<>();
    }


    //me obtiene la fecha de creacion del registro y me guarda
    @PrePersist
    public void prePersist(){
        this.createAt= new Date();
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public List<ItemFactura> getItems() {
        return this.items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    public Double getTotal(){
        Double total =0.00;
        
        for (ItemFactura itemFactura : items){
            total+= itemFactura.getImporte();
        }
        return total;
    }


    private static final long serialVersionUID = 1L;


}