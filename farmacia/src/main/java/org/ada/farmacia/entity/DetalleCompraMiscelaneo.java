package org.ada.farmacia.entity;

import javax.persistence.*;

@Entity
@Table(name="detalle_compra_miscelaneo")
public class DetalleCompraMiscelaneo {

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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="miscelaneo_id", nullable=false)
    private Miscelaneo miscelaneo;

    public DetalleCompraMiscelaneo() {
    }

    public DetalleCompraMiscelaneo(Integer cantidad, Miscelaneo miscelaneo) {
        this.cantidad = cantidad;
        this.miscelaneo = miscelaneo;
    }

    public DetalleCompraMiscelaneo(Integer cantidad, Miscelaneo miscelaneo, Factura factura) {
        this.cantidad = cantidad;
        this.miscelaneo = miscelaneo;
        this.factura = factura;
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

    public Miscelaneo getMiscelaneo() {
        return miscelaneo;
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
