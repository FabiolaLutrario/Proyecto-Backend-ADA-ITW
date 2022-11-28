package org.ada.farmacia.repository;

import org.ada.farmacia.entity.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {

    Optional<Laboratorio> findByNombre(String nombre);
}
