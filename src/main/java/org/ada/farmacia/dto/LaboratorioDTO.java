package org.ada.farmacia.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class LaboratorioDTO {

    private Integer id;
    private String nombre;
    @JsonAlias("medicamentos")
    private List<MedicamentoDTO> medicamentoDTOS;

    public LaboratorioDTO() {
    }

    public LaboratorioDTO(String nombre, List<MedicamentoDTO> medicamentoDTOS) {
        this.nombre = nombre;
        this.medicamentoDTOS = medicamentoDTOS;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<MedicamentoDTO> getMedicamentoDTOS() {
        return medicamentoDTOS;
    }
}
