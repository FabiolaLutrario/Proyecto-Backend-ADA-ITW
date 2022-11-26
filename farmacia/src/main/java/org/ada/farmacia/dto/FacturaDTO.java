package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.ada.farmacia.entity.Cliente;

import java.util.List;

public class FacturaDTO {

    private Integer id;
    private String fecha;
    private String idCliente;
    private Float impuesto;
    @JsonAlias("total_venta")
    private Float totalVenta;
    private String estado;
    @JsonAlias("detalle_compra_medicamentos")
    private List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS;

    public FacturaDTO() {
    }

    public FacturaDTO(Float impuesto, Float totalVenta, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS) {
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
    }

    public FacturaDTO(Float impuesto, Float totalVenta, String estado, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS) {
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
    }

    public FacturaDTO(Integer id, String fecha, String idCliente, Float impuesto, Float totalVenta, String estado, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
    }

    public FacturaDTO(Integer id, String fecha, String idCliente, Float impuesto, Float totalVenta, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public Float getImpuesto() {
        return impuesto;
    }

    public Float getTotalVenta() {
        return totalVenta;
    }

    public String getEstado() {
        return estado;
    }

    public List<DetalleCompraMedicamentoDTO> getDetalleCompraMedicamentoDTOS() {
        return detalleCompraMedicamentoDTOS;
    }
}
