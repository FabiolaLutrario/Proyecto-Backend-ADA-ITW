package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DetalleCompraMiscelaneoDTO {

    private Integer id;
    @JsonAlias("miscelaneo_id")
    private String idMiscelaneo;
    @JsonAlias("factura_id")
    private Integer cantidad;
    @JsonAlias("precio_unitario")
    private double precioUnitario;
    @JsonAlias("precio_total")
    private Double precioTotal;

    public DetalleCompraMiscelaneoDTO() {
    }

    public DetalleCompraMiscelaneoDTO(String idMiscelaneo, Integer cantidad) {
        this.idMiscelaneo = idMiscelaneo;
        this.cantidad = cantidad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public String getIdMiscelaneo() {
        return idMiscelaneo;
    }


}
