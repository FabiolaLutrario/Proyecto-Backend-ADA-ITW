package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.ada.farmacia.entity.Cliente;

import java.util.List;

public class FacturaDTO {

    private Integer id;
    private String fecha;
    @JsonAlias("cliente_id")
    private String idCliente;
    private Double impuesto;
    @JsonAlias("total_venta")
    private Double totalVenta;
    @JsonAlias("detalle_compra_medicamento")
    private List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS;
    @JsonAlias("detalle_compra_miscelaneo")
    private List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS;

    public FacturaDTO() {
    }

    public FacturaDTO(String fecha, String idCliente, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS, List<DetalleCompraMiscelaneoDTO> detalleCompraMiscelaneoDTOS) {
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
        this.detalleCompraMiscelaneoDTOS = detalleCompraMiscelaneoDTOS;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
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

    public List<DetalleCompraMedicamentoDTO> getDetalleCompraMedicamentoDTOS() {
        return detalleCompraMedicamentoDTOS;
    }

    public List<DetalleCompraMiscelaneoDTO> getDetalleCompraMiscelaneoDTOS() {
        return detalleCompraMiscelaneoDTOS;
    }
}
