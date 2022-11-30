package org.ada.farmacia.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="miscelaneo")
public class Miscelaneo {

    @Id
    private String id;

    @Column(nullable=false)
    private String nombre;

    private String descripcion;

    @Column(name="precio_compra", nullable=false)
    private Double precioCompra;

    @Column(name="precio_venta",nullable=false)
    private Double precioVenta;

    @Column(nullable=false)
    private Integer stock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "miscelaneo")
    private List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos;

    public Miscelaneo() {
    }

    public Miscelaneo(String id, String nombre, String descripcion, Double precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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

    public List<DetalleCompraMiscelaneo> getDetalleCompraMiscelaneos() {
        if(detalleCompraMiscelaneos==null) {
            detalleCompraMiscelaneos=new ArrayList<>();
        }
        return detalleCompraMiscelaneos;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "nombre":
                this.nombre = (String) newValue;
                break;
            case "descripcion":
                this.descripcion = (String) newValue;
                break;
            case "precio_compra":
                this.precioCompra = (Double) newValue;
                this.precioVenta = precioCompra*1.30;
                break;
            case "stock":
                this.stock = (Integer) newValue;
                break;
        }
    }
}
