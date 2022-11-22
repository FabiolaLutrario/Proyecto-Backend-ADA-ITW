package org.ada.farmacia.repository;

import org.ada.farmacia.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String> {

    //Acá me pedía ponerlo como tipo Lista porque es lo que va a retornar en
    //medicamentoService (me generaba conflicto) PREGUNTAR SI ESTÁ BIEN
    //Antes estaba en Optional
    List<Medicamento> findByNombreComercial(String nombreComercial);
    List<Medicamento> findByNombreGenerico(String nombreGenerico);
}
