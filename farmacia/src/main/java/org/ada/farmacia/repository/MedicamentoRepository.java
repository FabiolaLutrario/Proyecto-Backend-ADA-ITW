package org.ada.farmacia.repository;

import org.ada.farmacia.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String> {

    List<Medicamento> findByNombreComercial(String nombreComercial);
    List<Medicamento> findByNombreGenerico(String nombreGenerico);
}
