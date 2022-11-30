package org.ada.farmacia.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="medicamento")
public class Medicamento {

    @Id
    private String id;

    @Column(name="nombre_comercial",nullable=false)
    private String nombreComercial;

    @Column(name="nombre_generico",nullable=false)
    private String nombreGenerico;

    @Column(nullable=false)
    private String presentacion;

    @Column(name="principio_activo",nullable=false)
    private String principioActivo;

    @Column(nullable=false)
    private String dosis;

    @Column(name="precio_compra",nullable=false)
    private Double precioCompra;

    @Column(name="precio_venta",nullable=false)
    private Double precioVenta;

    @Column(nullable=false)
    private Integer stock;

    @ManyToOne(fetch=FetchType.EAGER)//Carga el laboratorio asociado a los medicamentos
    //cada vez que se consulten los medicamentos asociados a ese laboratorio.
    @JoinColumn(name="laboratorio_id")
    private Laboratorio laboratorio;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "medicamento")
    private List<DetalleCompraMedicamento> detalleCompraMedicamentos;

    public Medicamento(){
    }

    public Medicamento(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Double precioCompra, Integer stock, Laboratorio laboratorio) {
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

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
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

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public List<DetalleCompraMedicamento> getDetalleCompraMedicamentos() {
        if(detalleCompraMedicamentos==null) {
            detalleCompraMedicamentos=new ArrayList<>();
        }
        return detalleCompraMedicamentos;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "nombre_comercial":
                this.nombreComercial = (String) newValue;
                break;
            case "nombre_generico":
                this.nombreGenerico = (String) newValue;
                break;
            case "presentacion":
                this.presentacion = (String) newValue;
                break;
            case "principio_activo":
                this.principioActivo = (String) newValue;
                break;
            case "dosis":
                this.dosis = (String) newValue;
                break;
            case "precio_compra":
                this.precioCompra = (Double) newValue;
                this.precioVenta = precioCompra*1.30;
                break;
            case "stock":
                this.stock = (Integer) newValue;
                break;
            case "laboratorio_id":
                this.laboratorio = (Laboratorio) newValue;
                break;
        }
    }
}
