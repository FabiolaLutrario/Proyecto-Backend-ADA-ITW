package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.ada.farmacia.entity.Laboratorio;

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
    private Float precioCompra;
    private Integer stock;
    private String laboratorio;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Float precioCompra, Integer stock) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis =dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    public MedicamentoDTO(String id, String nombreComercial, String nombreGenerico, String presentacion, String principioActivo, String dosis, Float precioCompra, Integer stock, String laboratorio) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
        this.dosis = dosis;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.laboratorio = laboratorio;
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

    public Float getPrecioCompra() {
        return precioCompra;
    }

    public Integer getStock() {
        return stock;
    }

    public String getLaboratorio() {
        return laboratorio;
    }
}
