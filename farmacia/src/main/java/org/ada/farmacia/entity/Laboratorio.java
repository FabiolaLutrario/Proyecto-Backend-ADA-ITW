package org.ada.farmacia.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="laboratorio")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String nombre;

    @OneToMany(mappedBy = "laboratorio", fetch = FetchType.EAGER)
    //Qué tipo de cascada poner?
    private List<Medicamento> medicamentos;

    public Laboratorio() {
    }

    public Laboratorio(String nombre) {
        this.nombre = nombre;
    }

    public Laboratorio(String nombre, List<Medicamento> medicamentos) {
        this.nombre = nombre;
        this.medicamentos = medicamentos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Medicamento> getMedicamentos() {
        if(medicamentos==null) {
            medicamentos=new ArrayList<>();
        }
        return medicamentos;
    }
}
