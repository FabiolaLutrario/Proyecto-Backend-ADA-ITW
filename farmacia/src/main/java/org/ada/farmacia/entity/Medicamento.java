package org.ada.farmacia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="medicamento")
public class Medicamento {

    @Id
    private String id;

    @Column(name="nombre_comercial",nullable=false)
    private String nombreComercial;

    @Column(name="nombre_generico")
    private String nombreGenerico;

    @Column(nullable=false)
    private String presentacion;

    @Column(name="principio_activo",nullable=false)
    private String principioActivo;

    @Column(nullable=false)
    private String dosis;

    @Column(name="precio_compra",nullable=false)
    private Float precioCompra;

    @Column(nullable=false)
    private Integer stock;


    @ManyToOne(fetch=FetchType.EAGER)//Carga el laboratorio asociado a los medicamentos
    //cada vez que se consulten los medicamentos asociados a ese laboratorio.
    @JoinColumn(name="laboratorio_id")
    private Laboratorio laboratorio;

    public Medicamento(){

    }

    public Medicamento(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Float precioCompra, Integer stock, Laboratorio laboratorio) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis = dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.laboratorio = laboratorio;
    }

    public String getId() {
        return id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public String getDosis() {
        return dosis;
    }

    public Float getPrecioCompra() {
        return precioCompra;
    }

    public Integer getStock() {
        return stock;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }
}
