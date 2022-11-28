package org.ada.farmacia.entity;

import javax.persistence.*;

@Entity
@Table(name="detalle_compra_medicamento")
public class DetalleCompraMedicamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private Integer cantidad;

    @Column(name="precio_unitario", nullable=false)
    private Double precioUnitario;

    @Column(name="precio_total", nullable=false)
    private Double precioTotal;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="factura_id", nullable=false)
    private Factura factura;

    @ManyToOne(fetch=FetchType.EAGER)//Carga el medicamento asociados a los detalles de compra
    //cada vez que se consulten los detalles de compra asociados al medicamento.
    @JoinColumn(name="medicamento_id", nullable=false)
    private Medicamento medicamento;

    public DetalleCompraMedicamento() {
    }

    public DetalleCompraMedicamento(Integer cantidad, Medicamento medicamento, Factura factura) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.factura = factura;
    }

    public DetalleCompraMedicamento(Integer cantidad, Medicamento medicamento) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public void setPrecioUnitario(Double precioUnitario) {
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

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public Factura getFactura() {
        return factura;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }
}
