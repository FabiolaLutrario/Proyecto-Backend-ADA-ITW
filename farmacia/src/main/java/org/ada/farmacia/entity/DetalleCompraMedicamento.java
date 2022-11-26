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

    @Column(nullable=false)
    private Float precio;

    @ManyToOne(fetch=FetchType.EAGER)//Carga el medicamento asociados a los detalles de compra
    //cada vez que se consulten los detalles de compra asociados al medicamento.
    @JoinColumn(name="medicamento_id", nullable=false)
    private Medicamento medicamento;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="factura_id", nullable=false)
    private Factura factura;

    public DetalleCompraMedicamento() {
    }

    public DetalleCompraMedicamento(Integer cantidad, Float precio, Medicamento medicamento, Factura factura) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.medicamento = medicamento;
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

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public Factura getFactura() {
        return factura;
    }
}
