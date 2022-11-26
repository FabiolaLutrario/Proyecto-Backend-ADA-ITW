package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DetalleCompraMedicamentoDTO {

    private Integer id;
    @JsonAlias("id_medicamento")
    private String idMedicamento;
    private Integer cantidad;
    private Float precio;

    public DetalleCompraMedicamentoDTO() {
    }

    public DetalleCompraMedicamentoDTO(String idMedicamento, Integer cantidad, Float precio) {
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getIdMedicamento() {
        return idMedicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Float getPrecio() {
        return precio;
    }
}
