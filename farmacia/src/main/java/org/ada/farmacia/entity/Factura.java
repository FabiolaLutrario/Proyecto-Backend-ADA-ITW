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
    private Double totalVenta;

    @Column(nullable=false)
    private Double impuesto;

    private String estado;

    @OneToMany(mappedBy = "factura",cascade = CascadeType.REMOVE)
    private List<DetalleCompraMedicamento> detalleCompraMedicamentos;

    @OneToMany(mappedBy = "factura",cascade = CascadeType.REMOVE)
    private List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public Factura() {
    }

    public Factura(Integer id, LocalDate fecha, List<DetalleCompraMedicamento> detalleCompraMedicamentos) {
        this.fecha = fecha;
        this.detalleCompraMedicamentos = detalleCompraMedicamentos;
    }

    public Factura(LocalDate fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Factura(LocalDate fecha, String estado, Cliente cliente) {
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Factura(LocalDate fecha, String estado, List<DetalleCompraMedicamento> detalleCompraMedicamentos) {
        this.fecha = fecha;
        this.estado = estado;
        this.detalleCompraMedicamentos = detalleCompraMedicamentos;
    }

    public Factura(LocalDate fecha, String estado, List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos, Cliente cliente) {
        this.fecha = fecha;
        this.estado = estado;
        this.detalleCompraMiscelaneos = detalleCompraMiscelaneos;
        this.cliente = cliente;
    }

    public Factura(LocalDate fecha, List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos, Cliente cliente) {
        this.fecha = fecha;
        this.detalleCompraMiscelaneos = detalleCompraMiscelaneos;
        this.cliente = cliente;
    }

    public Factura(LocalDate fecha, String estado, List<DetalleCompraMedicamento> detalleCompraMedicamentos, List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos, Cliente cliente) {
        this.fecha = fecha;
        this.estado = estado;
        this.detalleCompraMedicamentos = detalleCompraMedicamentos;
        this.detalleCompraMiscelaneos = detalleCompraMiscelaneos;
        this.cliente = cliente;
    }

    public Factura(LocalDate fecha, List<DetalleCompraMedicamento> detalleCompraMedicamentos, List<DetalleCompraMiscelaneo> detalleCompraMiscelaneos, Cliente cliente) {
        this.fecha = fecha;
        this.detalleCompraMedicamentos = detalleCompraMedicamentos;
        this.detalleCompraMiscelaneos = detalleCompraMiscelaneos;
        this.cliente = cliente;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public String getEstado() {
        return estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<DetalleCompraMedicamento> getDetalleCompraMedicamentos() {
        if(detalleCompraMedicamentos==null) {
            detalleCompraMedicamentos=new ArrayList<>();
        }
        return detalleCompraMedicamentos;
    }

    public List<DetalleCompraMiscelaneo> getDetalleCompraMiscelaneos() {
        if(detalleCompraMiscelaneos==null) {
            detalleCompraMiscelaneos=new ArrayList<>();
        }
        return detalleCompraMiscelaneos;
    }
}
