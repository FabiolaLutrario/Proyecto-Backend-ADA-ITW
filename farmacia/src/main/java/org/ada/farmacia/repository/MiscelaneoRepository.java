package org.ada.farmacia.repository;

import org.ada.farmacia.entity.Medicamento;
import org.ada.farmacia.entity.Miscelaneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiscelaneoRepository extends JpaRepository<Miscelaneo, String> {

    List<Miscelaneo> findByNombre(String nombre);
}
