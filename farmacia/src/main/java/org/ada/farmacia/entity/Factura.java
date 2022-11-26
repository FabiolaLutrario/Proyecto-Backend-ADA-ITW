package org.ada.farmacia.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable=false)
    private LocalDate fecha;

    @Column(nullable=false)
    private Float impuesto;

    @Column(nullable=false)
    private Float totalVenta;

    private String estado;

    @OneToMany(mappedBy = "factura",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<DetalleCompraMedicamento> detalleCompraMedicamentos;

    @OneToMany(mappedBy = "factura",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Cliente cliente;

    public Factura() {
    }

    public Factura(Integer id, LocalDate fecha, Float impuesto, Float totalVenta, List<DetalleCompraMedicamento> detalleCompraMedicamentos) {
        this.id = id;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.detalleCompraMedicamentos = detalleCompraMedicamentos;
    }

    public Factura(Integer id, LocalDate fecha, Float impuesto, Float totalVenta, String estado, List<DetalleCompraMedicamento> detalleCompraMedicamentos) {
        this.id = id;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.estado = estado;
        this.detalleCompraMedicamentos = detalleCompraMedicamentos;
    }

    public Factura(Integer id, LocalDate fecha, Float impuesto, Float totalVenta, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
        this.estado = estado;
    }

    public Factura(Integer id, LocalDate fecha, Float impuesto, Float totalVenta) {
        this.id = id;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.totalVenta = totalVenta;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
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

    public List<DetalleCompraMedicamento> getDetalleCompraMedicamentos() {
        if(detalleCompraMedicamentos==null) {
            detalleCompraMedicamentos=new ArrayList<>();
        }
        return detalleCompraMedicamentos;
    }
}
