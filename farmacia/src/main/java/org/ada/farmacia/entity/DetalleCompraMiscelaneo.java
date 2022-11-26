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

    @Column(nullable=false)
    private Float precio;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="miscelaneo_id", nullable=false)
    private Miscelaneo miscelaneo;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="factura_id", nullable=false)
    private Factura factura;

    public DetalleCompraMiscelaneo() {
    }

    public DetalleCompraMiscelaneo(Integer cantidad, Float precio, Miscelaneo miscelaneo, Factura factura) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.miscelaneo = miscelaneo;
        this.factura = factura;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public Miscelaneo getMiscelaneo() {
        return miscelaneo;
    }

    public Factura getFactura() {
        return factura;
    }
}
