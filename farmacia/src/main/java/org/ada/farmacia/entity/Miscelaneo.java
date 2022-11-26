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
    private Float precioCompra;

    @Column(nullable=false)
    private Integer stock;

    @OneToMany(mappedBy = "miscelaneo", fetch = FetchType.LAZY)
    private List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos;

    public Miscelaneo() {
    }

    public Miscelaneo(String id, String nombre, String descripcion, Float precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public Miscelaneo(String id, String nombre, Float precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public Miscelaneo(String id, String nombre, Float precioCompra, Integer stock, List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMiscelaneos = detalleCompraMiscelaneos;
    }

    public Miscelaneo(String id, String nombre, String descripcion, Float precioCompra, Integer stock, List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMiscelaneos = detalleCompraMiscelaneos;
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

    public Float getPrecioCompra() {
        return precioCompra;
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
}
