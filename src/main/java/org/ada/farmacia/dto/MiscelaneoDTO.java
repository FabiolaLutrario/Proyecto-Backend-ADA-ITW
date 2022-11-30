package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class MiscelaneoDTO {

    private String id;
    private String nombre;
    private String descripcion;
    @JsonAlias("precio_compra")
    private Double precioCompra;
    @JsonAlias("precio_venta")
    private Double precioVenta;
    private Integer stock;
    @JsonAlias("detalle_compra_miscelaneo")
    private List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS;

    public MiscelaneoDTO() {
    }

    public MiscelaneoDTO(String id, String nombre, String descripcion, Double precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public MiscelaneoDTO(String id, String nombre, Double precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public MiscelaneoDTO(String id, String nombre, String descripcion, Double precioCompra, Integer stock, List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMiscelaneoDTOS = detalleCompraMiscelaneoDTOS;
    }

    public MiscelaneoDTO(String id, String nombre, Double precioCompra, Integer stock, List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMiscelaneoDTOS = detalleCompraMiscelaneoDTOS;
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

    public Integer getStock() {
        return stock;
    }
}
