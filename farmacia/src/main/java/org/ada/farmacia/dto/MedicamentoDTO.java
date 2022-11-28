package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class MedicamentoDTO {

    private String id;
    @JsonAlias("nombre_comercial")
    private String nombreComercial;
    @JsonAlias("nombre_generico")
    private String nombreGenerico;
    private String presentacion;
    @JsonAlias("principio_activo")
    private String principioActivo;
    private String dosis;
    @JsonAlias("precio_compra")
    private Double precioCompra;
    @JsonAlias("precio_venta")
    private Double precioVenta;
    private Integer stock;
    @JsonAlias("detalle_compra_medicamento")
    private List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS;
    @JsonAlias("nombre_laboratorio")
    private String nombreLaboratorio;
    @JsonAlias("laboratorio_id")
    private Integer laboratorioId;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Double precioCompra, Integer stock) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis =dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Double precioCompra, Integer stock, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis = dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Double precioCompra, Integer stock, List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS, String nombreLaboratorio) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis = dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.detalleCompraMedicamentoDTOS = detalleCompraMedicamentoDTOS;
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Double precioCompra, Integer stock, String nombreLaboratorio) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis = dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Double precioCompra, Integer stock, Integer laboratorioId) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis = dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.laboratorioId = laboratorioId;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getId() {
        return id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public String getDosis() {
        return dosis;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public Integer getStock() {
        return stock;
    }

    public String getNombreLaboratorio() {
        return nombreLaboratorio;
    }

    public Integer getLaboratorioId() {
        return laboratorioId;
    }

    public List<DetalleCompraMedicamentoDTO> getDetalleCompraMedicamentoDTOS() {
        return detalleCompraMedicamentoDTOS;
    }
}
