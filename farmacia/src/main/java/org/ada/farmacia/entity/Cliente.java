package org.ada.farmacia.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "cliente")
public class Cliente {

    @Id
    private String id;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String apellido;

    private String contacto;

    private String direccion;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Factura> facturas;

    public Cliente() {
    }

    public Cliente(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(String id, String nombre, String apellido, String contacto, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public Cliente(String id, String nombre, String apellido, String contacto, String direccion, List<Factura> facturas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.direccion = direccion;
        this.facturas = facturas;
    }

    public Cliente(String id, String nombre, String apellido, List<Factura> facturas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.facturas = facturas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContacto() {
        return contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Factura> getFacturas() {
        if(facturas==null) {
            facturas=new ArrayList<>();
        }
        return facturas;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "nombre":
                this.nombre = (String) newValue;
                break;
            case "apellido":
                this.apellido = (String) newValue;
                break;
            case "contacto":
                this.contacto = (String) newValue;
                break;
            case "direccion":
                this.direccion = (String) newValue;
                break;
        }
    }
}
