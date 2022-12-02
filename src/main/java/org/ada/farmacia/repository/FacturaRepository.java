package org.ada.farmacia.repository;

import org.ada.farmacia.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
