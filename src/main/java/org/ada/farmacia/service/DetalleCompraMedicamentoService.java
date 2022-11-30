package org.ada.farmacia.service;

import org.ada.farmacia.dto.DetalleCompraMedicamentoDTO;
import org.ada.farmacia.entity.*;
import org.ada.farmacia.exceptions.ResourceNotFoundException;
import org.ada.farmacia.repository.DetalleCompraMedicamentoRepository;
import org.ada.farmacia.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleCompraMedicamentoService {

    private final DetalleCompraMedicamentoRepository detalleCompraMedicamentoRepository;
    private final MedicamentoRepository medicamentoRepository;

    public DetalleCompraMedicamentoService(DetalleCompraMedicamentoRepository detalleCompraMedicamentoRepository, MedicamentoRepository medicamentoRepository) {
        this.detalleCompraMedicamentoRepository = detalleCompraMedicamentoRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public List<DetalleCompraMedicamento> create(List<DetalleCompraMedicamentoDTO> detalleCompraMedicamentoDTOS, Factura factura) {

        List <DetalleCompraMedicamento> detalleCompraMedicamentos = new ArrayList<>();
        for (DetalleCompraMedicamentoDTO detalleCompraMedicamentoDTO : detalleCompraMedicamentoDTOS) {

            Optional<Medicamento> medicamento = medicamentoRepository.findById(detalleCompraMedicamentoDTO.getIdMedicamento());
            if (medicamento.isEmpty()) {
                throw new ResourceNotFoundException("EL medicamento que está intentando facturar no existe.");
            }
            DetalleCompraMedicamento detalleCompraMedicamento =mapToEntity(detalleCompraMedicamentoDTO, factura,medicamento.get());
            detalleCompraMedicamentoRepository.save(detalleCompraMedicamento);
            detalleCompraMedicamentos.add(detalleCompraMedicamento);
        }

        return detalleCompraMedicamentos;
    }

    private DetalleCompraMedicamento mapToEntity(DetalleCompraMedicamentoDTO detalleCompraMedicamentoDTO, Factura factura, Medicamento medicamento) {
        DetalleCompraMedicamento detalleCompraMedicamento = new DetalleCompraMedicamento(detalleCompraMedicamentoDTO.getCantidad(),
                medicamento,factura);
        detalleCompraMedicamento.setPrecioTotal(medicamento.getPrecioVenta()
        * detalleCompraMedicamentoDTO.getCantidad());

        return detalleCompraMedicamento;
    }

    public List<DetalleCompraMedicamentoDTO> mapToDTOS(List<DetalleCompraMedicamento> detalleCompraMedicamentos) {

        return detalleCompraMedicamentos.stream()
                .map(detalleCompraMedicamento -> mapToDTO(detalleCompraMedicamento))
                .collect(Collectors.toList());
    }

    private DetalleCompraMedicamentoDTO mapToDTO (DetalleCompraMedicamento detalleCompraMedicamento){
        DetalleCompraMedicamentoDTO detalleCompraMedicamentoDTO = new DetalleCompraMedicamentoDTO(detalleCompraMedicamento.getMedicamento().getId(),
                detalleCompraMedicamento.getCantidad());
        detalleCompraMedicamentoDTO.setPrecioTotal(detalleCompraMedicamento.getPrecioTotal());

        return detalleCompraMedicamentoDTO;
    }
}
