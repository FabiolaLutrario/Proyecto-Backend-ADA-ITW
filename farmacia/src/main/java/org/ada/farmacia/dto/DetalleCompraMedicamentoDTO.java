package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DetalleCompraMedicamentoDTO {

    private Integer id;
    @JsonAlias("medicamento_id")
    private String medicamentoId;
    @JsonAlias("factura_id")
    private Integer facturaId;
    private Integer cantidad;
    private Float precio;

    public DetalleCompraMedicamentoDTO(String medicamentoId, Integer facturaId, Integer cantidad, Float precio) {
        this.medicamentoId = medicamentoId;
        this.facturaId = facturaId;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getMedicamentoId() {
        return medicamentoId;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Float getPrecio() {
        return precio;
    }
}
