package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DetalleCompraMiscelaneoDTO {

    private Integer id;
    private Integer cantidad;
    private Float precio;
    @JsonAlias("id_miscelaneo")
    private String idMiscelaneo;

    public DetalleCompraMiscelaneoDTO() {
    }

    public DetalleCompraMiscelaneoDTO(Integer cantidad, Float precio, String idMiscelaneo) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.idMiscelaneo = idMiscelaneo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public String getIdMiscelaneo() {
        return idMiscelaneo;
    }
}
