package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DetalleCompraMedicamentoDTO {

    private Integer id;
    @JsonAlias("medicamento_id")
    private String idMedicamento;
    private Integer cantidad;
    @JsonAlias("precio_unitario")
    private double precioUnitario;
    @JsonAlias("precio_total")
    private Double precioTotal;

    public DetalleCompraMedicamentoDTO() {
    }

    public DetalleCompraMedicamentoDTO(String idMedicamento, Integer cantidad) {
        this.idMedicamento = idMedicamento;
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

    public String getIdMedicamento() {
        return idMedicamento;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
