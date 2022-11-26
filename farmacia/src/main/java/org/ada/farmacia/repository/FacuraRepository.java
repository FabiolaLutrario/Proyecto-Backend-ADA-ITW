package org.ada.farmacia.repository;

import org.ada.farmacia.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacuraRepository extends JpaRepository<Factura, Integer> {
}
