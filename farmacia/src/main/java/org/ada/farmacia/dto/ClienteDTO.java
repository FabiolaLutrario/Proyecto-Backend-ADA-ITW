package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class ClienteDTO {

    private String id;
    private String nombre;
    private String apellido;
    private String contacto;
    private String direccion;
    @JsonAlias("factura")
    private List<FacturaDTO> facturaDTOS;

    public ClienteDTO() {
    }

    public ClienteDTO(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public ClienteDTO(String id, String nombre, String apellido, String contacto, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public ClienteDTO(String id, String nombre, String apellido, String contacto, String direccion, List<FacturaDTO> facturaDTOS) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.direccion = direccion;
        this.facturaDTOS = facturaDTOS;
    }

    public ClienteDTO(String id, String nombre, String apellido, List<FacturaDTO> facturaDTOS) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.facturaDTOS = facturaDTOS;
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

}
