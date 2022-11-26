package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class MiscelaneoDTO {

    private String id;
    private String nombre;
    private String descripcion;
    @JsonAlias("precio_compra")
    private Float precioCompra;
    private Integer stock;
    @JsonAlias("detalle_compra_miscelaneo")
    private List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS;

    public MiscelaneoDTO() {
    }

    public MiscelaneoDTO(String id, String nombre, String descripcion, Float precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public MiscelaneoDTO(String id, String nombre, Float precioCompra, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public MiscelaneoDTO(String id, String nombre, String descripcion, Float precioCompra, Integer stock, List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMiscelaneoDTOS = detalleCompraMiscelaneoDTOS;
    }

    public MiscelaneoDTO(String id, String nombre, Float precioCompra, Integer stock, List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMiscelaneoDTOS = detalleCompraMiscelaneoDTOS;
    }
}
